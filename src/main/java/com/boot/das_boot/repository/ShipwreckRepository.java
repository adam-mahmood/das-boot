package com.boot.das_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.das_boot.model.Shipwreck;

public interface ShipwreckRepository extends JpaRepository<Shipwreck, Long> {

}
