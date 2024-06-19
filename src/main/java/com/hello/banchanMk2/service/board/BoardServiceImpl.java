package com.hello.banchanMk2.service.board;

import com.hello.banchanMk2.common.Paging;
import com.hello.banchanMk2.domain.board.Board;
import com.hello.banchanMk2.repository.board.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;

@Slf4j
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board join(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public List<Board> findBoards(Paging paging) {
        return boardRepository.findAll(paging);
    }

    @Override
    public Optional<Board> findOne(Integer boardNum) {
        return boardRepository.findByBoardNum(boardNum);
    }

    @Override
    public void deleteBoardByNum(Integer boardNum) {
        boardRepository.deleteBoard(boardNum);
    }

    @Override
    public Integer countBoard() {
        return boardRepository.boardCount();
    }


}
