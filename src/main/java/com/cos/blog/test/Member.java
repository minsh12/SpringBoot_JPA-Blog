package com.cos.blog.test;

import lombok.*;

//@Getter
//@Setter
@Data       // @Data = @Getter + @Setter
//@AllArgsConstructor            // : 모든 필드 값을 파라미터로 받는 생성자를 생성
//@RequiredArgsConstructor     // : final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 생성
@NoArgsConstructor             // : 파라미터가 없는 기본 생성자를 생성
public class Member {
    // 클래스에서 변수를 만들때는 private으로 외부에서 직접 접근 불가능하게 만들기
    private int id;
    private String username;
    private String password;
    private String email;

    // @Builder 사용 위해 @AllArgsConstructor 쓰지 않고 생성자 직접 생성
    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

// lombok으로 대체
//    // 생성자
//    public Member(int id, String username, String password, String email) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//    }
//
//    // private 변수를 수정할 수 있게 (상태값을 변경할 수 있게) Getters와 Setters 만들기
//    // 이 함수들을 통해 위의 private 변수들을 수정
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
