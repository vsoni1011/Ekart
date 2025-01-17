package com.ekart.dao;

import com.ekart.model.Product;

import com.ekart.model.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select images from product_images where product_id=:id", nativeQuery = true)
    List<byte[]> getProductImagesById(@Param("id") Long id);
    Optional<Page<Product>> findProductBySubCategory(Pageable pageable, SubCategory subCategory);
}
