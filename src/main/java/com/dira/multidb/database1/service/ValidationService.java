package com.dira.multidb.database1.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

// untuk melakukan validasi pada service lainnya, agar tidak menulis code berulang-ulang untuk validasi seperti di UserService

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class ValidationService {
    
    @Autowired // untuk inject object agar tidak null
    private Validator validator;

    public void validate(Object request){
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);

        // jika constraintViloation ada isinya, maka ada error
        if (constraintViolations.size() != 0 ){
            throw new ConstraintViolationException(constraintViolations);
        }
    }

}
