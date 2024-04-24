package com.dira.multidb.database1.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // membuat setter getter
@AllArgsConstructor
@NoArgsConstructor
@Builder // untuk mempermudah saat membuat objek secara langsung

public class CreateContactRequest {
    
    @NotBlank
    @Size(max = 100)
    private String firstName;

    @Size(max = 100)
    private String lastName;

    @Size(max = 100)
    @Email // untuk validasi format email
    private String email;

    @Size(max = 100)
    private String phone;
}
