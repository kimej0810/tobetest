package tobe.project.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import tobe.project.dto.MemberVO;

@Component("fileUtils")
public class FileUtils {
	//파일 저장될 경로
	private static final String filePath = "D:\\kio\\tobe\\TO-BE\\src\\main\\webapp\\resources\\static\\filefolder\\";
	public List<Map<String, Object>> parseInsertFileInfo(MemberVO memberVO, MultipartHttpServletRequest mpRequest) throws IllegalStateException, IOException{
		Iterator<String> iterator = mpRequest.getFileNames();
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;
		int tidx = memberVO.getTidx();
		String type = "";
		File file = new File(filePath);
		System.out.println("FileUtils  file>>>>>>>>>>>>>>"+file);
		if(file.exists()==false) {
			file.mkdirs();
		}
		while(iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				file = new File(filePath+storedFileName);
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				listMap.put("tidx", tidx);
				System.out.println("tidx>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+tidx);
				listMap.put("F_TYPE", type);
				listMap.put("F_ORG_FILE_NAME", originalFileName);
				listMap.put("F_STORED_FILE_NAME",storedFileName);
				listMap.put("F_FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
			}
		}
		return list;
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-","");
	}
}
