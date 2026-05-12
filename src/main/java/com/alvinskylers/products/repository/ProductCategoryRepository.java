package com.alvinskylers.products.repository;

import com.alvinskylers.products.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query("SELECT c " +
            "FROM ProductCategory c " +
            "WHERE LOWER(c.name) LIKE " +
            "LOWER(CONCAT('%', :query, '%'))")
    Page<ProductCategory> findAllProductCategories(String query, Pageable pageable);
}
