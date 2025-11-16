package com.example.warehouse_castores.repository;

import com.example.warehouse_castores.model.Logbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogbookRepository extends JpaRepository<Logbook, Long> {

    public List<Logbook> findByMovementType(String movement_type);
}
