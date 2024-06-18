package com.hello.banchanMk2.repository.board;

import com.hello.banchanMk2.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll();
    Optional<Board> findByBoardNum(Integer num);
    List<Board> pagedFindAll(Pageable pageable);
    void deleteBoard(Integer num);

    Page<Board> pageTest(Pageable pageable);
}
