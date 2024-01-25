package com.example.shopapp.model;

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
@Table(name = "social_accounts")
@SQLRestriction("is_deleted <> true")
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider", nullable = false, length = 20)
    private String provider;

    @Column(name = "provider_id", nullable = false, length = 50)
    private String providerId;

    @Column(name = "name",length = 150)
    private String name;

    @Column(name = "email", length = 150)
    private String email;
}
