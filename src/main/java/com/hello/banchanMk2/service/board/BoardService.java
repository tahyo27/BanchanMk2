package com.hello.banchanMk2.service.board;

import com.hello.banchanMk2.common.Paging;
import com.hello.banchanMk2.domain.board.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    Board join(Board board);
    List<Board> findBoards(Paging paging);
    Optional<Board> findOne(Integer boardNum);
    void deleteBoardByNum(Integer boardNum);
    Integer countBoard();
}
