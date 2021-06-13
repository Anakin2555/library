package com.example.libarary.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.libarary.bean.User;
import com.example.libarary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam("username") String username,
                         @RequestParam("password") String password) {
        return userService.verify(username, password);
    }

    @GetMapping("/addUser")
    public boolean addUser(@RequestParam("param") String param) {
        User user = JSONObject.parseObject(param, User.class);
        return userService.add(user);
    }

    @GetMapping("/deleteUser")
    public boolean deleteUser(@RequestParam("username") String param) {
        return userService.delete(param);
    }

    @GetMapping("/updateUser")
    public boolean updateUser(@RequestParam("param") String param) {
        User user = JSONObject.parseObject(param, User.class);
        return userService.update(user);
    }

    @GetMapping("/borrow")
    public boolean borrowBook(@RequestParam("username") String username,
                              @RequestParam("bookId") String bookId) {
        return userService.borrowBook(username, bookId);
    }

    @GetMapping("/return")
    public boolean returnBook(@RequestParam("id") int id,
                              @RequestParam("bookId") String bookId) {
        return userService.returnBook(id, bookId);
    }

    @GetMapping("/getUsers")
    public String getUsers() {
        return JSONObject.toJSONString(userService.query());
    }

}
