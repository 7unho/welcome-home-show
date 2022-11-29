package com.april2nd.homeshow.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BoardDto : 공지사항정보", description = "게시글의 상세 정보를 나타낸다.")
public class NoticeDto {
    @ApiModelProperty(value = "글번호")
    private int noticeno;
    @ApiModelProperty(value = "작성자 아이디")
    private String userid;
    @ApiModelProperty(value = "글제목")
    private String subject;
    @ApiModelProperty(value = "글내용")
    private String content;
    @ApiModelProperty(value = "조회수")
    private int hit;
    @ApiModelProperty(value = "작성일")
    private String regtime;

    public int getNoticeno() { return noticeno; }

    public void setNoticeno(int noticeno) { this.noticeno = noticeno; }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    @Override
    public String toString() {
        return "NoticeDto [noticeno=" + noticeno + ", userid=" + userid + ", subject=" + subject + ", content="
                + content + ", hit=" + hit + ", regtime=" + regtime + "]";
    }

}