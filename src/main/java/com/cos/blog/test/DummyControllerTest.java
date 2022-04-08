package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController     // : html파일이 아니라 data를 리턴해주는 controller
                    // 페이지이동없이 데이터만 리턴해주며 응답만 해줄 수 있도록
public class DummyControllerTest {

    @Autowired  // 의존성 주입 (DI)
    private UserRepository userRepository;

    // password, email을 수정
    @Transactional      // save()를 하지 않아도 update가 되게해줌
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {   // @ResquestBody로 (Body-raw)json데이터를 받아옴
                                                                                    // json데이터를 요청 -> Springboot이 java object로 변환해서 받음 (MessageConverter의 Jackson라이브러리가 변환해서 받음)
        System.out.println("id: " + id);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        /* save 함수
            id를 전달하지 않으면 insert를 하고,
            id를 전달하면 해당 id에 대한 데이터가 있으면 update를 하고,
            id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 함.
        */
        //userRepository.save(user);

        // 더티 체킹
        return null;
    }

    // http://localhost:8000/blog/dummy/user
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // [페이징] 한페이지당 최신순의 id에 대한 2건의 데이터를 리턴받아볼 것
    // http://localhost:8000/blog/dummy/user   //http://localhost:8000/blog/dummy/user?page=0 (page=1)
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    // {id} 주소로 파라메터를 전달받을 수 O
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {

        /* findById() 리턴형태가 Optional인 이유 :
        *   만약 user/9를 찾으면 디비에서 못찾아와서 user = null이 되고,
        *   그럼 return null이 되서 그럼 프로그램에 문제가 될 수 O
        *   -> Optional로 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return하란 의도 */

        /* 바로 아래 코드를 람다식으로 구현 (Supplier 등 타입을 전혀 몰라도 됨)
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자는 없습니다.);
        });
        */
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
            }
        });

        /*
            요청은 웹브라우저에서,
            user 객체 = 자바 오브젝트, 자바 오브젝트를 반환하는데 그전에 변환 필요.
            웹브라우저가 이해할 수 있는 데이터인 json으로.
            스프링부트는 MessageConverter가 응답시에 자동 작동.
            만약에 자바 오브젝트를 리턴하게 되면MessageConverter가 Jackson 라이브러리를 호출해서
            user 오브젝트를 json으로 변환해서 브러우저에게 전달.
        */

        return user;
    }

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

        user.setRole(RoleType.USER);
        userRepository.save(user);      // insert할 때 사용
        return "회원가입이 완료되었습니다.";
    }
}