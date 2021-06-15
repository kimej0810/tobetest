package tobe.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import tobe.project.dao.MemberDAO;
import tobe.project.domain.SearchCriteria;
import tobe.project.dto.MemberVO;

@Repository
public class MemberServiceImpl implements MemberService{
	@Inject
	private MemberDAO dao;
	@Override
	public List<MemberVO> selectAllMember() throws Exception{
		return dao.selectAllMember();
	}
	@Override
	public List<MemberVO> searchDepartmentMember(String t_department)throws Exception{
		return dao.searchDepartmentMember(t_department);
	}
	@Override
	public MemberVO selectOneMember(int tidx) throws Exception {
		return dao.selectOneMember(tidx);
	}
	@Override
	public List<MemberVO> searchMember(MemberVO vo) throws Exception {
		return dao.searchMember(vo);
	}
}
