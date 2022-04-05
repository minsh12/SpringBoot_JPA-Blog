package com.cos.blog.test;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 페이지이동없이 데이터만 리턴해주며 응답만 해줄 수 있도록
public class DummyControllerTest {

    @Autowired  // 의존성 주입 (DI)
    private UserRepository userRepository;

    // http://localhost:8000/blog/dummy/join (요청)
    // http의 body에 username, password, email 데이터를 가지고 요청하면 join()함수의 파리미터에 맞춰 들어감
//    @PostMapping("/dummy/join")    // 회원가입 (insert)
//    public String join(String username, String password, String email) {    // 원래는 join(@RequestParam("username") String u, ~~~) 적어야하는데, 변수명 정확하게 똑같이 적어주면 그럴 필요 X
//        System.out.println("username : " + username);
//        System.out.println("password : " + password);
//        System.out.println("email : " + email);
//        return "회원가입이 완료되었습니다.";
//    }
    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("id : " + user.getId());
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getCreateDate());

        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}