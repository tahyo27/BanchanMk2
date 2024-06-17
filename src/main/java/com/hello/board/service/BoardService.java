package com.hello.board.service;

import com.hello.board.domain.Board;

import java.util.List;

public interface BoardService {

    Board join(Board board);
    List<Board> findBoards();
}
