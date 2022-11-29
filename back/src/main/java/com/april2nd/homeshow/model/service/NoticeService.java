package com.april2nd.homeshow.model.service;

import com.april2nd.util.PageNavigation;
import com.april2nd.homeshow.model.BoardParameterDto;
import com.april2nd.homeshow.model.NoticeDto;

import java.util.List;

public interface NoticeService {
    public boolean writeNotice(NoticeDto noticeDto) throws Exception;
    public List<NoticeDto> listNotice(BoardParameterDto boardParameterDto) throws Exception;
    public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;

    public NoticeDto getNotice(int noticeno) throws Exception;
    public void updateHitNotice(int noticeno) throws Exception;
    public boolean modifyNotice(NoticeDto NoticeDto) throws Exception;
    public boolean deleteNotice(int noticeno) throws Exception;
}
