package com.hello.board.service;

import com.hello.board.domain.Board;
import com.hello.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public List<Board> findBoards() {
        return boardRepository.findAll();
    }
}
