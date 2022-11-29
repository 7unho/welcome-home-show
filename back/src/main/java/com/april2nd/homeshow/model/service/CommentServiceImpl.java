package com.april2nd.homeshow.model.service;

import com.april2nd.homeshow.model.BoardDto;
import com.april2nd.homeshow.model.CommentDto;
import com.april2nd.homeshow.model.mapper.CommentMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private SqlSession sqlSession;
    @Override
    public boolean writeComment(CommentDto commentDto) throws Exception {
        if(commentDto.getContent() == null || commentDto.getWriter() == null) {
            throw new Exception();
        }
        return sqlSession.getMapper(CommentMapper.class).registComment(commentDto) == 1;
    }

    @Override
    public List<BoardDto> getComment(int articleno) throws Exception {
        return sqlSession.getMapper(CommentMapper.class).getComment(articleno);
    }

    @Override
    @Transactional
    public boolean deleteComment(int no) throws Exception {
        sqlSession.getMapper(CommentMapper.class).deleteComment(no);
        return sqlSession.getMapper(CommentMapper.class).deleteComment(no) == 1;
    }

    @Override
    @Transactional
    public boolean modifyComment(CommentDto commentDto) throws Exception {
        return sqlSession.getMapper(CommentMapper.class).modifyComment(commentDto) == 1;
    }
}
