package com.april2nd.homeshow.model.service;

import com.april2nd.homeshow.model.BookmarkDto;
import com.april2nd.homeshow.model.MemberDto;

import java.util.List;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;

	public MemberDto userInfo(String userid) throws Exception;

	public void saveRefreshToken(String userid, String refreshToken) throws Exception;

	public Object getRefreshToken(String userid) throws Exception;

	public void deleRefreshToken(String userid) throws Exception;

	public boolean joinUser(MemberDto memberDto) throws Exception;

	public boolean deleteUser(String userid) throws Exception;

	public List<BookmarkDto> getBookmarks(String userId) throws Exception;

	public List<BookmarkDto> newBookmark(String userId, String aptCode) throws Exception;

	public List<BookmarkDto> deleteBookmark(String userId, String aptCode) throws Exception;

	public boolean modifyUser(MemberDto memberDto) throws Exception;
}