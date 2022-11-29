package com.april2nd.homeshow.model;

public class CommentDto {
    private int no;
    private int articleno;
    private String content;
    private String regtime;
    private String writer;

    @Override
    public String toString() {
        return "CommentDto{" +
                "no=" + no +
                ", articleno=" + articleno +
                ", content='" + content + '\'' +
                ", regtime='" + regtime + '\'' +
                ", writer='" + writer + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
