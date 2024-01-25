package com.example.shopapp.core.response;

import com.example.shopapp.core.response.code.CommonApiCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ApiRes <T> {
    private int code;
    private String message;
    private T data;

    public static ApiRes SUCCESS = new ApiRes<>(CommonApiCode.SUCCESS.getCode(), CommonApiCode.SUCCESS.getMessage(), null);
    public static ApiRes ERROR = new ApiRes<>(CommonApiCode.ERROR.getCode(), CommonApiCode.ERROR.getMessage(), null);

    public static <T> ApiRes<T> with(ApiCode apiCode, T data){
        return ApiRes.<T>builder()
                .code(apiCode.getCode())
                .message(apiCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> ApiRes<T> success(T data) {
        return ApiRes.with(CommonApiCode.SUCCESS, data);
    }

    public static <T> ApiRes<T> error(T data) {
        return ApiRes.with(CommonApiCode.ERROR, data);
    }

    public static <T> ApiRes<T> error(String msg) {
        return ApiRes
                .<T>builder()
                .code(CommonApiCode.ERROR.getCode())
                .message(msg)
                .data(null)
                .build();
    }

    public static <T> ApiRes<T> error(Throwable throwable) {
        return ApiRes.<T>with(CommonApiCode.ERROR, null)
                .toBuilder()
                .message(throwable.getMessage())
                .build();
    }
}
