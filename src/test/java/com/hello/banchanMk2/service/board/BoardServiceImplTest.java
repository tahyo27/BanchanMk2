package com.hello.banchanMk2.service.board;

import com.hello.banchanMk2.BanchanMk2Application;
import com.hello.banchanMk2.domain.board.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = BanchanMk2Application.class)
class BoardServiceImplTest {
    @Autowired
    BoardService boardService;

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

    @Test
    void 보드선택() throws Exception {
        //given
        Optional<Board> boardOptional;
        //when
        boardOptional = boardService.findOne(2);

        //then
        if (boardOptional.isPresent()) {
            Assertions.assertThat(boardOptional.get().getWriter()).isEqualTo("김");
        } else {
            System.out.println("null임");
        }

    }

}