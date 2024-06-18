package com.hello.banchanMk2.service.board;

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

    @Override
    public List<Board> pagedAll(int pageNumber, int pageSize) {
        log.info("pageAll pageNumber : " + pageNumber + " pageSize : " + pageSize);
        Sort sort = Sort.by(Sort.Direction.DESC, "boardNum");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        log.info(pageable.toString());
        return boardRepository.pagedFindAll(pageable);
    }

    @Override
    public Page<Board> pageTestAll(Pageable pageable) {
        return boardRepository.pageTest(pageable);
    }


}
