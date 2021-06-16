package tobe.project.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import tobe.project.dto.LoginDTO;
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
	public MemberVO selectOneMemberIdx(int tidx) throws Exception {
		return sqlSession.selectOne(Namespace+".selectOneMemberIdx", tidx);
	}
	@Override
	public List<MemberVO> searchMember(MemberVO vo) throws Exception {
		return sqlSession.selectList(Namespace+".searchMember",vo);
	}
	////////////////[06-15]////////
	//로그인
	@Override
	public MemberVO login(LoginDTO dto) throws Exception {
		return sqlSession.selectOne(Namespace+".login", dto);
	}
	//비밀번호 변경
	@Override
	public int modifyPwd(MemberVO vo) throws Exception {
		return sqlSession.update(Namespace+".modifyPwd", vo);
	}
	@Override
	public MemberVO selectOneEmail(String t_email) throws Exception {
		return sqlSession.selectOne(Namespace+".selecOneEamil", t_email);
	}
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		sqlSession.update(Namespace+".memberUpdate", vo);
	}
	@Override
	public MemberVO selectOneMemberId(String t_id)  throws Exception{
		return sqlSession.selectOne(Namespace+".selectOneMemberId",t_id);
	}
	@Override
	public int checkId(String id) throws Exception{
		return sqlSession.selectOne(Namespace+".checkId", id);
	}
}
