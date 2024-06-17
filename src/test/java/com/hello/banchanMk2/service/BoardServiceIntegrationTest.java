package com.hello.banchanMk2.service;

import com.hello.banchanMk2.domain.board.Board;
import com.hello.banchanMk2.repository.board.BoardRepository;
import com.hello.banchanMk2.service.board.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BoardServiceIntegrationTest {

    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @Test
    void 등록() throws Exception {
        // given
        Board board = new Board();
        board.setWriter("John Doe");
        board.setTitle("Test Title");
        board.setContent("Test Content");
        board.setRegDate(new Timestamp(System.currentTimeMillis()));
        board.setViewCount(0);

        // when
        Board savedBoard = boardService.join(board);

        // then
        assertEquals("John Doe", savedBoard.getWriter());
        assertEquals("Test Title", savedBoard.getTitle());
        assertEquals("Test Content", savedBoard.getContent());
        assertEquals(0, savedBoard.getViewCount());
    }
}