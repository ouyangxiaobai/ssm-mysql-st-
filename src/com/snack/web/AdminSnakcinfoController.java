package com.snack.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.snack.model.Snackinfo;
import com.snack.model.domain.DoSnackType;
import com.snack.service.adminSnackInfoService;
import com.snack.utils.DataTables;
import com.snack.utils.DateUtil;
import com.snack.utils.PageHelp;
import com.snack.utils.ResponseUtil;
import com.snack.utils.TypeUtil;

@Controller
@RequestMapping("/admin/snackinfo/")
public class AdminSnakcinfoController {
	
	@Autowired
	private adminSnackInfoService adminSnackInfoService;
	
	@RequestMapping(value="addAdminSnack")
	@ResponseBody
    public String upload(HttpServletRequest request,
           @RequestParam("file") MultipartFile file,String fileName,Snackinfo snackinfo) throws Exception {
			Date day=new Date();    
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); 
			String sBatch=df.format(day).toString()+String.valueOf((int)(Math.random()*9000+1000));
			snackinfo.setsCreatedate(DateUtil.getCurrentDateStr());
			snackinfo.setState(1);
			snackinfo.setsPictureurl(fileName);
			snackinfo.setsBatch(sBatch);
			adminSnackInfoService.addAdminSnackinfo(snackinfo);
       //如果文件不为空，写入上传路径
       if(!file.isEmpty()) {
           //上传文件路径
           String path = request.getSession().getServletContext().getRealPath("/image/");
           //上传文件名
           String filename = fileName;
           File filepath = new File(path,filename);
           //判断路径是否存在，如果不存在就创建一个
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
	
	
	@RequestMapping("mainToAdminSnackinfo")
	public ModelAndView mainToAdminImg(ModelAndView model){
		model.setViewName("admin/snackinfo/adminSnackinfo");
		List<DoSnackType> allDoType = TypeUtil.getAllDoType();
		model.addObject("typeList", allDoType);
		return model;
	} 

	//商品
	@RequestMapping("adminSnackinfoLimit")
	@ResponseBody
	public DataTables adminOrderLimit(int start,int length,
			String sName,String sType,String startDate,String endDate){
		Map<Object,Object> map=new HashMap<Object, Object>();
			map.put("pageStart",start);
			map.put("pageSize",length);
			map.put("sType",sType);
			map.put("sName",sName);
			map.put("startDate",startDate);
			map.put("endDate",endDate);
		PageHelp<Snackinfo> pageHelp = adminSnackInfoService.selectAdminSnackinfoLimit(map);
		DataTables datatable=new DataTables();
		datatable.setData(pageHelp.getList());
		datatable.setRecordsFiltered(pageHelp.getRecord());
		datatable.setRecordsTotal(pageHelp.getRecord());
		return datatable;
	}
	
	//删除用户
	@RequestMapping("delAdminUserinfo")
	@ResponseBody
	public String delUserinfoById(int sId){
		return ResponseUtil.successToClient(adminSnackInfoService.delAdminSnackinfo(sId));
	}
	
	//加载用户
	@RequestMapping("selectAdminUserinfoBysId")
	@ResponseBody
	public String selectAdminUserinfoBysId(int sId){
		return ResponseUtil.successToClient(adminSnackInfoService.selectAdminUserinfoBysId(sId));
	}
	
	@RequestMapping(value="updateAdminSnack")
	@ResponseBody
    public String updateAdminSnack(HttpServletRequest request,MultipartFile file,String fileName,Snackinfo snackinfo) throws Exception {
       //如果文件不为空，写入上传路径
       if(file !=null) {
    	   snackinfo.setsPictureurl(fileName);
    	   adminSnackInfoService.updateAdminSnackinfo(snackinfo);
           //上传文件路径
           String path = request.getSession().getServletContext().getRealPath("/image/");
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
    	   adminSnackInfoService.updateAdminSnackinfo(snackinfo);
    	   return ResponseUtil.successToClient();
       }

    }
}
