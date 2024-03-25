package com.dira.multidb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dira.multidb.database1.resolver.UserArgumentResolver;

import java.util.List;

// mendaftarkan resolver yang sudah dibuat
// jadi sekarang, setiap ada controller yang butuh data User, maka akan secara otomatis menggunakan UserArgumentResolver untuk mendapatkan datanya
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private UserArgumentResolver userArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(userArgumentResolver);
    }
}