package com.dira.multidb.database1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dira.multidb.database1.entity.User;
import com.dira.multidb.database1.payload.RegisterUserRequest;
import com.dira.multidb.database1.payload.UpdateUserRequest;
import com.dira.multidb.database1.payload.UserResponse;
import com.dira.multidb.database1.payload.WebResponse;
import com.dira.multidb.database1.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

// macam-macam request
// @RequestHeader("Authorization") String token
// @RequestBody UpdateUserRequest request
// @PathVariable("id") Long id
// @RequestParam("page") Integer page

@RestController // menandakan bahwa ini adalah controller
public class UserController {

    @Autowired // untuk inject object agar tidak null (instansiasi object)
    private UserService userService;

    // alternatif jika tidak menggunakan @AutoWired, harus inject contstructor manual
    // private UserService userService;
    // public UserController(UserService userService) {
    //     this.userService = userService;
    // }

    // membuat controller register dengan return payload WebResponse dengan data string, menggunakan RequestBody dengan struktur data dari payload RegisterUserRequest
    // menggunakan method POST, dengan tipe data yang dapat diterima dan dikembalikan berupa JSON (atribute consume dan produce tidak wajib)
    @PostMapping(
        path = "/api/users", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterUserRequest request){
        // memanggil method register dari userService dengan parameter request
        userService.register(request);
        // mengembalikan WebResponse dengan data string yang dibentuk dengan builder karena telah menggunakan anotasi builder dipayload WebResponse 
        return WebResponse.<String>builder().data("OK").errors(null).build();
    }


    // mengambil data user saat ini, akan menjalankan UserArgumentResolver untuk mengecek apakah user sudah login dan membawa token, jika ingin cek manual bisa dengan @RequestHeader("Authorization")
    @GetMapping(
        path = "/api/users/current",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> get(User user){
        UserResponse userResponse = userService.get(user);
        return WebResponse.<UserResponse>builder().data(userResponse).errors(null).build();
    }
    

    // update user saat ini
    @PatchMapping(
        path = "/api/users/current",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> update(User user, @RequestBody UpdateUserRequest request){
        UserResponse userResponse = userService.update(user, request);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }
}
