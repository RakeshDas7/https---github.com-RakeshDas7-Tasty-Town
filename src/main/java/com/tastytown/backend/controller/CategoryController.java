package com.tastytown.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tastytown.backend.dto.CategoryRequestDTO;
import com.tastytown.backend.entity.Category;
import com.tastytown.backend.service.ICategoryService;
import com.tastytown.backend.service.impl.CategoryServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Category Api", description = "This controller manages crud oprations for category api") // to change category-controller to category api and descriptions...
public class CategoryController {
    private final ICategoryService categoryService;
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Category created")// for return 201 in swagger api
    @Operation(summary = "Create a new category") //to provide the descriptions in swagger api in the side od post api or get api etc....
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestDTO requestDTO) {
        // return categoryService.saveCategory(requestDTO);

        // var savedCategory = categoryService.saveCategory(requestDTO);
        // return ResponseEntity<>(savedCategory, HttpStatus.CREATED);
        return new ResponseEntity<>(categoryService.saveCategory(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiResponse(description = "Get All Categories")
    @Operation(summary = "Extract All Categories")
    public ResponseEntity<List<Category>> getAllCategory() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping("/{categoryId}")
    @ApiResponse(description = "Get Category By Id")
    @Operation(summary = "Extract a Category By Id")
    public ResponseEntity<Category> getCategoryById(@PathVariable String categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }
    
    @PutMapping("/{categoryId}")
    @Operation(summary = "Update A Category By Id")
    public ResponseEntity<Category> updateCategory(@PathVariable String categoryId, @RequestBody CategoryRequestDTO requestDTO){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, requestDTO));
    }
    
    @DeleteMapping("/{categoryId}")
    @ApiResponse(responseCode = "204",description = "Delete A Category By Id")
    @Operation(summary = "Delete A Category By Id")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId){
        categoryService.deleteCategory(categoryId);

        return ResponseEntity.noContent().build();
    }
}
