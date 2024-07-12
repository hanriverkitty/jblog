package com.poscodx.jblog.repository;

import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
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
		UserVo vo = sqlSession.selectOne("user.getUser",Map.of("id",id,"password",password));
		System.out.println(vo);
		return vo;
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

	public <R> R getUser(String id, Class<R> resultType) {
		GetUserResultHandler<R> getUserResultHandler = new GetUserResultHandler<>(resultType);
		sqlSession.select("user.getUserByIdUsedInLogin",id,getUserResultHandler);
		return getUserResultHandler.result;
	}
	
	private class GetUserResultHandler<R> implements ResultHandler<Map<String,Object>>{
		private R result;
		private Class<R> resultType;
		
		GetUserResultHandler(Class<R> resultType){
			this.resultType = resultType;
		}

		@Override
		public void handleResult(ResultContext<? extends Map<String, Object>> resultContext) {
			Map<String, Object> resultMap = resultContext.getResultObject();
			result = new ObjectMapper().convertValue(resultMap,resultType);
		}
	}	
}
