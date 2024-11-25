package com.kdj.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kdj.board.domain.Board;
import com.kdj.board.domain.Page;

@Mapper
public interface BoardMapper {
    
    // Create
    // 게시물 생성
    public int insert(Board board) throws Exception;

    // Read
    // 게시글 목록 조회
    public List<Board> list(@Param("page") Page page) throws Exception;
    
    // 게시글 조회
    public Board select(Long no) throws Exception;
    
    // Update
    // 게시글 수정
    public int update(Board board) throws Exception;

    // Delete
    // 게시글 삭제
    public int delete(Long no) throws Exception;

    // 젠체 게시물 수 조회
    public int total() throws Exception;
}
