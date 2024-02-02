package com.example.shopapp.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    private int page;
    private int size;
    private String sort;
    private String sortBy;
}
