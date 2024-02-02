package com.example.shopapp.model;

import jakarta.persistence.AttributeConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    PENDING("pending"),
    PROCESSING("processing"),
    SHIPPED("shipped"),
    DELIVERED("deliverd"),
    CANCELED("canceled"),
    ;

    private final String status;
    public static OrderStatus fromStatus(String abbr) {
        for (OrderStatus c : values()) {
            if (c.status.equals(abbr)) {
                return c;
            }
        }
        return null;
    }

    public static class OrderConverter implements AttributeConverter<OrderStatus, String>{

        @Override
        public String convertToDatabaseColumn(OrderStatus orderStatus) {
            return orderStatus.getStatus();
        }

        @Override
        public OrderStatus convertToEntityAttribute(String s) {
            return fromStatus(s);
        }
    }
}
