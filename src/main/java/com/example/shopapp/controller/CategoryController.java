package com.example.shopapp.controller;

import com.example.shopapp.dto.request.CategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @GetMapping
    public ResponseEntity<String> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ){
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId){
        return null;
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryRequest categoryRequest
    ){
        return null;
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
        return null;
    }
}
