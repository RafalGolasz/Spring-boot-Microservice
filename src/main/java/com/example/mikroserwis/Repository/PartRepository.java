package com.example.mikroserwis.Repository;

import com.example.mikroserwis.Model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {

    Optional<Part> findByMaterialNumberAndSupplierNumber(int materialNumber, int supplierNumber);
}
