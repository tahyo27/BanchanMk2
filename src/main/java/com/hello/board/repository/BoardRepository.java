package com.hello.board.repository;

import com.hello.board.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll();
}
