package com.example.shopapp.rest;

import com.example.shopapp.core.enums.WebApiPrefix;
import com.example.shopapp.core.response.ApiRes;
import com.example.shopapp.dto.PageDTO;
import com.example.shopapp.dto.request.CategoryRequest;
import com.example.shopapp.service.category.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebApiPrefix.API_CATEGORY_V1)
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiRes<?>> getAll(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "50") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort,
            @RequestParam(name = "sortBy", required = false, defaultValue = "createdAt") String sortBy
    ){
        PageDTO pageDto = PageDTO.builder()
                .sort(sort)
                .sortBy(sortBy)
                .page(page)
                .size(size)
                .build();
        return ResponseEntity.ok(categoryService.findCategory(name, pageDto));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId){
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PostMapping
    public ResponseEntity<ApiRes<String>> createCategory(@RequestBody @Valid CategoryRequest categoryRequest){
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryRequest categoryRequest
    ){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryRequest));
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}
