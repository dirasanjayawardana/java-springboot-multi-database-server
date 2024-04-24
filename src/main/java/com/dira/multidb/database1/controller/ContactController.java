package com.dira.multidb.database1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dira.multidb.database1.entity.User;
import com.dira.multidb.database1.payload.ContactResponse;
import com.dira.multidb.database1.payload.CreateContactRequest;
import com.dira.multidb.database1.payload.WebResponse;
import com.dira.multidb.database1.service.ContactService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController // menandakan bahwa ini adalah controller
public class ContactController {

    @Autowired // untuk inject object agar tidak null (instansiasi object)
    private ContactService contactService;

    // menggunakan method POST, dengan tipe data yang dapat diterima dan dikembalikan berupa JSON (atribute consume dan produce tidak wajib)
    @PostMapping(
        path = "/api/contacts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ContactResponse> createContact(User user, @RequestBody CreateContactRequest request) {
        ContactResponse contactResponse = contactService.createContact(user, request);
        return WebResponse.<ContactResponse>builder()
                .data(contactResponse)
                .build();
    }

}
