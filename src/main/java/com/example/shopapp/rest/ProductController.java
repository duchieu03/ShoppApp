package com.example.shopapp.rest;

import com.example.shopapp.core.response.ApiRes;
import com.example.shopapp.dto.PageDTO;
import com.example.shopapp.dto.request.ProductRequest;
import com.example.shopapp.dto.response.ProductDetailResponse;
import com.example.shopapp.dto.response.ProductResponse;
import com.example.shopapp.service.product.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;
    @GetMapping
    public ResponseEntity<ApiRes<Page<ProductResponse>>> getProduct(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "50") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort,
            @RequestParam(name = "sortBy", required = false, defaultValue = "createdAt") String sortBy
    ) {
        PageDTO pageDto = PageDTO.builder()
                .sort(sort)
                .sortBy(sortBy)
                .page(page)
                .size(size)
                .build();
        return ResponseEntity.ok(productService.findProduct(name, pageDto));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiRes<ProductDetailResponse>> findProductDetail(@PathVariable Long productId){
        return ResponseEntity.ok(productService.findProductDetail(productId));
    }

    @PostMapping(name="", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiRes<String>> insertProduct(
            @Valid @ModelAttribute ProductRequest productRequest
            ) throws IOException {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PutMapping(value = "/{productId}/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiRes<String>> updateProduct(
            @PathVariable Long productId,
            @Valid @ModelAttribute ProductRequest productRequest
    ) throws IOException {
        return ResponseEntity.ok(productService.updateProduct(productId, productRequest));
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiRes<String>> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }
}
