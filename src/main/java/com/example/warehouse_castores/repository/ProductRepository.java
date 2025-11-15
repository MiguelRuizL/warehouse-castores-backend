package com.example.warehouse_castores.repository;

import com.example.warehouse_castores.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    // busco todos los productos por status
    List<Product> findByStatus(Boolean status);

}
