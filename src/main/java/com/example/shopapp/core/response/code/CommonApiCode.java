package com.example.shopapp.core.response.code;

import com.example.shopapp.core.response.ApiCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonApiCode implements ApiCode {
    SUCCESS(200, "Success"),
    ERROR(500, "Error"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not found record"),
    INVALID_QUERY(405, "Invalid sql query"),
    DUPLICATED_RECORD(407, "Duplicated record");

    private final int code;
    private final String message;
}
