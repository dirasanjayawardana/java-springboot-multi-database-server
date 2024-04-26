package com.dira.multidb.database2.exception.custom;

// ada dua jenis exception:
// Checked (harus di handle dengan try catch), contoh IOException, SQLException
// Unchecked (tidak harus di handle dengan try catch), contoh RuntimeException

// perbedaan antara exception dengan error:
// Exception adalah masalah yang dapat ditangani oleh program,
// Error adalah masalah yang biasanya di luar kendali program dan tidak seharusnya ditangani secara normal, biasanya menyebabkan program berhenti

public class CustomException extends RuntimeException {
    private int statusCode;

    public CustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
