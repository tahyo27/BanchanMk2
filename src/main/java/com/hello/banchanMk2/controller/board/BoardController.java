package com.hello.banchanMk2.controller.board;

import com.hello.banchanMk2.domain.board.Board;
import com.hello.banchanMk2.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardService.findBoards();
        model.addAttribute("boards", boards);
        log.info("list call");
        return "/boards/boardList"; // View의 이름인 boardList.html을 반환
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("board", new Board());
        return "/boards/boardForm"; // View의 이름인 boardForm.html을 반환
    }

    @GetMapping("/list/{num}")
    public String select(@PathVariable("num") int num, Model model) {
        log.info("list num call : " + num);
        Optional<Board> findBoard = boardService.findOne(num);
        if (findBoard.isPresent()) {
            model.addAttribute("board", findBoard.get());
        } else {
            model.addAttribute("errorMessage", "Board not found");
        }
        return "/boards/boardSelect";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Board board) {
        board.setRegDate(new Timestamp(System.currentTimeMillis())); // 현재 시간을 등록 날짜로 설정
        board.setViewCount(0); // 처음 등록할 때 조회수 0으로 설정
        boardService.join(board);
        return "redirect:/boards/list"; // 목록 페이지로 리다이렉트
    }
}