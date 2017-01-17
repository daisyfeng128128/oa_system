package com.baofeng.oa.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.ElectEquipentBean;
import com.baofeng.oa.bean.OfficeSuppliesBean;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.ElectEquipent;
import com.baofeng.oa.entity.OfficeSupplies;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPurchaseAuditService;
import com.baofeng.oa.service.IPurchaseService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能 ： 采购审核
 * */
@Controller
@RequestMapping("/purchaseAudit")
public class PurchaseAuditController extends BaseController {

	@Autowired
	private IPurchaseAuditService purchaseAuditService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IPurchaseService purchaseService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(Integer branchs, Integer purType,Integer mId,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS+ "/electAduit");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request,
				String.valueOf("purchaseAudit/show.do"));
		branchs = branchs == null ? super.getSessionAttribute(request,
				Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		purType = purType == null ? 2 : purType;
		BranchOffice boffice = this.branchOfficeService
				.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("purType", purType);
		mav.addObject("mId", mId);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	@RequestMapping(value = "/showCh", method = RequestMethod.GET)
	public ModelAndView showCh(Integer branchs, Integer purType,Integer mId,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS
				+ "/officeAduit");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request,
				String.valueOf("purchaseAudit/show.do"));
		branchs = branchs == null ? super.getSessionAttribute(request,
				Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		purType = purType == null ? 1 : purType;
		BranchOffice boffice = this.branchOfficeService
				.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("attsList", attsList);
		mav.addObject("mId", mId);
		mav.addObject("purType", purType);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/**
	 * 功能：获取头部数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readBaseTabCommon", method = RequestMethod.POST)
	public ResultMsg readBaseTabCommon(HttpServletRequest request, @RequestBody ElectEquipentBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (bean.getId() != null) {
			if (bean.getPurType() == 2) {
				ElectEquipent electEquipent = this.purchaseService.readElectEquipent(bean.getId());
				String auditMsg = "";
					if (electEquipent != null) {
						auditMsg = String.valueOf("待审核");
						switch (electEquipent.getAuditp()) {
						case PENDING:
							auditMsg = String.valueOf("待审核");
							break;
						case UNKNOWN:
							auditMsg = String.valueOf("审核中");
							break;
						case NOTPASS:
							String failure = String.valueOf("");
							if (electEquipent != null && electEquipent.getRejectMsg() != null)
								failure = electEquipent.getRejectMsg();
							auditMsg = String.valueOf("拒绝原因:" + failure);
							break;
						case PASSED:
							auditMsg = String.valueOf("通过");
							break;
						default:
							break;
						}
						msg.setExData(electEquipent);
					}
				msg.setResultMessage(auditMsg);
				msg.setResultStatus(200);
			}
		} 
		return msg;
	}
	
	/**
	 * 功能：获取头部数据办公用品
	 * */
	@ResponseBody
	@RequestMapping(value = "/readBaseTabCommonOff", method = RequestMethod.POST)
	public ResultMsg readBaseTabCommon(HttpServletRequest request, @RequestBody OfficeSuppliesBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (bean.getId() != null) {
			 if(bean.getPurType() == 1){
				OfficeSupplies officeSupplies = this.purchaseService.readOfficeSupplies(bean.getId());
				String auditMsg = "";
					if (officeSupplies != null) {
						auditMsg = String.valueOf("待审核");
						switch (officeSupplies.getAuditp()) {
						case PENDING:
							auditMsg = String.valueOf("待审核");
							break;
						case UNKNOWN:
							auditMsg = String.valueOf("审核中");
							break;
						case NOTPASS:
							String failure = String.valueOf("");
							if (officeSupplies != null && officeSupplies.getRejectMsg() != null)
								failure = officeSupplies.getRejectMsg();
							auditMsg = String.valueOf("拒绝原因:" + failure);
							break;
						case PASSED:
							auditMsg = String.valueOf("通过");
							break;
						default:
							break;
						}
					msg.setExData(officeSupplies);
					}
				msg.setResultMessage(auditMsg);
				msg.setResultStatus(200);
			}
		} 
		return msg;
	}

	
	/**
	 * 功能：分页数据 日常用品
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPagesR", method = RequestMethod.POST)
	public NPageResult readPagesR(int pageSize, int curPage, String sortName,String sortOrder, Integer type, Integer id, Integer branchs,
			String fastArg,HttpServletRequest request,String classQuery) {
		NPageResult pages = null;
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(
				Constants.CURRENT_USER);
		if (users != null) {
			pages = this.purchaseAuditService.readPagesR(pageSize, curPage,sortName, sortOrder, type, id,fastArg,
					super.searchFilter(request,branchs), classQuery);
		}
		return pages;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName,String sortOrder, String fastArg,Integer type, Integer id, Integer branchs,
			HttpServletRequest request,String classQuery) {
		NPageResult pages = null;
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(
				Constants.CURRENT_USER);
		if (users != null) {
			pages = this.purchaseAuditService.readPages(pageSize, curPage,sortName, sortOrder, type,fastArg, id,super.searchFilter(request,branchs),classQuery);
		}
		return pages;
	}

	/**
	 * 功能 ： 部门通过
	 * */
	@ResponseBody
	@RequestMapping(value = "/deptpass", method = RequestMethod.POST)
	public ResultMsg deptPass(String id,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}
		if (this.purchaseAuditService.updatedeptPass(list, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能 ： 设备人员通过
	 * */
	@ResponseBody
	@RequestMapping(value = "/equipass", method = RequestMethod.POST)
	public ResultMsg equiPass(String id,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}		
		if (this.purchaseAuditService.updateequiPass(list, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能 ： 负责人通过
	 * */
	@ResponseBody
	@RequestMapping(value = "/personpass", method = RequestMethod.POST)
	public ResultMsg personPass(String id,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}
		if (this.purchaseAuditService.updatepersonPass(list, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能 ：总公司 通过
	 * */
	@ResponseBody
	@RequestMapping(value = "/headpass", method = RequestMethod.POST)
	public ResultMsg headPass(String id,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}
		if (this.purchaseAuditService.updateheadPass(list, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能 ：开始处理
	 * */
	@ResponseBody
	@RequestMapping(value = "/startEl", method = RequestMethod.POST)
	public ResultMsg startEl(String id,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}
		if (this.purchaseAuditService.updatestartEl(list, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能 ：完成处理
	 * */
	@ResponseBody
	@RequestMapping(value = "/endEl", method = RequestMethod.POST)
	public ResultMsg endEl(@RequestBody ElectEquipentBean bean,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(
				Constants.CURRENT_USER);
		if (this.purchaseAuditService.updatendEl(bean, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能 ：办公用品 部门通过
	 * */
	@ResponseBody
	@RequestMapping(value = "/deptpassOff", method = RequestMethod.POST)
	public ResultMsg deptPassOff(String id,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}		
			if (this.purchaseAuditService.updatedeptPassOff(list, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能 ： 负责人通过
	 * */
	@ResponseBody
	@RequestMapping(value = "/personpassOff", method = RequestMethod.POST)
	public ResultMsg personPassOff(String id,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}		
		if (this.purchaseAuditService.updatepersonPassOff(list, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能 ：总公司 通过
	 * */
	@ResponseBody
	@RequestMapping(value = "/headpassOff", method = RequestMethod.POST)
	public ResultMsg headPassOff(String id,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}				
			if (this.purchaseAuditService.updateheadPassOff(list, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能 ：开始处理
	 * */
	@ResponseBody
	@RequestMapping(value = "/startOff", method = RequestMethod.POST)
	public ResultMsg startOff(String id,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}
		if (this.purchaseAuditService.updatestartOff(list, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能 ：完成处理
	 * */
	@ResponseBody
	@RequestMapping(value = "/endOff", method = RequestMethod.POST)
	public ResultMsg endOff(@RequestBody OfficeSuppliesBean bean,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(
				Constants.CURRENT_USER);
		if (this.purchaseAuditService.updatendOff(bean, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能 ：申请拒绝 办公用品
	 * */
	@ResponseBody
	@RequestMapping(value = "/rejectOff", method = RequestMethod.POST)
	public ResultMsg rejectOff(@RequestBody OfficeSuppliesBean bean,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (this.purchaseAuditService.updateRejOff(bean, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能 ：申请拒绝 电子产品
	 * */
	@ResponseBody
	@RequestMapping(value = "/rejectEl", method = RequestMethod.POST)
	public ResultMsg rejectEl(@RequestBody ElectEquipentBean bean,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (this.purchaseAuditService.updateRejEl(bean, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}
	/**
	 * 功能 ：查看原因El
	 * */
	@ResponseBody
	@RequestMapping(value = "/notPassEl", method = RequestMethod.POST)
	public ResultMsg checkReasonEl(@RequestBody ElectEquipentBean bean,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		ElectEquipent electEquipent = this.purchaseAuditService.checkReason(bean.getId());
		if (electEquipent != null) {
			msg.setExData(electEquipent.getRejectMsg());
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}
	/**
	 * 功能 ：查看原因Off
	 * */
	@ResponseBody
	@RequestMapping(value = "/notPassOff", method = RequestMethod.POST)
	public ResultMsg checkReasonOff(@RequestBody ElectEquipentBean bean,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		OfficeSupplies officeSupplies = this.purchaseAuditService.checkReasonOff(bean.getId());
		if (officeSupplies != null) {
			msg.setExData(officeSupplies.getRejectMsg());
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}
	
	/**
	 * 功能：日期
	 * */
	@ResponseBody
	@RequestMapping(value = "/readbranchs", method = RequestMethod.POST)
	public ResultMsg readDateList(HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Object[] rest = new Object[1];
		List<RoleDetailsAtts> areaList = super.platformsRoleDetails(request, String.valueOf("branchOff/show.do"));
		rest[0] = areaList;
		msg.setData(rest);
		return msg;
	}
	/**
	 * 功能: El
	 * */
	@ResponseBody
	@RequestMapping(value = "/checkEl", method = RequestMethod.POST)
	public ResultMsg notPassEL(HttpServletRequest request,@RequestBody ElectEquipentBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		ElectEquipent electEquipent = this.purchaseService.readElectEquipent(bean.getId());
		if (electEquipent != null) {
			msg.setExData(electEquipent.getReason());
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}
	/**
	 * 功能:审核进度Off
	 * */
	@ResponseBody
	@RequestMapping(value = "/checkOff", method = RequestMethod.POST)
	public ResultMsg notPassOff(HttpServletRequest request,@RequestBody OfficeSuppliesBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		OfficeSupplies officeSupplies = this.purchaseAuditService.checkReasonOff(bean.getId());
		if (officeSupplies != null) {
			msg.setExData(officeSupplies.getReason());
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

}
