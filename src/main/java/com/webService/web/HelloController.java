package com.webService.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON을 반환
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
