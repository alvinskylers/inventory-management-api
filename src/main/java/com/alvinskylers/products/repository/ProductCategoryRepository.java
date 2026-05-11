package com.alvinskylers.products.repository;

import com.alvinskylers.products.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, BigInteger> {
}
