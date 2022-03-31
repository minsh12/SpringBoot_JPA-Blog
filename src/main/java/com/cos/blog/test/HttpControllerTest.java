package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

// '@Controller'의 역할 : 사용자의 요청에 대한 응답(HTML 파일)을 해줌
// '@RestController'의 역할 : 사용자의 요청에 대한 응답(Data)을 해줌
@RestController
public class HttpControllerTest {

    // 인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다.
    // http://localhost:8080/http/get (SELECT)
//    @GetMapping("/http/get")
//    public String getTest(@RequestParam int id, @RequestParam String username) {
//        return "get 요청 : " + id + ", " + username;
//    }
    // 위처럼 값을 하나씩 받지 않고, 한번에 다 받아올 수 있는 방법
    @GetMapping("/http/get")
    public String getTest(Member m) {       // /http/get?id=2&username=minhye&password=1234&email=abcd@naver.com
        return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }


    // http://localhost:8080/http/post (INSERT)
//    @PostMapping("/http/post")        // x-www-form 데이터로 전송 (key, value 지정해서 form 제출)
//    public String postTest(Member m) {
//        return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
//    }
//    @PostMapping("/http/post")      // raw 데이터로 전송 - MIME타입이 text/plain (평문 보냄)
//    public String postTest(@RequestBody String text) {      // Body의 데이터를 text 변수로 받음
//        return "post 요청 : " + text;
//    }
    @PostMapping("/http/post")      // raw 데이터로 전송 - MINE타입 application.json (json 형태)
    public String postTest(@RequestBody Member m) {

    }


    // http://localhost:8080/http/put (UPDATE)
    @PutMapping("/http/put")
    public String putTest() {
        return "put 요청";
    }

    // http://localhost:8080/http/delete (DELETE)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
