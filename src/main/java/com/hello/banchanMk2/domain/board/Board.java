package com.hello.banchanMk2.domain.board;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Board {

    private int boardNum;
    private String writer;
    private String title;
    private String content;
    private Timestamp regDate;
    private int viewCount;

}
