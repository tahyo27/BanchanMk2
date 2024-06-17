package com.hello.board.repository.board;

import com.hello.board.domain.board.Board;

import java.util.List;

public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll();
}
