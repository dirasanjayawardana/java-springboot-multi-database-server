package com.dira.multidb.database1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dira.multidb.database1.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    // berisi method untuk melakukan query ke database
    // secara default sudah ada method findAll(), save(), findById()
    // jika ingin menambahkan custom query bisa ditambahkan disini

}
