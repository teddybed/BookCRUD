package com.bookStore.controller;

import com.bookStore.entity.Book;
import com.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService service;
    @GetMapping("/")
    public String home(){

        return "home";
    }
    @GetMapping("/book_register")
    public String bookRegister(){

        return "bookRegister";
    }
    @GetMapping("/avaliable_book")
    public ModelAndView getAllBook(){
        List<Book>list= service.getAllBook();
        ModelAndView m= new ModelAndView();
        m.setViewName("bookList");
        m.addObject("book",list);
        return new ModelAndView("bookList","book",list);

    }
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
        service.save(b);
        return "redirect:/avaliable_book";
    }
    @GetMapping("/my_books")
    public String getMyBooks(){

        return "myBooks";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id")int id){
        service.deleteById(id);
        return "redirect:/avaliable_book";
    }


}
