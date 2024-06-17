package com.hello.banchanMk2.repository.board;

import com.hello.banchanMk2.domain.board.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll();
    Optional<Board> findByBoardNum(int num);

}
