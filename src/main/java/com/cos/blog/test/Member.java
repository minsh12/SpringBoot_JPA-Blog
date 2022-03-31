package com.cos.blog.test;

public class Member {

    // 클래스에서 변수를 만들때는 private으로 외부에서 직접 접근 불가능하게 만들기
    private int id;
    private String username;
    private String password;
    private String email;

    // 생성자
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // private 변수를 수정할 수 있게 (상태값을 변경할 수 있게) Getters와 Setters 만들기
    // 이 함수들을 통해 위의 private 변수들을 수정
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
