package com.april2nd.homeshow.model.mapper;

import com.april2nd.homeshow.model.BoardDto;
import com.april2nd.homeshow.model.CommentDto;

import java.sql.SQLException;
import java.util.List;

public interface CommentMapper {
    public int registComment(CommentDto commentDto) throws SQLException;

    public List<BoardDto> getComment(int articleno) throws SQLException;


    public int deleteComment(int no) throws SQLException;

    public int modifyComment(CommentDto commentDto) throws SQLException;
}
