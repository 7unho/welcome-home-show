package com.april2nd.homeshow.model.mapper;

import com.april2nd.homeshow.model.BoardParameterDto;
import com.april2nd.homeshow.model.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface NoticeMapper {

    public int writeNotice(NoticeDto noticeDto) throws SQLException;
    public List<NoticeDto> listNotice(BoardParameterDto boardParameterDto) throws SQLException;
    public int getNoticeTotalCount(BoardParameterDto boardParameterDto) throws SQLException;
    public NoticeDto getNotice(int noticeno) throws SQLException;
    public void updateHitNotice(int noticeno) throws SQLException;
    public int modifyNotice(NoticeDto noticeDto) throws SQLException;
    public int deleteNotice(int noticeno) throws SQLException;

}