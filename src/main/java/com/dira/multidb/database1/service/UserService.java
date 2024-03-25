package com.dira.multidb.database1.service;

import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dira.multidb.database1.entity.User;
import com.dira.multidb.database1.payload.RegisterUserRequest;
import com.dira.multidb.database1.payload.UpdateUserRequest;
import com.dira.multidb.database1.payload.UserResponse;
import com.dira.multidb.database1.repository.UserRepository;
import com.dira.multidb.security.BCrypt;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

// service menjalankan repository yang sudah dibuat
// idealnya dibuat interfacenya dulu baru di implement ke class

@Service
public class UserService {

    @Autowired // untuk inject object agar tidak null
    private UserRepository userRepository;

    @Autowired
    private Validator validator; // membuat valiadator, contoh untuk validasi header dan params, agar terpusat di service, sebenarnya bisa melakukanvalidasi di controller

    @Autowired
    private ValidationService validationService;

    // dibuat void karena register tidak mereturn data
    // @Transactional --> Jika semuanya berhasil, maka perubahan akan disimpan di database. Jika ada masalah di tengah jalan, maka semua perubahan akan dibatalkan
    @Transactional
    public void register(RegisterUserRequest request){
        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        // jika constraintViloation ada isinya, maka ada error
        if (constraintViolations.size() != 0 ){
            throw new ConstraintViolationException(constraintViolations);
        }

        // cek apakah user sudah terdaftar atau belum
        if(userRepository.existsById(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username alrady register");
        }

        // membuat objek user dengan hash password 
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        // simpan user baru ke database
        userRepository.save(user);
    }
    
    // mendapatkan user saat ini
    public UserResponse get(User user){
        // tidak perlu melakukan query ke database karena data langsung dari parameter
        return UserResponse.builder().username(user.getUsername()).name(user.getName()).build();
    }

    // update user
    public UserResponse update(User user, UpdateUserRequest request){
        validationService.validate(request);

        // jika pada request name ada isinya, maka lakukan perubahan pada name
        if(Objects.nonNull(request.getName())){
            user.setName(request.getName());
        }

        // jika pada request password ada isinya, maka lakukan perubahan pada password yang sudah di BCrypt
        if(Objects.nonNull(request.getPassword())){
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        // simpan perubahan
        userRepository.save(user);

        return UserResponse.builder().name(user.getName()).username(user.getUsername()).build();
    }

}
