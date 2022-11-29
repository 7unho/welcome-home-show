package com.april2nd.homeshow.model.service;

import com.april2nd.util.PageNavigation;
import com.april2nd.homeshow.model.BoardParameterDto;
import com.april2nd.homeshow.model.NoticeDto;
import com.april2nd.homeshow.model.mapper.NoticeMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public boolean writeNotice(NoticeDto noticeDto) throws Exception {
        if(noticeDto.getSubject() == null || noticeDto.getContent() == null) {
            throw new Exception();
        }
        return sqlSession.getMapper(NoticeMapper.class).writeNotice(noticeDto) == 1;
    }

    @Override
    public List<NoticeDto> listNotice(BoardParameterDto boardParameterDto) throws Exception {
        int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
        boardParameterDto.setStart(start);
        return sqlSession.getMapper(NoticeMapper.class).listNotice(boardParameterDto);
    }

    @Override
    public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception {
        int naviSize = 5;
        PageNavigation pageNavigation = new PageNavigation();
        pageNavigation.setCurrentPage(boardParameterDto.getPg());
        pageNavigation.setNaviSize(naviSize);
        int totalCount = sqlSession.getMapper(NoticeMapper.class).getNoticeTotalCount(boardParameterDto);//총글갯수  269
        pageNavigation.setTotalCount(totalCount);
        int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
        pageNavigation.setTotalPageCount(totalPageCount);
        boolean startRange = boardParameterDto.getPg() <= naviSize;
        pageNavigation.setStartRange(startRange);
        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
        pageNavigation.setEndRange(endRange);
        pageNavigation.makeNavigator();
        return pageNavigation;
    }

    @Override
    public NoticeDto getNotice(int articleno) throws Exception {
        return sqlSession.getMapper(NoticeMapper.class).getNotice(articleno);
    }

    @Override
    public void updateHitNotice(int articleno) throws Exception {
        sqlSession.getMapper(NoticeMapper.class).updateHitNotice(articleno);
    }

    @Override
    @Transactional
    public boolean modifyNotice(NoticeDto noticeDto) throws Exception {
        return sqlSession.getMapper(NoticeMapper.class).modifyNotice(noticeDto) == 1;
    }

    @Override
    @Transactional
    public boolean deleteNotice(int noticeno) throws Exception {
//        sqlSession.getMapper(NoticeMapper.class).deleteMemo(articleno);
        return sqlSession.getMapper(NoticeMapper.class).deleteNotice(noticeno) == 1;
    }
}
