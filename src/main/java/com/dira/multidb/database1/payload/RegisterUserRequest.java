package com.dira.multidb.database1.payload;

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

public class RegisterUserRequest {
 
    @NotBlank // menandakan bahwa data ini tidak boleh kosong
    @Size(max = 100) // menandakan bahwadata ini panjangnya tidak boleh lebih dari 100
    private String username;
    
    @NotBlank
    @Size(max = 100) // menandakan bahwadata ini panjangnya tidak boleh lebih dari 100
    private String password;

    @NotBlank
    @Size(max = 100) // menandakan bahwadata ini panjangnya tidak boleh lebih dari 100
    private String name;
}
