package com.example.shopapp.core.enums;

import lombok.experimental.UtilityClass;
@UtilityClass
public class WebApiPrefix {
    public static final String WEB_PREFIX_V1 = "/api/v1/";
    public static final String API_CATEGORY_V1 = WEB_PREFIX_V1 + "categories";
    public static final String API_PRODUCT_V1 = WEB_PREFIX_V1 + "products";
}
