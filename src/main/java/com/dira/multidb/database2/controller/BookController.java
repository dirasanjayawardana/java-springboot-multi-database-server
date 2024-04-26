package com.dira.multidb.database2.controller;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dira.multidb.database2.entity.Book;
import com.dira.multidb.database2.payload.BookRequest;
import com.dira.multidb.database2.payload.Response;
import com.dira.multidb.database2.payload.ResponseData;
import com.dira.multidb.database2.service.book.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity<?> showBooks(@RequestParam(defaultValue = "", value = "title") String title) {
        try {
            // deklarasikan wadah untuk menampung response
            ResponseData<List<Book>> response;
            // cek apakah menampilkan semua buku atau mencari berdasarkan title
            if (title == "" || title == null || Objects.isNull(title) || title.equals("")) {
                // panggil method getBook dari BookServicesdan isi response
                response = bookService.getBooks();
            } else {
                // panggil method getBooksByTitle dari BookServices dan isi response
                response = bookService.getBooksByTitle(title);
            }
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            // bisa throw exception jika telah melakukan custom exception, atau bisa langsung beri response
            // throw e;
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookRequest request) {
        try {
            Response response = bookService.postBook(request);
            return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
        } catch (Exception e) {
            // bisa throw exception jika telah melakukan custom exception, atau bisa langsung beri response
            // throw e;
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable String id, @RequestBody BookRequest request) {
        try {
            ResponseData<Book> responseData = this.bookService.updateBook(id, request);
            return ResponseEntity.ok().body(responseData);
        } catch (Exception e) {
            // bisa throw exception jika telah melakukan custom exception, atau bisa langsung beri response
            // throw e;
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        try {
            Response response = this.bookService.deleteBook(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            // bisa throw exception jika telah melakukan custom exception, atau bisa langsung beri response
            // throw e;
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
