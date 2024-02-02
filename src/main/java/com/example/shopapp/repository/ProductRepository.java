package com.example.shopapp.repository;

import com.example.shopapp.dto.response.ProductResponse;
import com.example.shopapp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT p.name AS name, p.price AS price, p.thumbnail as thumbnail,
            p.description as description, c.name AS categoryName, p.quantity AS quantity,
            p.createdAt AS createdAt, p.createdBy AS createdBy,
            p.updatedAt AS updatedAt, p.updatedBy AS updatedBy
            FROM Product p LEFT JOIN p.category c
            WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
            """)
    Page<ProductResponse> findProduct(String name, Pageable pageable);

    boolean existsByName(String name);
}
