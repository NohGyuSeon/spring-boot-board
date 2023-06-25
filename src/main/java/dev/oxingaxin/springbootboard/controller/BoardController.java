package dev.oxingaxin.springbootboard.controller;


import dev.oxingaxin.springbootboard.dto.BoardCreateRequest;
import dev.oxingaxin.springbootboard.entity.Board;
import dev.oxingaxin.springbootboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 테스트용 메소드 작성
     */
    @ResponseBody
    @GetMapping("/api")
    public List<Board> list() {
        List<Board> boards = boardService.getBoards();
        return boards;
    }

    @GetMapping
    public String list(Model model) {
        List<Board> boards = boardService.getBoards();
        model.addAttribute("boards", boards);
        return "boards" ;
    }

    @PostMapping
    public String write(BoardCreateRequest boardCreateRequest) {
        boardService.createBoard(boardCreateRequest);
        return "redirect:/boards";
    }

    @GetMapping("/add")
    public String addForm() {
        return "addForm" ;
    }

    @GetMapping("/{boardId}")
    public String view(@PathVariable Long boardId, Model model) {
        Board board = boardService.getBoard(boardId);
        model.addAttribute("board", board);
        return "board";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model) {
        Board board = boardService.getBoard(boardId);
        model.addAttribute(board);
        return "editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, @ModelAttribute Board board) {
        boardService.updateBoard(boardId, board);
        return "redirect:/boards/{boardId}";
    }

    @PostMapping("/{boardId}")
    public String delete(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/boards";
    }
}
