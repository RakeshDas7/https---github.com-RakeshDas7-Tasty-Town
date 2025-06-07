package com.tastytown.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Category created")// for return 201 in swagger api
    @Operation(summary = "Create a new category") //to provide the descriptions in swagger api in the side od post api or get api etc....
    public ResponseEntity<FoodResponseDTO> savedFood(@RequestBody FoodRequestDTO foodRequestDTO) {
        return ResponseEntity.ok(foodService.createFood(foodRequestDTO));
    }
}
