package com.dira.multidb.database1.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // membuat setter getter
@AllArgsConstructor
@NoArgsConstructor
@Builder // untuk mempermudah saat membuat objek secara langsung

public class CreateContactRequest {
    
    private String firstName;
}
