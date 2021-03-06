package tobe.project.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tobe.project.dto.LoginDTO;
import tobe.project.dto.MemberDTO;
import tobe.project.dto.MemberVO;
import tobe.project.service.MemberFileService;
import tobe.project.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Inject
	private MemberService service;
	@Inject
	private MemberFileService fileService;
	
	//06-15 시큐리티
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	@RequestMapping(value = "/list")
	public String selectListMember(Locale locale, Model model) throws Exception {
		System.out.println("MemberController");
		//List<FileVO> selectAllFile = fileService.selectAllFile();
		//List<MemberVO> selectAllMember = service.selectAllMember();
		//model.addAttribute("fileList",selectAllFile);
		//model.addAttribute("selectAllMember",selectAllMember);
		List<MemberDTO> selectAllMember = service.selectAllMember2();
		model.addAttribute("selectAllMember",selectAllMember);
		return "/member/list";
	}
	@RequestMapping(value = "/buseolist")
	@ResponseBody
	public Object searchDepartmentMember(Locale locale, Model model, String t_department) throws Exception {
		if(t_department.equals("all")) {
			//List<MemberVO> searchDepartmentMember = service.selectAllMember();
			List<MemberDTO> searchDepartmentMember = service.selectAllMember2();
			model.addAttribute("searchDepartmentMember",searchDepartmentMember);
			return searchDepartmentMember;
		}
		//List<MemberVO> searchDepartmentMember = service.searchDepartmentMember(t_department);
		List<MemberDTO> searchDepartmentMember = service.searchDepartmentMember(t_department);
		model.addAttribute("searchDepartmentMember",searchDepartmentMember);
		return searchDepartmentMember;
	}
	@RequestMapping(value = "/saoneinfo")
	@ResponseBody
	public Object selectOneMember(Locale locale, Model model, int tidx) throws Exception {
		//MemberVO saoneInfo = service.selectOneMemberIdx(tidx);
		MemberDTO saoneInfo = service.selectOneMemberIdx(tidx);
		model.addAttribute("saoneinfo",saoneInfo);
		return saoneInfo;
	}
	@RequestMapping(value = "/insertSaone")
	@ResponseBody
	public Object addMember(Locale locale, Model model, int tidx) throws Exception {
		//MemberVO saoneInfo = service.selectOneMemberIdx(tidx);
		MemberDTO saoneInfo = service.selectOneMemberIdx(tidx);
		model.addAttribute("saoneinfo",saoneInfo);
		return saoneInfo;
	}
	@RequestMapping(value = "/search")
	@ResponseBody
	public Object searchMember(Model model,MemberVO vo)throws Exception{
		String department = vo.getT_department();
		String name = vo.getT_name();
		String result[] = department.split("=");
		String department1 = result[1];
		System.out.println(department1);
		String result2[] = name.split("=");
		String name1 = result2[1];
		System.out.println(name1);
		if(department1.equals("all")) {
			List<MemberVO> searchMember = service.searchMember2(name1);
			model.addAttribute("searchMember",searchMember);
			return searchMember;
		}
		vo.setT_department(department1);
		vo.setT_name(name1);
		List<MemberVO> searchMember = service.searchMember(vo);
		model.addAttribute("searchMember",searchMember);
		return searchMember;
	}
	//////////////////////////////////////////////////////////////
	//로그인
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
	}
	@RequestMapping(value="/loginPost", method = RequestMethod.POST)
	public String loginPost(LoginDTO dto, HttpSession session, Model model) throws Exception{
		System.out.println("넘어온 비번>>>>>>>>"+dto.getT_pwd());
		MemberVO vo = service.login(dto);
		String rvo = vo.getT_pwd();
		System.out.println("vo>>>>"+rvo);
		String mvo = dto.getT_pwd();
		System.out.println("mvo>>>>"+mvo);
		boolean pwdMatch = false;
		if(vo!=null) {
			pwdMatch = BCrypt.checkpw(mvo, rvo);
			System.out.println("로그인 성공");
			return "/";
		}else{
			System.out.println("로그인 실패");
			pwdMatch = false;
			return "/member/login";
		}

	}
	//비밀번호 찾기
	@RequestMapping(value="/findPwd", method = RequestMethod.GET)
	public void findPwdGET() throws Exception{
	}
	@RequestMapping(value="/findPwd", method = RequestMethod.POST)
	public void findPwdPOST(@ModelAttribute MemberVO vo, HttpServletResponse response) throws Exception{
		service.findPwd(response, vo);
	}
	//사원정보수정
	@RequestMapping(value="/updateMember",method = RequestMethod.POST)
	public String updateMember(MemberVO vo, HttpSession session) throws Exception {
		return "redirect:/";
	}
	//////////////////////////////////////////////////////////////
}
