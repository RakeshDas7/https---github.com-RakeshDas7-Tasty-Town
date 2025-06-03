package com.tastytown.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tastytown.backend.dto.CategoryRequestDTO;
import com.tastytown.backend.entity.Category;
import com.tastytown.backend.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Category Api", description = "This controller manages crud oprations for category api") // to change category-controller to category api and descriptions...
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("path")
    @ApiResponse(responseCode = "201", description = "Category created")// for return 201 in swagger api
    @Operation(summary = "Create a new category") //to provide the descriptions in swagger api in the side od post api or get api etc....
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestDTO requestDTO) {
        // return categoryService.saveCategory(requestDTO);

        // var savedCategory = categoryService.saveCategory(requestDTO);
        // return ResponseEntity<>(savedCategory, HttpStatus.CREATED);
        return new ResponseEntity<>(categoryService.saveCategory(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Category> getAllCategory() {
        return categoryService.findAllCategories();

    }
    
    
}
