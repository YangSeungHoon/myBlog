package com.sheep.sh.myblog.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length= 100 ,unique = true)
    private String username;


    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    private String oauth; //null 허용 가능. 카카오 로그인인지 확인하는 변수

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp //시간 자동 입력
    private Timestamp createDate;

}
