package com.dira.multidb.database2.payload;

import lombok.Data;

@Data // menambahkan setter getter
public class BookRequest {
    private String title;
    private String description;
}
