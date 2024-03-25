package com.dira.multidb.database1.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // membuat setter getter
@NoArgsConstructor
@AllArgsConstructor
@Builder // mempermudah saat membuat objek secara langsung

public class WebResponse<T> {

    private T data;

    private String errors;
    
}
