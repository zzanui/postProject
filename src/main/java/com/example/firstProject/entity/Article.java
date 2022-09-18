package com.example.firstProject.entity;


import lombok.*;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
@Getter
@Builder
public class Article {
    
    @Id//대표값
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

    @Column
    private String nickName;

    @Column
//  @CreationTimestamp //컬럼 생성시
    @UpdateTimestamp    //컬럼 업데이트시
    private LocalDate timestamp;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private Integer view;

}



