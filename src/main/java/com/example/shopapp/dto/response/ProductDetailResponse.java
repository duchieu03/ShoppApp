package com.example.shopapp.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDetailResponse {
    private String name;

    private BigDecimal price;

    private String thumbnail;

    private String description;

    private String categoryName;

    private Long quantity;

    private List<String> productImages;
}
