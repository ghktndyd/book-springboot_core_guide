package com.springboot.api.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/delete-api")
@RestController
public class DeleteController {

    // http://localhost:9090/api/v1/delete-api/{String 값}
    @DeleteMapping(value = "{/variable}")
    public String deleteVariable(@PathVariable String variable) {
        return variable;
    }

    // http://localhost:9090/api/v1/delete-api/request1?email=value
    @DeleteMapping(value = "/request1")
    public String getRequestParam1(@RequestParam String email) {
        return "email : " + email;
    }
}
