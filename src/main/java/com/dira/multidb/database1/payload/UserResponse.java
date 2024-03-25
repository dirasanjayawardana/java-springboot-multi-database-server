package com.dira.multidb.database1.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // membuat setter getter
@AllArgsConstructor
@NoArgsConstructor
@Builder // mempermudah saat membuat objek secara langsung
public class UserResponse {

    private String username;

    private String name;

}
