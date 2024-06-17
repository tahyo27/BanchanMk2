package com.hello.banchanMk2.service.board;

import com.hello.banchanMk2.domain.board.Board;

import java.util.List;

public interface BoardService {

    Board join(Board board);
    List<Board> findBoards();
}
