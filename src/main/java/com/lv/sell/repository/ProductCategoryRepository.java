package com.lv.sell.repository;

import com.lv.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
        List<ProductCategory> findBycategoryTypeIn(List<Integer> list);
}
