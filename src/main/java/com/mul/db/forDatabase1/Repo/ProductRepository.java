package com.mul.db.forDatabase1.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mul.db.forDatabase1.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
