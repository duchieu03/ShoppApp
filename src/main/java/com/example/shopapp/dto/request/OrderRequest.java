package com.example.shopapp.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;
    private BigDecimal totalMoney;
    private String shippingAddress;
    private String shippingMethod;
}
