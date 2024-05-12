package com.dira.multidb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // Membuat objek konfigurasi CORS berbasis URL
        CorsConfiguration config = new CorsConfiguration(); // Membuat objek konfigurasi CORS
        config.setAllowCredentials(true); // Mengizinkan kredensial untuk CORS
        config.addAllowedOriginPattern("*"); // Mengizinkan semua origin
        config.addAllowedHeader("*"); // Mengizinkan semua header
        config.addAllowedMethod("*"); // Mengizinkan semua metode HTTP
        source.registerCorsConfiguration("/**", config); // Mendaftarkan konfigurasi CORS untuk semua path
        return new CorsFilter(source); // Mengembalikan filter CORS yang telah dikonfigurasi
    }
}
