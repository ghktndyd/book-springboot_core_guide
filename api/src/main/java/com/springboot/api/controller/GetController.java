package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/v1/get-api")
@RestController
public class GetController {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello, World";
    }

    // http://localhost:9090/api/v1/get-api/variable1/{Stirng variable}
    @GetMapping("/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        return variable;
    }

    // http://localhost:9090/api/v1/get-api/variable1/{Stirng var}
    @GetMapping("/variable2/{variable}")
    public String getVariable2(@PathVariable(value = "variable") String var) {
        return var;
    }

    /**
     * @ApiOperation : 대상 Api의 설명을 작성하기 위한 어노테이션
     * @ApiParam : 매개변수에 대한 설명 및 설정을 위한 어노테이션 Dto를 사용해도 사용가능하다.
     */
    // http://localhost:9090/api/v1/get-api/request1?name=kim&email=ghktn&&organization=playdata
    @ApiOperation(value = "GET 메서드 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping("/request1")
    public String getRequestParam1(
            @ApiParam(value = "이름", readOnly = true) @RequestParam String name,
            @ApiParam(value = "이메일", readOnly = true) @RequestParam String email,
            @ApiParam(value = "회사", readOnly = true) @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }

    // 쿼리스트링에 어떤 값이 들어올지 모를 때는 Map 객체 사용
    // http://localhost:9090/api/v1/get-api/request2?key1=value1&key2=value2
    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    // http://localhost:9090/api/v1/get-api/request3?name=value1&email=value2&organization=value3
    @GetMapping("/request3")
    public String getRequestParam3(MemberDto memberDto) {

        return memberDto.toString();
    }
}
