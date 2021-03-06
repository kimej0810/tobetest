package tobe.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tobe.project.dao.EmailDAO;
import tobe.project.domain.SearchCriteria;
import tobe.project.dto.EmailVO;
import tobe.project.util.MailHandler;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Inject
	EmailDAO emailDAO;
	
	@Inject
	JavaMailSender mailSender;
	private static final String FROM_ADDRESS = "tobe202105@gmail.com";
	
	@Override
	public void sendEmail(EmailVO emailVO) {
		try {
			MailHandler mailHandler = new MailHandler(mailSender);
			//받는사람
			mailHandler.setTo(emailVO.getM_addressee());
			//보내는사람
			mailHandler.setFrom(FROM_ADDRESS);
			//제목
			mailHandler.setSubject(emailVO.getM_title());
			//HTML Layout
			String htmlContent = "<div style='width:400px;height:400px;margin:auto;border:1px solid black;'>"+emailVO.getM_content()+"</div>";
			mailHandler.setText(htmlContent, true);
			//첨부파일
			//mailHandler.setAttach("test.txt", "file/test.txt");
			//이미지삽입
			//mailHandler.setInline("profile.png", "file/profile.png");
			mailHandler.send();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public int writeEmail(EmailVO emailVO) throws Exception {
		return emailDAO.writeEmail(emailVO);
	}
	@Override
	public int totalCountEmail(String keyword) throws Exception {
		return emailDAO.totalCountEmail(keyword);
	}
	@Override
	public List<EmailVO> searchEmailList(SearchCriteria searchCriteria) throws Exception {
		return emailDAO.searchEmailList(searchCriteria);
	}
	@Override
	public int totalCountsearchEmail(SearchCriteria searchCriteria) throws Exception {
		return emailDAO.totalCountsearchEmail(searchCriteria);
	}
	@Override
	public EmailVO selectOneEmail(int midx) throws Exception {
		return emailDAO.selectOneEmail(midx);
	}
}
