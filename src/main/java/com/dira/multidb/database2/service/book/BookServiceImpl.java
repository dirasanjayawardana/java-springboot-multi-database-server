package com.dira.multidb.database2.service.book;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dira.multidb.database2.entity.Book;
import com.dira.multidb.database2.payload.BookRequest;
import com.dira.multidb.database2.payload.Response;
import com.dira.multidb.database2.payload.ResponseData;
import com.dira.multidb.database2.repository.BookRepo;


@Service
public class BookServiceImpl implements BookService {

    @Autowired // untuk inject object agar tidak null
    BookRepo bookRepo;

    @Override
    public ResponseData<List<Book>> getBooks() {
        // ambil data All dari databse
        List<Book> books = this.bookRepo.findAll();

        // return ke response data
        ResponseData<List<Book>> results = new ResponseData<List<Book>>("success", true, books);
        return results;
    }

    @Override
    public Response postBook(BookRequest request) {
        // buat entitas buku dari payload
        Book book = new Book(request.getTitle(), request.getDescription());

        // save to repo
        this.bookRepo.save(book);

        // return response
        Response response = new Response("success added book", true);
        return response;
    }

    @Override
    public ResponseData<Book> updateBook(String id, BookRequest request) {
        // find book by id
        // jika id ada -> book timpa dari req
        // jika id tidak ada -> throw exception
        Book book = this.bookRepo.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("ID book " + id + " not found!");
        });

        // save book
        book.setTitle(request.getTitle());
        book.setDescription(request.getDescription());
        this.bookRepo.save(book);

        // return response
        ResponseData<Book> resBook = new ResponseData<Book>("success update data", true, book);
        return resBook;

    }

    @Override
    public Response deleteBook(String id) {
        // soft delete (tidak benar-benar menghapus, hanya mengubah status isDeleted
        // menjadi true)

        // find book by id
        Book book = this.bookRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID book " + id + " not found!"));

        // update status deleted
        book.setIsDeleted(true);

        // save
        this.bookRepo.save(book);

        // response
        Response response = new Response(id + " is deleted", true);
        return response;
    }

    @Override
    public ResponseData<List<Book>> getBooksByTitle(String title) {
        // dari data dengan custom method dari BookRepo
        List<Book> books = this.bookRepo.searchByTitle(title);
        ResponseData<List<Book>> responseData = new ResponseData<List<Book>>("success", true, books);

        // response
        return responseData;
    }

}
