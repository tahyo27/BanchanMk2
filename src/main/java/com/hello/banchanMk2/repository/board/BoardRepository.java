package com.hello.banchanMk2.repository.board;

import com.hello.banchanMk2.domain.board.Board;

import java.util.List;

public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll();
}
