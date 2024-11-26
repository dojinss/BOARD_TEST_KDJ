package com.kdj.board.domain;


import org.springframework.beans.factory.annotation.Autowired;

import com.kdj.board.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class Page {
    
    @Autowired private BoardMapper boardMapper;

    private static final int PAGE_NUM    = 1;    // 현재 페이지
    private static final int ROWS        = 10;   // 페이지당 보여줄 게시글 수
    private static final int COUNT       = 10;   // 페이징에서 보여줄 페이지 개수
    
    private int page;               // 현재 페이지
    private int rows;               // 페이지당 보여줄 게시글 수
    private int count;              // 페이징에서 보여줄 페이지 개수
    private int total;              // 전체 게시글수

    private int start;              // 보여지는 페이징 시작 숫자
    private int end;                // 보여지는 페이징 끝 숫자
    private int first;              // 전체 시작 페이징 숫자 = 1
    private int last;               // 전체 끝 페이징  숫자 = (전체 게시글수 / pageCount)

    private int prev;
    private int next;

    private int index;
    
    public Page() {
        log.info("Page 생성...");
        this.page = PAGE_NUM;
        this.count = COUNT;
        this.rows = ROWS;
        log.info("Page : " + this.page);
        calc();
    }

    public Page(int page) {
        this.page = page;
    }

    public Page(int page, int total) {
        this(page, ROWS, COUNT, total);
    }

    public Page(int page, int rows, int count, int total) {
        this.page = page;
        this.rows = rows;
        this.count = count;
        this.total = total;
        calc();
    }

    // setter
    // * 데이터 계수 지정 후, 페이지 수식 재계산
    public void setTotal(int total) {
        this.total = total;
        calc();
    }

    // 페이징 처리 수식
    public void calc(){
        // 첫번호
        this.first = 1;
        // 마지막번호
        this.last = (this.total - 1) / rows + 1;
        
        // 시작번호
        this.start = ((page - 1) / count ) * count +1;
        // 끝 번호
        this.end = ((page - 1) / count + 1) * count;
        if( this.end > this.last) this.end = this.last;
        
        // 이전 번호
        this.prev = start - 1;
        // 다음 번호
        this.next = end + 1;
        // 데이터 순서 번호
        this.index = (page - 1) * rows;

    }
    
}
