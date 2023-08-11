package com.mul.db.forDatabase2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mul.db.forDatabase2.enties.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
