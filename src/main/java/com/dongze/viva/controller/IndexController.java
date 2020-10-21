package com.dongze.viva.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nicholas
 * @version 创建时间：2020/10/21
 * @ClassName IndexController
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/hello")
    public String hello(){
        return "hello,hikari-url!";
    }

}
