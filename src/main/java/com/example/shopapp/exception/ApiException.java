package com.example.shopapp.exception;

import com.example.shopapp.core.response.ApiCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiException extends RuntimeException{
    private final ApiCode errorCode;
}
