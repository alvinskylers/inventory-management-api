package com.alvinskylers.products.repository;

import com.alvinskylers.products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p " +
            "WHERE LOWER(p.name) " +
            "LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Product> findProductByName(@Param("query") String query, Pageable pageable);


    @Query("SELECT p FROM Product p " +
            "WHERE LOWER(p.sku) " +
            "LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Product> findProductBySKU(@Param("query") String query, Pageable pageable);


    @Query("SELECT p FROM Product p " +
            "WHERE p.price " +
            "BETWEEN :minPrice AND :maxPrice")
    Page<Product> filterProductByPrice(
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable pageable);
}
