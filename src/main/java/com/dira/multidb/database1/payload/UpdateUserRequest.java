package com.dira.multidb.database1.payload;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // membuat setter getter
@AllArgsConstructor
@NoArgsConstructor
@Builder // untuk mempermudah saat membuat objek secara langsung

public class UpdateUserRequest {
    // tidak perlu validasi NotBlank, karena opsional

    @Size(max = 100)
    private String name;

    @Size(max = 100)
    private String password;

}
