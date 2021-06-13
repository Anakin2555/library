package com.example.libarary.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.libarary.bean.Book;
import com.example.libarary.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BookController {
    private static final BookService bookService = new BookService();

    @GetMapping("/addBook")
    public boolean addBook(@RequestParam("param") String param) {
        Book book = JSONObject.parseObject(param, Book.class);
        return bookService.add(book);
    }

    @GetMapping("/updateBook")
    public boolean updateBook(@RequestParam("param") String param) {
        Book book = JSONObject.parseObject(param, Book.class);
        return bookService.update(book);
    }

//    @PostMapping("/uploadImage")
//    public boolean upload(@RequestParam("file") MultipartFile file,
//                          HttpServletRequest request) {
//        return bookService.updateImage(Integer.parseInt(request.getParameter("id")), file);
//    }

    @GetMapping("/deleteBook")
    public boolean deleteBook(@RequestParam("id") String isbn) {
        return bookService.delete(isbn);
    }

    @GetMapping("/getBooks")
    public String getBooks() {
        List<Book> books = bookService.query();
        return JSONObject.toJSONString(books);
    }

    @GetMapping("/queryFromIsbn")
    public String queryFromIsbn(@RequestParam("isbn") String isbn) {
        Book book = bookService.queryFromIsbn(isbn);
        return JSONObject.toJSONString(book);
    }

    @GetMapping("/queryFromTitle")
    public String queryFromTitle(@RequestParam("title") String title) {
        List<Book> book = bookService.queryFromTitle(title);
        return JSONObject.toJSONString(book);
    }

}
