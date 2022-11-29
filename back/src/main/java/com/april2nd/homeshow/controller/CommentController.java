package com.april2nd.homeshow.controller;

import com.april2nd.homeshow.model.BoardDto;
import com.april2nd.homeshow.model.CommentDto;
import com.april2nd.homeshow.model.service.CommentService;
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

@RestController
@RequestMapping("/comment")
@Api("게시판 컨트롤러  API V1")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "댓글작성", notes = "댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PostMapping
    public ResponseEntity<String> writeComment(@RequestBody @ApiParam(value = "게시글 정보.", required = true) CommentDto commentDto) throws Exception {
        logger.info("writeArticle - 호출");
        if (commentService.writeComment(commentDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "댓글보기", notes = "글번호에 해당하는 댓글의 정보를 반환한다.", response = BoardDto.class)
    @GetMapping("/{articleno}")
    public ResponseEntity<List<BoardDto>> listComment(@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno) throws Exception {
        logger.info("getArticle - 호출 : " + articleno);
        System.out.println("getArticle - 호출 : " + articleno);
        commentService.getComment(articleno);
        return new ResponseEntity<List<BoardDto>>(commentService.getComment(articleno), HttpStatus.OK);
    }

    @ApiOperation(value = "댓글삭제", notes = "댓글번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @DeleteMapping("/{no}")
    public ResponseEntity<String> deleteComment(@PathVariable("no") @ApiParam(value = "삭제할 댓글번호.", required = true) int no) throws Exception {
        System.out.println("deleteArticle - 호출");
        if (commentService.deleteComment(no)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "댓글", notes = "수정할 댓글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PutMapping
    public ResponseEntity<String> modifyComment(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) CommentDto commentDto) throws Exception {
        if (commentService.modifyComment(commentDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
}
