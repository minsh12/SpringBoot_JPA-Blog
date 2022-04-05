package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob    // 대용량 데이터시 사용
    private String content;     // 섬머노트 라이브러리 사용할 예정. <html>태그가 섞여서 디자인됨.

    @ColumnDefault("0")
    private int count;  // 조회수

    @ManyToOne(fetch = FetchType.EAGER)  // Board = Many, User = One. 한명의 user는 여러개의 게시글을 쓸 수 O
    @JoinColumn(name="userId")     // 실제 DB에는 필드가 userId란 이름으로 생성됨
    private User user; // 게시글 작성자
                        // DB는 object를 저장할 수 X. so, FK를 사용. -> ORM 사용전에는 private int userId;로 썼어야했음
                        // 자바같은 객체지향프로그램에서는 object를 저장할 수 O -> ORM인 JPA를 사용하면 Object로 저장해도 DB 괜찮음

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)  // 하나의 게시글은 여러개의 답변 가질 수 O
                                    // mappedBy - 연관관계의 주인이 아님 (얜 FK가 아님) -> DB에 컬럼을 만들지 X
                                    // board를 select 할때 join문을 통해 값을 얻기위한 것. FK키는 Board테이블에 존재
    private List<Reply> reply;

    @CreationTimestamp     // 데이터가 insert, update될 때 현재시간값이 자동으로 들어감
    private Timestamp createDate;
}
