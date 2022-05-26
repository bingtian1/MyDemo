package com.example.demo.Control;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MyDemo")
public class MyDemo {
    @GetMapping("/myTest")
    public User myTest(@RequestParam String name){
        return new User(name.hashCode(),name);
    }
}
