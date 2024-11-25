package com.kdj.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdj.board.domain.Board;
import com.kdj.board.domain.Page;
import com.kdj.board.service.BaordService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    // Service 의존성 주입
    @Autowired
    private BaordService baordService;


    /**
     * Create
     */

    // 게시글 등록 페이지
    @GetMapping("/insert")
    public void insert() {
        log.info("[Board] - 게시글 등록 페이지...");
    }
    
    // 게시글 처리
    @PostMapping("/insert")
    public String insertPro(Board board) throws Exception {
        log.info("[BoardPro] - 게시글 등록 처리중...");
        int result = baordService.insert(board);

        if (result > 0) {
            log.info("게시글 등록 성공");
            return "redirect:/board/list";
        } else {
            log.error("게시글 등록 실패..");
            return "redirect:/board/create?error";
        }

    }
    
    /**
     * Read
     */
     
    // 게시글 목록
    @GetMapping("/list")
    public String list(Model model,Page page) throws Exception {
        log.info("[Board] - 게시글 목록 조회...");
        // 페이징 처리
        int total = baordService.total();
        page.setTotal(total);
        List<Board> boardList = baordService.list(page);
        model.addAttribute("page", page);
        model.addAttribute("boardList", boardList);

        return "/board/list";
    }
    

    // 게시글 조회
    @GetMapping("/read/{no}")
    public String read(@PathVariable("no") Long no, Model model) throws Exception {
        log.info("[Board] - 게시글 조회...");
        Board board = baordService.select(no);
        model.addAttribute("board", board);
        return "/board/read";
    }
    
    /**
     * Update
     */
    
    // 게시글 수정 페이지
    @GetMapping("/update/{no}")
    public String update(@PathVariable("no") Long no, Model model) throws Exception {
        log.info("[Board] - 게시글 수정 페이지...");
        Board board = baordService.select(no);
        model.addAttribute("board", board);
        return "board/update";
    }
    
    // 게시글 수정 처리 페이지
    @PostMapping("/update")
    public String updatePro(Board board) throws Exception {
        log.info("[Board] - 게시글 수정 요청...");
        int result = baordService.update(board);
        
        if (result > 0) {
            log.info("수정 성공");
            return "redirect:/board/list";
        } else {
            log.error("수정 실패");
            return "redirect:/board/read?error";
        }
        
    }
    

    /**
     * Delete
     */

    // 게시글 삭제 처리 페이지
    @PostMapping("/delete")
    public String delete(Board board) throws Exception {
        log.info("[Board] - 게시글 삭제 요청...");
        int result = baordService.delete(board.getNo());
        
        if (result > 0) {
            log.info("삭제 성공");
            return "redirect:/board/list";
        } else {
            log.error("삭제 실패");
            return "redirect:/board/list?error";
        }
        
    }
}
