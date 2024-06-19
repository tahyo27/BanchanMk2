package com.hello.banchanMk2.domain.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "BOARD")
public class Board {
    @Id
    @Column(name = "BOARDNUM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardNum;

    private String writer;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "REGDATE")
    private LocalDateTime regDate;

    @Column(name = "VIEWCOUNT")
    private int viewCount;

    @PrePersist
    public void prePersist() {
        this.regDate = LocalDateTime.now();
    }

}
