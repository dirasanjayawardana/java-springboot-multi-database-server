package com.dira.multidb.database1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dira.multidb.database1.entity.User;
import com.dira.multidb.database1.payload.LoginUserRequest;
import com.dira.multidb.database1.payload.TokenResponse;
import com.dira.multidb.database1.payload.WebResponse;
import com.dira.multidb.database1.service.AuthService;

@RestController // menandakan bahwa ini adalah controller
public class AuthController {

    @Autowired // untuk inject object agar tidak null (instansiasi object)
    private AuthService authService;

    // membuat controller register dengan return payload WebResponse dengan data TokenResponse, menggunakan RequestBody dengan struktur data dari payload LoginUserRequest
    // menggunakan method POST, dengan tipe data yang dapat diterima dan dikembalikan berupa JSON (atribute consume dan produce tidak wajib)
    @PostMapping(
        path = "/api/auth/login", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TokenResponse> login(@RequestBody LoginUserRequest request) {
        TokenResponse tokenResponse = authService.login(request);
        return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
    }

    @DeleteMapping(
        path = "/api/auth/logout",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> logout(User user) {
        authService.logout(user);
        
        return WebResponse.<String>builder().data("OK").errors(null).build();
    }
    
}
