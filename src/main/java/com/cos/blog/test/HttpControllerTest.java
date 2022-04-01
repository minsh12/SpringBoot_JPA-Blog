package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

// '@Controller'의 역할 : 사용자의 요청에 대한 응답(HTML 파일)을 해줌
// '@RestController'의 역할 : 사용자의 요청에 대한 응답(Data)을 해줌
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest : ";

    @GetMapping("/http/build")      // http://localhost:8000/blog/http/build
    public String buildTest() {
        // id값 할당X. builder를 사용하면 필드의 순서나 기존 값 등을 몰라도 편하게 쓸 수 O.
        Member m = Member.builder().username("min").password("1234").email("min@naver.com").build();
        System.out.println(TAG + "getter : " + m.getId());      // 출력: 0
        m.setId(5000);
        System.out.println(TAG + "setter : " + m.getId());      // 출력: 5000
        return "build 테스트 완료";
    }


    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = new Member(1, "min", "1234", "min@naver.com");
        System.out.println(TAG + "getter : " + m.getId());
        m.setId(5000);
        System.out.println(TAG + "setter : " + m.getId());
        return "lombok 테스트 완료";
    }

    // ---------- GET Request ----------
    // 인터넷 브라우저 요청(& 주소 요청)은 무조건 get 요청밖에 할 수 없다.
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



    // ---------- POST Request ----------
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
    public String postTest(@RequestBody Member m) {     // 데이터를 json으로 send하면 스프링부트의 MessageConverter가 자동으로 파싱해서 object에 넣어줌
        return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }



    // ---------- PUT Request ----------
    // http://localhost:8080/http/put (UPDATE)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }



    // ---------- DELETE Request ----------
    // http://localhost:8080/http/delete (DELETE)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
