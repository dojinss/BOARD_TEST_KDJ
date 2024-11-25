package com.kdj.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdj.board.domain.Board;
import com.kdj.board.domain.Page;
import com.kdj.board.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BaordService{


    @Autowired
    private BoardMapper boardMapper;

    @Override
    public int insert(Board board) throws Exception {
        return boardMapper.insert(board);
    }

    @Override
    public List<Board> list(Page page) throws Exception {
        return boardMapper.list(page);
    }

    @Override
    public Board select(Long no) throws Exception {
        return boardMapper.select(no);
    }

    @Override
    public int update(Board board) throws Exception {
        return boardMapper.update(board);
    }

    @Override
    public int delete(Long no) throws Exception {
        return boardMapper.delete(no);
    }
    
    @Override
    public int total() throws Exception {
        log.info("게시물 총 개수 : " + boardMapper.total());
        return boardMapper.total();
    }
    
}
