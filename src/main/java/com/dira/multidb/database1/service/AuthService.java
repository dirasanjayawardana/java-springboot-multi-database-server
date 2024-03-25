package com.dira.multidb.database1.service;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dira.multidb.database1.entity.User;
import com.dira.multidb.database1.payload.LoginUserRequest;
import com.dira.multidb.database1.payload.TokenResponse;
import com.dira.multidb.database1.repository.UserRepository;
import com.dira.multidb.security.BCrypt;

import jakarta.transaction.Transactional;

// service menjalankan repository yang sudah dibuat
// idealnya dibuat interfacenya dulu baru di implement ke class

@Service
public class AuthService {

    @Autowired // untuk inject object agar tidak null (sama seperti instansiasi object, private UserRepository userRepository = new UserRepository())
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional // Jika semuanya berhasil, maka perubahan akan disimpan di database. Jika ada masalah di tengah jalan, maka semua perubahan akan dibatalkan
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);

        // cek apakah user terdaftar, jika tidak maka throw error
        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password error"));

        // cek apakah password benar
        if (BCrypt.checkpw(request.getPassword(), user.getPassword())){
            // jika benar buat tokennya, bisa dalam bentuk jwt, contoh ini hanya menggunakan UUID
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());

            // save user token
            userRepository.save(user);

            // return tokennya
            return TokenResponse.builder().token(user.getToken()).expiredAt(user.getTokenExpiredAt()).build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password error");
        } 
    }

    // function untuk menghitung 30 hari kedepan
    private Long next30Days() {
        Instant now = Instant.now();
        Instant next30Days = now.plusSeconds(30 * 24 * 60 * 60); // Tambahkan 30 hari ke waktu sekarang
        return next30Days.toEpochMilli(); // Konversi ke milidetik sejak Epoch Unix
    }
    // private Long next30Days() {
    //     return System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 30);
    // }

    
    // user logout
    @Transactional
    public void logout(User user){
        user.setToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }

}
