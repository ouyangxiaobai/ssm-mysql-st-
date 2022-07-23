package com.snack.web;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.snack.utils.ResponseUtil;

@Controller
@RequestMapping("/admin/img/")
public class AdminImg {
	
	@RequestMapping("mainToAdminImg")
	public String mainToAdminImg(){
		return "admin/Img/adminImg";
	} 
	
	@RequestMapping(value="/upload")
	@ResponseBody
    public String upload(HttpServletRequest request,
           @RequestParam("file") MultipartFile file,String fileName) throws Exception {
       //如果文件不为空，写入上传路径
       if(!file.isEmpty()) {
           //上传文件路径
           String path = request.getSession().getServletContext().getRealPath("/image/trimImg/");
           //上传文件名
           String filename = fileName;
           File filepath = new File(path,filename);
           //判断路径是否存在，如果不存在就创建一个
           filepath.delete();
           if (!filepath.getParentFile().exists()) { 
               filepath.getParentFile().mkdirs();
           }
           //将上传文件保存到一个目标文件当中
           file.transferTo(new File(path + File.separator + filename));
           return ResponseUtil.successToClient();
       } else {
           return ResponseUtil.errorToClient();
       }
    }
}
