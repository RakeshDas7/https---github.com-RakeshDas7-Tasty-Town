package com.tastytown.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tastytown.backend.dto.FoodRequestDTO;
import com.tastytown.backend.dto.FoodResponseDTO;
import com.tastytown.backend.service.IFoodService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/foods")
@RequiredArgsConstructor
@Tag(name = "Food API", description = "API for managing foods")
public class FoodController {
    private final IFoodService foodService;
    private final ObjectMapper objectMapper;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Category created") // for return 201 in swagger api
    @Operation(summary = "Create a new category") // to provide the descriptions in swagger api in the side od post api
                                                  // or get api etc....
    public ResponseEntity<FoodResponseDTO> savedFood(@RequestPart String json,
            @RequestPart MultipartFile foodImage) throws IOException { // RequestPart Is used for upload A file for
                                                                       // total treated as a sIngle json object

        FoodRequestDTO requestDTO = objectMapper.readValue(json, FoodRequestDTO.class);

        return new ResponseEntity<>(foodService.createFood(requestDTO, foodImage), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<FoodResponseDTO>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodResponseDTO> getFoodById(@PathVariable String foodId) {
        return ResponseEntity.ok(foodService.getFoodById(foodId));
    }
    
    @GetMapping("/paginated-foods")
    public ResponseEntity<Page<FoodResponseDTO>> getPaginatedFoods(
                @RequestParam(required = false, defaultValue = "0") int pageNumber, 
                @RequestParam(required = false, defaultValue = "8") int pageSize,
                @RequestParam(required = false, defaultValue = "all") String categoryId,
                @RequestParam(required = false, defaultValue = "all") String search ) {
        return ResponseEntity.ok(foodService.getPaginatedFoods(pageNumber, pageSize, categoryId, search));
    }
}
