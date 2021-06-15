package tobe.project.dao;

import java.util.List;

import tobe.project.domain.SearchCriteria;
import tobe.project.dto.MemberVO;

public interface MemberDAO {
	public List<MemberVO> selectAllMember() throws Exception;
	//부서별 사원리스트
	public List<MemberVO> searchDepartmentMember(String t_department) throws Exception;
	public MemberVO selectOneMember(int tidx) throws Exception;
	public List<MemberVO> searchMember(MemberVO vo)throws Exception;
}
