package com.hello.banchanMk2.service.board;

import com.hello.banchanMk2.domain.board.Board;
import com.hello.banchanMk2.repository.board.BoardRepository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Board> findOne(Integer boardNum) {
        return boardRepository.findByBoardNum(boardNum);
    }

    @Override
    public void deleteBoardByNum(Integer boardNum) {
        boardRepository.deleteBoard(boardNum);
    }
}
