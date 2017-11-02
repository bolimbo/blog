package com.codeup.blog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{a}/and/{b}")
    @ResponseBody
    public int addMethod(@PathVariable int a, @PathVariable int b){
        return a + b;
    }
    @GetMapping("/subtract/{b}/from/{a}")
    @ResponseBody
    public int substractMethod(@PathVariable int a, @PathVariable int b){
        return a - b;
    }
    @GetMapping("/divide/{a}/by/{b}")
    @ResponseBody
    public int divideMethod(@PathVariable int a, @PathVariable int b){
        return a / b;
    }
    @GetMapping("/multiply/{a}/by/{b}")
    @ResponseBody
    public int multiplyMethod(@PathVariable int a, @PathVariable int b){
        return a * b;
    }

}
