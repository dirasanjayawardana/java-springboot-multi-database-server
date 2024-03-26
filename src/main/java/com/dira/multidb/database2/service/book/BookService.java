package com.dira.multidb.database2.service.book;

import java.util.List;

import com.dira.multidb.database2.entity.Book;
import com.dira.multidb.database2.payload.BookRequest;
import com.dira.multidb.database2.payload.Response;
import com.dira.multidb.database2.payload.ResponseData;


public interface BookService {
    // read
    ResponseData<List<Book>> getBooks();
    ResponseData<List<Book>> getBooksByTitle(String title);

    // create
    Response postBook(BookRequest request);

    // update
    ResponseData<Book> updateBook(String id, BookRequest request);

    // delete
    Response deleteBook(String id);
}
