package tobe.project.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import tobe.project.domain.SearchCriteria;
import tobe.project.dto.FileVO;
import tobe.project.dto.MemberVO;
import tobe.project.util.FileUtils;

@Repository
public class AdminDAOImpl implements AdminDAO{
	@Inject
	private SqlSession sqlSession;
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	private static final String Namespace = "tobe.project.mapper.adminMapper";
	
	
	@Override
	public List<MemberVO> selectAllMember() throws Exception {
		return sqlSession.selectList(Namespace+".selectAllMember");
	}
	@Override
	public int addMember(MemberVO vo) throws Exception{
		return sqlSession.insert(Namespace+".addMember", vo);
	}
	@Override
	public MemberVO selectOneMember(int tidx) throws Exception {
		return sqlSession.selectOne(Namespace+".selectOneMember", tidx);
	}
	@Override
	public int modifyMember(MemberVO vo) throws Exception {
		return sqlSession.update(Namespace+".modifyMember", vo);
	}
	@Override
	public int totalCountMember(String keyword) throws Exception {
		return sqlSession.selectOne(Namespace+".totalCountMember",keyword);
	}
	@Override
	public List<MemberVO> searchMemberList(SearchCriteria searchCriteria) throws Exception {
		return sqlSession.selectList(Namespace+".searchMemberList",searchCriteria);
	}
	@Override
	public int totalCountsearchMember(SearchCriteria searchCriteria) throws Exception {
		return sqlSession.selectOne(Namespace+".totalCountsearchMember",searchCriteria);
	}
	@Override
	public void addFile(Map<String, Object> map) throws Exception {
		sqlSession.insert(Namespace+".addFile",map);
	}
	@Override
	public FileVO selectOneFile(int tidx) throws Exception {
		return sqlSession.selectOne(Namespace+".selectOneFile",tidx);
	}
	@Override
	public String selectOneId() throws Exception {
		return sqlSession.selectOne(Namespace+".selectOneId");
	}

}
