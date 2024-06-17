package com.hello.board.controller.board;

import com.hello.board.domain.board.Board;
import com.hello.board.service.board.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.List;

@Controller
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
        return "/boards/boardList"; // View의 이름인 boardList.html을 반환
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("board", new Board());
        return "/boards/boardForm"; // View의 이름인 boardForm.html을 반환
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Board board) {
        board.setRegDate(new Timestamp(System.currentTimeMillis())); // 현재 시간을 등록 날짜로 설정
        board.setViewCount(0); // 처음 등록할 때 조회수 0으로 설정
        boardService.join(board);
        return "redirect:/boards/list"; // 목록 페이지로 리다이렉트
    }
}