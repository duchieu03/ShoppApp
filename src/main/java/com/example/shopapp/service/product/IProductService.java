package com.example.shopapp.service.product;


import com.example.shopapp.core.response.ApiRes;
import com.example.shopapp.dto.PageDTO;
import com.example.shopapp.dto.request.ProductRequest;
import com.example.shopapp.dto.response.ProductDetailResponse;
import com.example.shopapp.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface IProductService {
    public ApiRes<String> createProduct(ProductRequest productRequest) throws IOException;
    public ApiRes<Page<ProductResponse>> findProduct(String name, PageDTO pageDTO);
    public ApiRes<String> updateProduct(Long id, ProductRequest productRequest) throws IOException;
    public ApiRes<String> deleteProduct(Long id);
    public ApiRes<ProductDetailResponse> findProductDetail(Long id);
}
