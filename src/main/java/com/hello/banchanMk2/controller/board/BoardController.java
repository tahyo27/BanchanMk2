package com.hello.banchanMk2.controller.board;

import com.hello.banchanMk2.common.Paging;
import com.hello.banchanMk2.domain.board.Board;
import com.hello.banchanMk2.service.board.BoardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("board", new Board()); // 폼 초기화
        return "/boards/boardForm"; // View의 이름인 boardForm.html을 반환
    }

    @GetMapping("/list")
    public String pagedTest(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                            Model model) {
        log.info("pageNum : " + pageNum + " pageSize: " + pageSize);

        int count = boardService.countBoard();
        log.info("board count" + count);
        Paging paging = new Paging(pageNum, count, pageSize, 10);
        List<Board> boardPage = boardService.findBoards(paging);
        log.info(paging.toString());
        model.addAttribute("boards", boardPage);
        model.addAttribute("paging", paging);

        return "/boards/boardList";
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

        boardService.join(board);
        return "redirect:/boards/list"; // 목록 페이지로 리다이렉트
    }

    @GetMapping("/delete/{num}")
    public String delete(@PathVariable("num") int num) {
        log.info("list num call : " + num);
        try {
            boardService.deleteBoardByNum(num);
        } catch (EntityNotFoundException e) {
           log.info(e.toString());
        }

        return "redirect:/";
    }

}