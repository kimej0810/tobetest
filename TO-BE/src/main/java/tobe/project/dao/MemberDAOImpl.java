package tobe.project.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import tobe.project.dto.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "tobe.project.mapper.memberMapper";
	@Override
	public List<MemberVO> selectAllMember() throws Exception{
		return sqlSession.selectList(Namespace+".selectAllMember");
	}
	//부서별사원리스트
	@Override
	public List<MemberVO> searchDepartmentMember(String t_department) throws Exception{
		return sqlSession.selectList(Namespace+".searchDepartmentMember",t_department);
	}
	@Override
	public MemberVO selectOneMember(int tidx) throws Exception {
		return sqlSession.selectOne(Namespace+".selectOneMember", tidx);
	}
	@Override
	public List<MemberVO> searchMember(MemberVO vo) throws Exception {
		return sqlSession.selectList(Namespace+".searchMember",vo);
	}
}
