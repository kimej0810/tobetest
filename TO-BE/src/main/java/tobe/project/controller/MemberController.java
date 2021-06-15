package tobe.project.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tobe.project.dto.MemberVO;
import tobe.project.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Inject
	private MemberService service;
	
	@RequestMapping(value = "/list")
	public String selectListMember(Locale locale, Model model) throws Exception {
		System.out.println("MemberController");
		List<MemberVO> selectAllMember = service.selectAllMember();
		model.addAttribute("selectAllMember",selectAllMember);
		return "/member/list";
	}
	@RequestMapping(value = "/buseolist")
	@ResponseBody
	public Object searchDepartmentMember(Locale locale, Model model, String t_department) throws Exception {
		if(t_department.equals("전체")) {
			List<MemberVO> memberList = service.selectAllMember();
			model.addAttribute("memberList",memberList);
			return memberList;
		}
		List<MemberVO> searchDepartmentMember = service.searchDepartmentMember(t_department);
		model.addAttribute("searchDepartmentMember",searchDepartmentMember);
		return searchDepartmentMember;
	}
	@RequestMapping(value = "/saoneinfo")
	@ResponseBody
	public Object selectOneMember(Locale locale, Model model, int tidx) throws Exception {
		MemberVO saoneInfo = service.selectOneMember(tidx);
		model.addAttribute("saoneinfo",saoneInfo);
		return saoneInfo;
	}
	@RequestMapping(value = "/insertSaone")
	@ResponseBody
	public Object addMember(Locale locale, Model model, int tidx) throws Exception {
		MemberVO saoneInfo = service.selectOneMember(tidx);
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
		vo.setT_department(department1);
		String result2[] = name.split("=");
		String name1 = result2[1];
		System.out.println(name1);
		vo.setT_name(name1);
		List<MemberVO> searchMember = service.searchMember(vo);
		model.addAttribute("searchMember",searchMember);
		return searchMember;
	}
	
}
