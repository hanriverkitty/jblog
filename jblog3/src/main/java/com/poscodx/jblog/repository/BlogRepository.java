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
	
	// 카테고리로 글가져오기
	public List<PostVo> getPostByCategoryNo(int no,String id){
		return sqlSession.selectList("blog.getPostByCategory",Map.of("no",no,"id",id));
	}
	
	// 카테고리와 글번호로 글 가져오기
	public PostVo getPostByNoAndCategory(int no, int categoryNo,String id) {
		return sqlSession.selectOne("blog.getPostByNoAndCategory",Map.of("no",no,"categoryNo",categoryNo,"id",id));
	}
	
	// 디폴트로 카테고리 가져오기
	public CategoryVo getDefaultCategory(String id) {
		return sqlSession.selectOne("blog.getDefaultCategory",id);
	}
	// 글번호를 제외한 같은 카테고리 글들 가져오기
	public List<PostVo> getPostsExceptNo(int no, int categoryNo, String id) {
		return sqlSession.selectList("blog.getPostsExceptNo",Map.of("no",no,"categoryNo",categoryNo,"id",id));
	}
}
