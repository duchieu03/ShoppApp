package com.example.shopapp.core.response.code;

import com.example.shopapp.core.response.ApiCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClientCode implements ApiCode {
    CATEGORY_NOT_FOUND(10001, "Category not found"),
    PRODUCT_NAME_EXISTED(10002, "Product name existed"),
    PRODUCT_IMAGE_REQUIRED(10003, "Product image required"),
    PRODUCT_NOT_FOUND(10004, "Product not found");

    private final int code;
    private final String message;
}
