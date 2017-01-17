package com.baofeng.oa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.AssetsBean;
import com.baofeng.oa.entity.Assets;
import com.baofeng.oa.entity.BaseEnums.HeadPass;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.service.IAssetsService;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 固定资产审核
 */
@Controller
@RequestMapping("/assetsAudit")
public class AssetsAuditController extends BaseController {

	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IAssetsService assetsService;
	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showAduit(HttpServletRequest request, Integer branchs) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/assetsAudit");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("assetsAudit/show.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesAduit", method = RequestMethod.POST)
	public NPageResult<?> readPagesAduit(int pageSize, int curPage, String sortName, String sortOrder,String branchsMsg ,Integer branchs, HttpServletRequest request) {
		NPageResult<?> pages = this.assetsService.readPagesAduit(pageSize, curPage, sortName, sortOrder,branchsMsg, super.searchFilter(request,branchs));
		return pages;
	}

	/**
	 * 功能 ：通过
	 * */
	@ResponseBody
	@RequestMapping(value = "/headpass", method = RequestMethod.POST)
	public ResultMsg endEl(@RequestBody AssetsBean bean,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (this.assetsService.updatendAssets(bean, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}
	
	/**
	 * 导出excel
	 * */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,String id) throws Exception {
		Assets assets = this.assetsService.readAssetsById(Integer.valueOf(id), null);
		Date thisMonth = assets.getThisMonth();
		DateFormat sd=new SimpleDateFormat("yyyy-MM");
		String thisMonth1 = sd.format(thisMonth);
		String path = Constants.DEFAULT_UPLOADEXCEL+"/Asset/"+thisMonth1;
		File file = new File(path);
		File[] files=file.listFiles();  
		for (int j = 0; j < files.length; j++) {
			if (files[0].isFile()) {
				if (files[0].getName().endsWith(".xls")) {
					File file1 = new File(path+"/"+thisMonth1+".xls");
					response.setContentType("application/msexcel;");             
			        response.setHeader("Content-Disposition", new String(("attachment;filename="+thisMonth1+".xls").getBytes("GB2312"), "UTF-8"));
			        FileInputStream in = new FileInputStream(file1);
			        byte b[] = new byte[1024];
			        int i = 0;
			        ServletOutputStream out = response.getOutputStream();
			        while((i=in.read(b))!=  -1){
			            out.write(b, 0, i);
			        }
			        out.flush();
			        out.close();
			        in.close();
				} else if (files[0].getName().endsWith(".xlsx")) {
					File file1 = new File(path+"/"+thisMonth1+".xlsx");
					response.setContentType("application/msexcel;");             
			        response.setHeader("Content-Disposition", new String(("attachment;filename="+thisMonth1+".xlsx").getBytes("GB2312"), "UTF-8"));
			        FileInputStream in = new FileInputStream(file1);
			        byte b[] = new byte[1024];
			        int i = 0;
			        ServletOutputStream out = response.getOutputStream();
			        while((i=in.read(b))!=  -1){
			            out.write(b, 0, i);
			        }
			        out.flush();
			        out.close();
			        in.close();
				}
			}
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(String remarks, @RequestParam MultipartFile[] upload ,HttpServletRequest request)throws Exception {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/assetsAudit");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("assetsAudit/show.do"));
		mav.addObject("isEdit", true);
		mav.addObject("attsList", attsList);
		final String EXTENSION_XLS = ".xls";
		final String EXTENSION_XLSX = ".xlsx";
		String date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		if (upload != null && upload.length > 0) {
			for (MultipartFile $file : upload) {
				if (!$file.isEmpty()) {
					String $name = $file.getOriginalFilename();
					String ext = $name.substring($name.lastIndexOf("."), $name.length());
					if(ext.endsWith(EXTENSION_XLS) || ext.endsWith(EXTENSION_XLSX)){
						String fileName =date + ext;
						String path = Constants.DEFAULT_UPLOADEXCEL+"/Asset/"+date;
						File myPath = new File(path);  
			            if ( !myPath.exists()){//若此目录不存在，则创建之  
			                myPath.mkdirs();  
			            } 
						FileUtils.copyInputStreamToFile($file.getInputStream(), new File(myPath, fileName));
					}
				}
			}
		}
		DateFormat sdf=new SimpleDateFormat("yyyy-MM");
		Assets post = this.assetsService.readAllassetsByThisMonth(sdf.parse(date));
		if (post != null) {
			post.setRemarks(remarks);
			DateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
			post.setCompleteDT(sd.parse(sd.format(new Date())));
			post.setHeadPass(HeadPass.UNKNOWN);
			if (this.assetsService.addAssets(post, request));
		}else{
			post = new Assets();
			RoleUsers users = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
			Employee employee = this.employeeService.readEmployee(users.getUser().getId());
			Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
			post.setBranchs(branchs);
			post.setThisMonth(sdf.parse(date));
			DateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
			post.setCompleteDT(sd.parse(sd.format(new Date())));
			post.setEmployee(employee);
			post.setHeadPass(HeadPass.UNKNOWN);
			post.setFile("Excel文档");
			post.setRemarks(remarks);
			if (this.assetsService.addAssets(post, request));
		}
		return mav;
	}
	
}
