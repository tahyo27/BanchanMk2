package com.hello.banchanMk2.controller.board;

import com.hello.banchanMk2.common.PageViewInfo;
import com.hello.banchanMk2.domain.board.Board;
import com.hello.banchanMk2.service.board.BoardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

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
        log.info(boards.toString());
        model.addAttribute("boards", boards);
        log.info("list call");
        return "/boards/boardList"; // View의 이름인 boardList.html을 반환
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("board", new Board()); // 폼 초기화
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

    @GetMapping("/paged")
    public String paged(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, Model model) {
        log.info("pageNum : " + pageNum + "pageSize" + pageSize);
        List<Board> boards = boardService.pagedAll(pageNum, pageSize);
        model.addAttribute("boards", boards);
        return "/boards/boardList";
    }

    @GetMapping("/test")
    public String pagedTest(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                            Model model) {
        log.info("pageNum : " + pageNum + " pageSize: " + pageSize);
        pageNum = pageNum < 1 ? 1 : pageNum;
        Pageable pageable = PageRequest.of(pageNum -1, pageSize);
        Page<Board> boardPage = boardService.pageTestAll(pageable);
        PageViewInfo pageViewInfo = new PageViewInfo(pageNum, (int) boardPage.getTotalElements(), pageSize,  5);
        log.info(pageViewInfo.toString());
        model.addAttribute("boards", boardPage.getContent());
        model.addAttribute("paging", pageViewInfo);
        return "/boards/boardListTest";
    }
}