package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 MySQL 테이블에 생성됨
        // ORM - JAVA의 Object를 테이블로 매핑해주는 기술
//@DynamicInsert      // insert할때 null 인 필드 제외시켜줌
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 제작된 DB의 넘버링을 따라감
    private int id; // 시퀀스, auto_increment


    /* 사용자로부터 username, password, email 값만 받으면 됨. 나머지는 자동 입력 */
    @Column(nullable = false, length = 30)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 123456 => 해쉬 (비밀번호 암호화, 넉넉하게 크기 잡아두기)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("'user'") // 디폴트값으로 user가 저장됨. 양 옆으로 작은 따옴표를 줘서 문자라는 것을 알려줘야함
    @Enumerated(EnumType.STRING)    // DB는 RoleType이란게 없음. 이렇게 어노테이션붙여 해당 enum이 String임을 알려줘야함.
    private RoleType role; // Enum을 쓰는게 좋음. // admin, user, manager -> 도메인(프로그래밍에서 도메인은 범위를 의미)

    @CreationTimestamp // 값을 비워두고 insert해도 시간이 자동 입력
    private Timestamp createDate;

}