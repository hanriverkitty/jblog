package com.poscodx.jblog.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;

	
	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert",vo);
	}

	public UserVo getUser(String id, String password) {
		return sqlSession.selectOne("user.getUser",Map.of("id",id,"password",password));
	}
	
	public UserVo getUser(String id) {
		return sqlSession.selectOne("user.getUserById",id);
	}

	public int makeBlog(UserVo vo) {
		return sqlSession.insert("user.makeBlog",vo.getId());
	}

	public int setUpCategory(CategoryVo vo) {
		return sqlSession.insert("user.setUpCategory",vo);	
	}

	public void setUpPost(int categoryNo) {
		sqlSession.insert("user.setUpPost",categoryNo);
	}
	
}
