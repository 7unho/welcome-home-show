package com.april2nd.homeshow.model.service;

import com.april2nd.homeshow.model.BoardDto;
import com.april2nd.homeshow.model.CommentDto;

import java.util.List;

public interface CommentService {
    public boolean writeComment(CommentDto commentDto) throws Exception;
    public List<BoardDto> getComment(int articleno) throws Exception;

    public boolean deleteComment(int no) throws Exception;

    public boolean modifyComment(CommentDto commentDto) throws Exception;
}
