package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller     // 해당 경로 이하에 있는 파일을 리턴하므로 '/'를 붙여야함
                // @RestController - 문자 그 제체를 리턴
public class TempControllerTest {

    // http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("tempHome()");
        // 파일리턴 기본경로 : src/main/webapp/WEB-INF/views/
        // 리턴명 : home과 /home 둘다 됨
        // 풀경로 : src/main/webapp/WEB-INF/views/home.jsp
        return "home";
    }

    @GetMapping("/temp/test")
    public String temImg() {
        return "test";
    }
}
