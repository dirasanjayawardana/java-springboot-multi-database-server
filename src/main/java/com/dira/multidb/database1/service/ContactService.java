package com.dira.multidb.database1.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dira.multidb.database1.entity.Contact;
import com.dira.multidb.database1.entity.User;
import com.dira.multidb.database1.payload.ContactResponse;
import com.dira.multidb.database1.payload.CreateContactRequest;
import com.dira.multidb.database1.repository.ContactRepository;

import jakarta.transaction.Transactional;

// service menjalankan repository yang sudah dibuat
// idealnya dibuat interfacenya dulu baru di implement ke class

@Service
public class ContactService {
    
    @Autowired // untuk inject object agar tidak null
    private ContactRepository contactRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional // Jika semuanya berhasil, maka perubahan akan disimpan di database. Jika ada masalah di tengah jalan, maka semua perubahan akan dibatalkan
    public ContactResponse createContact(User user, CreateContactRequest request) {
        // validasi request
        validationService.validate(request);

        // buat contact baru
        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);

        // simpan contact ke database
        contactRepository.save(contact);

        // return response
        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }
}
