package com.hello.board.domain.board;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Board {

    private int boardNum;
    private String writer;
    private String title;
    private String content;
    private Timestamp regDate;
    private int viewCount;

}
