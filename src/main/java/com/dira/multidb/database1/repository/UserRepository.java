package com.dira.multidb.database1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dira.multidb.database1.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // berisi method untuk melakukan query ke database
    // secara default sudah ada method findAll(), save(), findById()
    // jika ingin menambahkan custom query bisa ditambahkan disini

    Optional<User> findFirstByToken(String token);
}
