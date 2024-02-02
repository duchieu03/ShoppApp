package com.example.shopapp.service.product;

import com.example.shopapp.core.response.ApiRes;
import com.example.shopapp.core.response.code.ClientCode;
import com.example.shopapp.dto.PageDTO;
import com.example.shopapp.dto.request.ProductRequest;
import com.example.shopapp.dto.response.ProductDetailResponse;
import com.example.shopapp.dto.response.ProductResponse;
import com.example.shopapp.exception.ApiException;
import com.example.shopapp.model.Category;
import com.example.shopapp.model.Product;
import com.example.shopapp.model.ProductImage;
import com.example.shopapp.repository.CategoryRepository;
import com.example.shopapp.repository.ProductImageRepository;
import com.example.shopapp.repository.ProductRepository;
import com.example.shopapp.utils.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;
    private final ObjectMapper objectMapper;

    @Override
    public ApiRes<String> createProduct(ProductRequest productRequest) throws IOException {
        if(productRepository.existsByName(productRequest.getName())) throw new ApiException(ClientCode.PRODUCT_NAME_EXISTED);
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ApiException(ClientCode.CATEGORY_NOT_FOUND));
        Product product = Product.builder()
                .price(productRequest.getPrice())
                .name(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .description(productRequest.getDescription())
                .category(category)
                .build();
        product.setThumbnail(uploadImage(productRequest.getFiles(), product));
        productRepository.save(product);
        return ApiRes.success("Create successful");
    }

    @Override
    public ApiRes<Page<ProductResponse>> findProduct(String name, PageDTO pageDTO) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageDTO.getSort()), pageDTO.getSortBy());
        Pageable pageable = PageRequest.of(pageDTO.getPage(), pageDTO.getSize(), sort);
        Page<ProductResponse> page = productRepository.findProduct(name, pageable);
        return ApiRes.success(page);
    }

    @Override
    public ApiRes<String> updateProduct(Long id, ProductRequest productRequest) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException(ClientCode.PRODUCT_NOT_FOUND));
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ApiException(ClientCode.CATEGORY_NOT_FOUND));
        List<ProductImage> productImages = product.getProductImages();
        productImages.forEach(productImage -> {
            try {
                FileUtils.deleteFile(productImage.getImageUrl());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setDescription(product.getDescription());
        product.setCategory(category);
        product.setThumbnail(uploadImage(productRequest.getFiles(), product));
        productRepository.save(product);
        return ApiRes.success("Update successful");
    }

    @Override
    public ApiRes<String> deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException(ClientCode.PRODUCT_NOT_FOUND));
        product.setDeleted(true);
        List<ProductImage> productImages = productImageRepository.findAllByProduct(product);
        productImages.forEach(productImage -> {
            try {
                FileUtils.deleteFile(productImage.getImageUrl());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        productRepository.save(product);
        productImageRepository.deleteAll(productImages);
        return ApiRes.success("Delete successful");
    }

    @Override
    public ApiRes<ProductDetailResponse> findProductDetail(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException(ClientCode.PRODUCT_NOT_FOUND));
        ProductDetailResponse productDetailResponse = ProductDetailResponse.builder()
                .name(product.getName())
                .categoryName(product.getCategory().getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .productImages(product.getProductImages().stream()
                        .map(ProductImage::getImageUrl)
                        .collect(Collectors.toList()))
                .thumbnail(product.getThumbnail())
                .build();
        return ApiRes.success(productDetailResponse);
    }

    private String uploadImage(List<MultipartFile> multipartFiles, Product product) throws IOException {
        if(multipartFiles.isEmpty()) throw new ApiException(ClientCode.PRODUCT_IMAGE_REQUIRED);
        String thumbnail = "";
        List<ProductImage> list = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            String path = FileUtils.storeFile(multipartFile);
            if(thumbnail.isEmpty()) thumbnail=path;
            ProductImage productImage = ProductImage.builder()
                    .product(product)
                    .imageUrl(path)
                    .build();
            list.add(productImage);
        }
        product.setProductImages(list);
        return thumbnail;
    }
}
