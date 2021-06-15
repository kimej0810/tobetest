package tobe.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberFileDAOImpl implements MemberFileDAO{
	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE = "tobe.project.mapper.memberFileMapper";
	
	//첨부파일 추가
	@Override
	public void addFile(String fullName, Integer tidx) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fullName", fullName);
		paramMap.put("tidx",tidx);
		sqlSession.insert(NAMESPACE+".addFile",paramMap);
	}
	//첨부파일 조회
	@Override
	public List<String> selectListFile(Integer tidx) throws Exception {
		return sqlSession.selectList(NAMESPACE+".selectListFile",tidx);
	}
	//첨부파일 수정
	@Override
	public void modifyFile(String fullName, Integer tidx) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fullName", fullName);
		paramMap.put("tidx",tidx);
		sqlSession.insert(NAMESPACE+".modifyFile",paramMap);
	}
	//첨부파일 삭제
	@Override
	public void deleteFile(String fullName, Integer tidx) throws Exception {
		sqlSession.delete(NAMESPACE+".deleteFile",fullName);
	}
}
