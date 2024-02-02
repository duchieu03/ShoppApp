package com.example.shopapp.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public interface ProductResponse {
    String getName();

    BigDecimal getPrice();

    String getThumbnail();

    String getDescription();

    String getCategoryName();

    long getQuantity();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+0000")
    LocalDateTime getCreatedAt();

    String getCreatedBy();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+0000")
    LocalDateTime getUpdatedAt();

    String getUpdatedBy();
}
