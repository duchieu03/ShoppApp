package com.example.shopapp.controller;

import com.example.shopapp.dto.request.ProductRequest;
import com.example.shopapp.utils.FileUtils;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @GetMapping
    public ResponseEntity<String> getProduct(
            @RequestParam(name = "page", required = false, defaultValue = "0") String page,
            @RequestParam(name = "size", required = false, defaultValue = "0") String size,
            @RequestParam(name = "name", required = false) String name
    ) {
        return null;
    }

    @PostMapping(name="", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> insertProduct(
            @ModelAttribute ProductRequest productRequest
            ) throws IOException {
        for(MultipartFile file: productRequest.getFiles()){
            String filename = FileUtils.storeFile(file,"upload");
        }
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<String> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductRequest productRequest
    ) {
        return null;
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<String> deleteProduct() {
        return null;
    }
}
