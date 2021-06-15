package tobe.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tobe.project.domain.SearchCriteria;
import tobe.project.dto.MemberVO;

public interface MemberService {
	public List<MemberVO> selectAllMember() throws Exception;
	public List<MemberVO> searchDepartmentMember(@Param(value="t_department")String t_department) throws Exception;
	public MemberVO selectOneMember(@Param(value="tidx")int tidx) throws Exception;
	public List<MemberVO> searchMember(MemberVO vo)throws Exception;
}
