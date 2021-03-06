package com.bbs.dao;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bbs.vo.Authmail;
import com.bbs.vo.Users;

@Repository // 저장소를 접근하는 역할을 인식하게 함.
public class UsersDAOImpl implements UsersDAO {

	// ※ sqlSession : 데이터베이스 접속
	
	@Inject
	SqlSession sqlSession;
	
	final String SESSION = "com.bbs.mappers.bbs";
	
	@Override
	public String idCheck(String user_id) throws Exception {
		
		// 받아 오는 값이 하나면 selectOne
		return sqlSession.selectOne(SESSION + ".idCheck", user_id);
		// selectOne("com.bbs.mappers.bbs.[id값]", 전달받을 값);
		// mapper nameSpace : com.bbs.mappers.bbs.
		
	}

	@Override
	public Integer getAuthnum(String user_mail) throws Exception {	
		return sqlSession.selectOne(SESSION + ".getAuthnum", user_mail);
	}

	@Override
	public void setAuthnum(Authmail authmail) throws Exception {
		sqlSession.insert(SESSION + ".setAuthnum", authmail);		
	}
	
	@Override
	public void resetAuthnum(Authmail authmail) throws Exception {
		sqlSession.update(SESSION + ".resetAuthnum", authmail);
	}

	@Override
	public void deleteAuthmail(String user_mail) throws Exception {
		// 세션을 받아온다.삭제 작업을 할 것이다.(세션에 접속해서 + "아이디 값을 찾아가고", 전달할 값);
		sqlSession.delete(SESSION + ".deleteAuthmail", user_mail);
	}

	@Override
	public void join(Users users) throws Exception {
		sqlSession.insert(SESSION + ".join", users);
	}

	@Override
	public Users login(Users users) throws Exception {
		return sqlSession.selectOne(SESSION + ".login", users);
		// 			└── 요구하는 것은 "하나"의 Object 값이다. 여러 개의 값을 들고 올 수 있는 방법은 ↓
		// 					1. Bean 객체	--> 	하나의 객체로 만들어서 여러 개의 값을 들고 와야한다.	--> 간단함
		//					2. Hashmap	--> 	{"이름" : "실제 데이터", "이름" : "실제 데이터"}	--> 방법이 복잡함.
	}

}
