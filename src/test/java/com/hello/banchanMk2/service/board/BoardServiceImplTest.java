package com.hello.banchanMk2.service.board;

import com.hello.banchanMk2.BanchanMk2Application;
import com.hello.banchanMk2.domain.board.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ContextConfiguration(classes = BanchanMk2Application.class)
class BoardServiceImplTest {
    @Autowired
    EntityManager em;
    @Autowired
    BoardService boardService;

    @Test
    void 등록() throws Exception {
        // given
        Board board = new Board();
        board.setWriter("John Doe");
        board.setTitle("Test Title");
        board.setContent("Test Content");
        board.setRegDate(LocalDateTime.now());
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

    @Test
    public void 삭제() {
        //given
        Board board = new Board();
        em.persist(board);

        //when
        boardService.deleteBoardByNum(board.getBoardNum());

        //then
        Board deleteBoard = em.find(Board.class, board.getBoardNum());
        assertNull(deleteBoard, "널");
    }

    @Test
    public void 삭제예외() {
        //given
        int bdNum = 999;
        String message = "";
        //when
        Throwable exception = assertThrows(EntityNotFoundException.class, ()->{
           boardService.deleteBoardByNum(bdNum);
        });
        //then "Board with num : " + num + " not found"
        Assertions.assertThat(exception.getMessage()).isEqualTo("Board with num : " + bdNum + " not found");
    }

} //class