package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/v1/put-api")
@RestController
public class PutController {

    // http://localhost:9090/api/v1/put-api/member
    @PutMapping("/member")
    public String postMember(@RequestBody Map<String, Object> putData) {
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    // http://localhost:9090/api/v1/put-api/member1
    @PutMapping("/member1")
    public String postMemberDto1(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }

    // http://localhost:9090/api/v1/put-api/member2
    @PutMapping("/member2")
    public MemberDto postMemberDto2(@RequestBody MemberDto memberDto) {
        return memberDto;
    }

    // http://localhost:9090/api/v1/put-api/member3
    @PutMapping("/member3")
    public ResponseEntity<MemberDto> postMemberDto3(@RequestBody MemberDto memberDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }

}
