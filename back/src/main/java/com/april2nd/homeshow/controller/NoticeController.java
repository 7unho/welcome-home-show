package com.april2nd.homeshow.controller;

import com.april2nd.homeshow.model.BoardParameterDto;
import com.april2nd.homeshow.model.NoticeDto;
import com.april2nd.homeshow.model.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/notice")
@Api("공지사항 컨트롤러  API V1")
public class NoticeController {

    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private NoticeService noticeService;

    @ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PostMapping
    public ResponseEntity<String> writeArticle(@RequestBody @ApiParam(value = "게시글 정보.", required = true) NoticeDto noticeDto) throws Exception {
        logger.info("writeArticle - 호출");
        if (noticeService.writeNotice(noticeDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
    @GetMapping
    public ResponseEntity<List<NoticeDto>> listArticle(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) throws Exception {
        logger.info("listArticle - 호출");
        return new ResponseEntity<List<NoticeDto>>(noticeService.listNotice(boardParameterDto), HttpStatus.OK);
    }

    @ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = NoticeDto.class)
    @GetMapping("/{noticeno}")
    public ResponseEntity<NoticeDto> getArticle(@PathVariable("noticeno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int noticeno) throws Exception {
        logger.info("getArticle - 호출 : " + noticeno);
        noticeService.updateHitNotice(noticeno);
        return new ResponseEntity<NoticeDto>(noticeService.getNotice(noticeno), HttpStatus.OK);
    }

    @ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PutMapping
    public ResponseEntity<String> modifyArticle(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) NoticeDto noticeDto) throws Exception {
        logger.info("modifyArticle - 호출 {}", noticeDto);

        if (noticeService.modifyNotice(noticeDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @DeleteMapping("/{noticeno}")
    public ResponseEntity<String> deleteArticle(@PathVariable("noticeno") @ApiParam(value = "살제할 글의 글번호.", required = true) int noticeno) throws Exception {
        logger.info("deleteArticle - 호출");
        if (noticeService.deleteNotice(noticeno)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
}