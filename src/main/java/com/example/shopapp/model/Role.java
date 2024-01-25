package com.example.shopapp.model;

import com.example.shopapp.core.model.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.repository.NoRepositoryBean;

@Getter
@Setter
@AllArgsConstructor
@NoRepositoryBean
@Builder
@Entity
@Table(name = "roles")
@SQLRestriction("is_deleted <> true")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public static String ADMIN = "ADMIN";
    public static String USER = "USER";
}
