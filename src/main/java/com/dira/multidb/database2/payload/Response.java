package com.dira.multidb.database2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // menambahkan setter getter
@NoArgsConstructor // membuat constructor kosong
@AllArgsConstructor // membuat semua constructor
public class Response {
    private String message;
    private Boolean success;
}
