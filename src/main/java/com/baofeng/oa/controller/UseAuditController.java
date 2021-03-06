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
import com.baofeng.oa.bean.EmployeeBean;
import com.baofeng.oa.bean.EquipmentCollarBean;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.EquipmentCollar;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IUseApplicationService;
import com.baofeng.oa.service.IUseAuditService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能 ： 领用审核
 * */
@Controller
@RequestMapping("/useAudit")
public class UseAuditController extends BaseController {

	@Autowired
	private IUseAuditService useAuditService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IUseApplicationService useApplicationService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(Integer branchs,Integer mId,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS+ "/useAudit");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request,String.valueOf("useAudit/show.do"));
		branchs = branchs == null ? super.getSessionAttribute(request,Constants.CURRENT_BRANCHS) : branchs;
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
	 * 功能：获取头部数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readBaseTabCommon", method = RequestMethod.POST)
	public ResultMsg readBaseTabCommon(HttpServletRequest request, @RequestBody ElectEquipentBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (bean.getId() != null) {
			EquipmentCollar equipmentCollar = this.useApplicationService.readEquipmentCollar(bean.getId());
				String auditMsg = "";
					if (equipmentCollar != null) {
						auditMsg = String.valueOf("待审核");
						switch (equipmentCollar.getAuditp()) {
						case PENDING:
							auditMsg = String.valueOf("待审核");
							break;
						case UNKNOWN:
							auditMsg = String.valueOf("审核中");
							break;
						case NOTPASS:
							String failure = String.valueOf("");
							if (equipmentCollar != null && equipmentCollar.getRejectMsg() != null)
								failure = equipmentCollar.getRejectMsg();
							auditMsg = String.valueOf("拒绝原因:" + failure);
							break;
						case PASSED:
							auditMsg = String.valueOf("通过");
							break;
						default:
							break;
						}
						msg.setExData(equipmentCollar);
					}
				msg.setResultMessage(auditMsg);
				msg.setResultStatus(200);
		} 
		return msg;
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
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (users != null) {
			pages = this.useAuditService.readPages(pageSize, curPage,sortName, sortOrder, type,fastArg, id,super.searchFilter(request,branchs),classQuery);
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
			if (this.useAuditService.updatedeptPass(list, users, request)) {
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
			if (this.useAuditService.updateequiPass(list, users, request)) {
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
	@RequestMapping(value = "/end", method = RequestMethod.POST)
	public ResultMsg endEl(String id,HttpServletRequest request) {
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
			if (this.useAuditService.updateEnd(list, users, request)) {
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能 ：申请拒绝
	 * */
	@ResponseBody
	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	public ResultMsg reject(@RequestBody EquipmentCollarBean bean,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (this.useAuditService.updateReject(bean, users, request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}
	
	/**
	 * 功能 ：查看原因El
	 * */
	@ResponseBody
	@RequestMapping(value = "/notPass", method = RequestMethod.POST)
	public ResultMsg checkReasonEl(@RequestBody EquipmentCollarBean bean,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (bean.getId() != null) {
			EquipmentCollar equipmentCollar = this.useAuditService.checkReason(bean.getId());
			if (equipmentCollar != null) {
				msg.setExData(equipmentCollar.getRejectMsg());
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能: El
	 * */
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResultMsg notPassEL(HttpServletRequest request,@RequestBody EquipmentCollarBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (bean.getId() != null) {
			EquipmentCollar equipmentCollar = this.useAuditService.checkReason(bean.getId());
			if (equipmentCollar != null) {
				msg.setExData(equipmentCollar.getReason());
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}
	
	/**
	 * 功能: El
	 * */
	@ResponseBody
	@RequestMapping(value = "/checkIssuer", method = RequestMethod.POST)
	public ResultMsg Issuer(HttpServletRequest request,@RequestBody EquipmentCollarBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (bean.getId() != null) {
			EquipmentCollar equipmentCollar = this.useAuditService.checkReason(bean.getId());
			if (equipmentCollar != null) {
				msg.setExData(equipmentCollar.getIssuer());
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能：保存 
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg saveEquipmentCollar(@RequestBody List<EquipmentCollarBean> list, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (this.useApplicationService.saveEquipmentCollar(list, users, branchs, request)) {
			msg.setResultStatus(200);
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/editEmp", method = RequestMethod.GET)
	public ResultMsg readUser( HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
/*		Employee emp = this.employeeService.readEmployeeByNumber(users.getUser().getNumber());
*/		Employee emp = this.employeeService.readEmployee(users.getUser().getId());
		EmployeeBean employeeBean = new EmployeeBean();
		if (emp != null) {
			employeeBean.setId(emp.getId());
			employeeBean.setName(emp.getName());
			employeeBean.setNum(String.valueOf(emp.getNumber()));
			if (emp.getDepart().getName() != null) {
				employeeBean.setDepName(emp.getDepart().getName());
			}
			msg.setData(employeeBean);
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}else{
			employeeBean.setId(users.getUser().getId());
			employeeBean.setName(users.getUser().getAccounts());
			if (users.getUser().getNumber()!= null) {
				employeeBean.setNum(String.valueOf(users.getUser().getNumber()));
			}else{
				employeeBean.setNum("");
			}
			employeeBean.setDepName("");
			msg.setData(employeeBean);
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}
	
	/**
	 * 功能：修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/mid", method = RequestMethod.POST)
	public ResultMsg midEquipmentCollar(@RequestBody EquipmentCollarBean bean, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (this.useApplicationService.midEquipment(bean, users, branchs, request)) {
			msg.setResultStatus(200);
		}
		return msg;
	}
	
	/**
	 * 功能：删除
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg deleteEquipmentCollar(String id, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}
		if (this.useApplicationService.deleteEquipment(list, request)) {
			msg.setResultStatus(200);
			msg.setResultMessage("success");
			}
		}
		return msg;
	}
	
	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/editCollar", method = RequestMethod.GET)
	public ResultMsg readEquipmentCollar(Integer id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		EquipmentCollarBean equipmentCollar = this.useApplicationService.readEquipmentCollarBean(id);
		if (equipmentCollar != null) {
			msg.setResultStatus(200);
			msg.setData(equipmentCollar);
		}
		return msg;
	}
	
	/**
	 * 功能：查看详情
	 * */
	@ResponseBody
	@RequestMapping(value = "/editEq", method = RequestMethod.GET)
	public EquipmentCollarBean editPur(Integer id) {
		if (id != null) {
			EquipmentCollar equipmentCollar = this.useApplicationService.readEquipmentCollar(id);
			if (equipmentCollar != null) {
				EquipmentCollarBean bean = new EquipmentCollarBean();
				bean.setRealname(equipmentCollar.getRealname());
				bean.setNum(equipmentCollar.getEmployee().getNumber().toString());
				bean.setDepart(equipmentCollar.getDepart());
				bean.setApplyDT(equipmentCollar.getApplyDT());
				bean.setUsername(equipmentCollar.getUsername());
				bean.setPlace(equipmentCollar.getPlace());
				bean.setGoodsname(equipmentCollar.getGoodsname());
				bean.setModel(equipmentCollar.getModel());
				bean.setNumbers(equipmentCollar.getNumbers().toString());
				if (equipmentCollar.getRemarks() != null) {
					bean.setRemarks(equipmentCollar.getRemarks());
				}else{
					bean.setRemarks("");
				}
				return bean;
			}
		}
		return null;
	}

}
