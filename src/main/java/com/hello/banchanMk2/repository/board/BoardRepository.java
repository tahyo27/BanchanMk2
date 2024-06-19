package com.hello.banchanMk2.repository.board;

import com.hello.banchanMk2.common.Paging;
import com.hello.banchanMk2.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll(Paging paging);
    Optional<Board> findByBoardNum(Integer num);
    void deleteBoard(Integer num);
    Integer boardCount();
}
