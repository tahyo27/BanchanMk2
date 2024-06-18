package com.hello.banchanMk2.service.board;

import com.hello.banchanMk2.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    Board join(Board board);
    List<Board> findBoards();
    Optional<Board> findOne(Integer boardNum);

    void deleteBoardByNum(Integer boardNum);

    List<Board> pagedAll(int pageNumber, int pageSize);

    Page<Board> pageTestAll(Pageable pageable);
}
