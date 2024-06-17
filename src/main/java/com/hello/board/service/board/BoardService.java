package com.hello.board.service.board;

import com.hello.board.domain.board.Board;

import java.util.List;

public interface BoardService {

    Board join(Board board);
    List<Board> findBoards();
}
