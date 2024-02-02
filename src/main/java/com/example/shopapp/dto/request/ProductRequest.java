package com.example.shopapp.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotEmpty
    @NotNull
    private String name;
    @Min(value = 0)
    private BigDecimal price;
    @Min(value = 1)
    private Long quantity;
    private String description;
    @NotNull
    private Long categoryId;
    private List<MultipartFile> files = new ArrayList<>();
}
