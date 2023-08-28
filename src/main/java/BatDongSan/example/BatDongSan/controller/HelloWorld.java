package BatDongSan.example.BatDongSan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping(path = "/")
    public String hi(){
        return "Hello World";
    }
}
