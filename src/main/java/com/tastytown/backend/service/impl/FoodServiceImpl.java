package com.tastytown.backend.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tastytown.backend.dto.FoodRequestDTO;
import com.tastytown.backend.dto.FoodResponseDTO;
import com.tastytown.backend.mapper.FoodMapper;
import com.tastytown.backend.repository.CategoryRepository;
import com.tastytown.backend.repository.FoodRepository;
import com.tastytown.backend.service.ICategoryService;
import com.tastytown.backend.service.IFoodService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements IFoodService {
    private final ICategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final FoodRepository foodRepository;

    @Value("${upload.file.dir}") // Value Annotation used for injecting the value of the property // Spel
    private String FILE_DIR;

    @Override
    public FoodResponseDTO createFood(FoodRequestDTO requestDTO, MultipartFile foodImage) throws IOException {
        // check category exist or not
        var existingCategory = categoryService.getCategoryById(requestDTO.categoryId());

        // Save The Image In the folder FleOutputStream
        var fileName = uploadFile(foodImage);

        // save food in database
        var food = FoodMapper.convertToEntity(requestDTO, existingCategory, fileName);

        var savedFood = foodRepository.save(food);

        // return the food response
        return FoodMapper.convertToDTO(savedFood);
    }

    private String uploadFile(MultipartFile foodImage) throws IOException {
        if (!foodImage.isEmpty()) {
            var fileName = foodImage.getOriginalFilename(); // it returns the File name including the extension
            var newFileName = generateFilename(fileName);
            var fos = new FileOutputStream(FILE_DIR + File.separator + newFileName);
            fos.write(foodImage.getBytes());
            fos.close();

            return newFileName;
        }

        throw new FileNotFoundException("Already Image Not Found");
    }

    private String generateFilename(String fileName) {
        var extensionName = fileName.substring(fileName.lastIndexOf('.'));
        var newFileName = UUID.randomUUID().toString();
        return newFileName + extensionName;
    }
}
