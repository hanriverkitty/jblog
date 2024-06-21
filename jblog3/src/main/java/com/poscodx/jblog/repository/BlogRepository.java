package com.poscodx.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getBlogVo(String id) {
		return sqlSession.selectOne("blog.getBlog",id);
	}

	public List<CategoryVo> getCategory(String id) {
		return sqlSession.selectList("blog.getCategory",id);
	}
	
	public List<PostVo> getPostByCategory(int no){
		return sqlSession.selectList("blog.getPostByCategory",no);
	}
	
	public PostVo getPostByCategoryAndNo(int no, int categoryNo) {
		return sqlSession.selectOne("blog.getPostByCategoryAndNo",Map.of("no",no,"categoryNo",categoryNo));
	}
}
