package tobe.project.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import tobe.project.dto.LoginDTO;
import tobe.project.dto.MemberVO;

public interface MemberService {
	public List<MemberVO> selectAllMember() throws Exception;
	public List<MemberVO> searchDepartmentMember(@Param(value="t_department")String t_department) throws Exception;
	public MemberVO selectOneMemberIdx(@Param(value="tidx")int tidx) throws Exception;
	public List<MemberVO> searchMember(MemberVO vo)throws Exception;
	//로그인
	public MemberVO login(LoginDTO dto) throws Exception;
	//이메일 전송
	public void sendEmail(MemberVO vo, String div) throws Exception;
	//비밀번호 찾기
	public void findPwd(HttpServletResponse response, MemberVO vo) throws Exception;
	public void memberUpdate(MemberVO vo) throws Exception;
}
