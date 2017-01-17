package com.baofeng.oa.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.baofeng.commons.entity.Roles;
import com.baofeng.commons.service.IRolesService;
import com.baofeng.commons.service.IUsersService;
import com.baofeng.oa.bean.BranchLoginBean;
import com.baofeng.oa.bean.BranchOfficeBean;
import com.baofeng.oa.bean.EmployeeBean;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.BranchUser;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IBranchUserService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPositionsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.ResultMsg;
import com.baofeng.utils.SearchFilter;

/**
 * 功能: 分公司登陆表
 * */
@Controller
@RequestMapping("/branchLogin")
public class BranchUserController extends BaseController {

	@Autowired
	private IBranchUserService branchUserService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IPositionsService positionsService;
	@Autowired
	private IRolesService rolesService;
	@Autowired
	private IUsersService usersService;

	/** 分公司登录表 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showAll(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/branchLogin");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("branchLogin/show.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, HttpServletRequest request) {
		NPageResult pages = null;
		pages = this.branchUserService.readAllPages(pageSize, curPage, sortName, sortOrder, super.searchFilter(request));
		return pages;
	}

	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/editEmp", method = RequestMethod.GET)
	public ResultMsg read(Integer id, String loadTable) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.intValue() > 0) {
			RoleUsers users = this.usersService.readRoleUsers(id.toString());
			if (users != null && users.getUser() != null) {
				Employee emp = this.employeeService.readEmployee(users.getUser().getId());
				if (emp != null) {
					EmployeeBean bean = new EmployeeBean();
					bean.setId(users.getId());
					bean.setNum(emp.getNumber().toString());
					bean.setName(emp.getName());
					bean.setPhone(emp.getPhone());
					BranchOfficeBean branch = this.branchOfficeService.readBranch(emp.getBranchs());
					if (branch != null)
						bean.setBranchs(branch.getIpgp());
					else
						bean.setBranchs("");
					if (emp.getDepart() != null)
						bean.setDepName(emp.getDepart().getName());
					if (emp.getPosition() != null)
						bean.setPosName(emp.getPosition().getName());
					msg.setData(bean);
					msg.setResultMessage("success");
					msg.setResultStatus(200);
				}
			}
		}
		return msg;
	}

	/**
	 * 功能： 保存
	 * */
	@ResponseBody
	@RequestMapping("/save")
	public ResultMsg save(@RequestBody BranchLoginBean bean, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		BranchUser branch = new BranchUser();
		if (bean.getId() != null && bean.getId().trim().length() > 0)
			branch.setId(Integer.valueOf(bean.getId()));
		branch.setDepart(bean.getDepart());
		branch.setEmail(bean.getEmail());
		if (bean.getEmpid() != null && bean.getEmpid().trim().length() > 0) {
			RoleUsers users = this.usersService.readRoleUsers(bean.getEmpid());
			if (users != null)
				branch.setUser(users);
		}
		branch.setBranchs(branchs);
		branch.setEndTime(bean.getEndTime());
		branch.setName(bean.getName());
		if (bean.getNumber() != null && bean.getNumber().trim().length() > 0)
			branch.setNumber(Integer.valueOf(bean.getNumber()));
		branch.setPhone(bean.getPhone());
		branch.setOwnBranchs(bean.getOwnBranchs());
		branch.setPositions(bean.getPositions());
		if (bean.getTarBranchs_id() != null && bean.getTarBranchs_id().trim().length() > 0) {
			BranchOffice branchOff = this.branchOfficeService.readBranchOffice(Integer.valueOf(bean.getTarBranchs_id()));
			branch.setTarBranchs(branchOff);
		}
		if (bean.getRole_id() != null && bean.getRole_id().trim().length() > 0) {
			HashSet<Roles> role = new HashSet<Roles>();
			String role1 = bean.getRole_id();
			String[] strA=role1.split(",");
			for(int i=0;i<strA.length;i++){
				int StrA=Integer.valueOf(strA[i]);
				Roles roles = this.rolesService.readRoles(StrA);
				role.add(roles);
			}
			branch.setDetails((java.util.Set<Roles>) role);
		}
		if (branch.getId() == null || !(branch.getId().intValue() > 0)) {
			if (this.branchUserService.checkBranchUser(branch)) {
				msg.setResultMessage("error");
				msg.setResultStatus(101);
				return msg;
			}
		}
		if (this.branchUserService.addBranchLogin(branch)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping("/edit")
	public ResultMsg edit(Integer id,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.intValue() > 0) {
			BranchUser branch = this.branchUserService.readBranchLogin(id);
			Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
			if (branch != null) {
				BranchLoginBean bean = new BranchLoginBean();
				bean.setId(branch.getId().toString());
				bean.setBranchs(branchs.toString());
				bean.setName(branch.getName());
				bean.setDepart(branch.getDepart());
				bean.setPositions(branch.getPositions());
				bean.setEndTime(branch.getEndTime());
				bean.setNumber(branch.getNumber().toString());
				bean.setOwnBranchs(branch.getOwnBranchs());
				bean.setOwnBranchs_id(branch.getBranchs().toString());
				bean.setPhone(branch.getPhone());
				if (branch.getDetails() != null && branch.getDetails().size() > 0) {
					Integer role2 = null;
					List<String> l = new ArrayList<String>();
					Set<Roles> role =  branch.getDetails();
					 Iterator<Roles> it = role.iterator();
					  while(it.hasNext()){
						  role2= it.next().getId();
						  l.add(role2.toString());
					  }
					  bean.setRole_id1(l);
				}else if(branch.getRole() != null){
					Roles role =  branch.getRole();
					bean.setRole_id(role.getId().toString());
				}
				
				bean.setTarBranchs(branch.getTarBranchs().getName());
				bean.setTarBranchs_id(branch.getTarBranchs().getId().toString());
				bean.setEmail(branch.getEmail());
				msg.setData(bean);
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesAllBranchs", method = RequestMethod.POST)
	public PageResult<?> readPagesAllBranchs(int page, int rows, String filter, Integer empid, HttpServletRequest request) {
		PageResult<?> pages = null;
		Employee emp = this.employeeService.readEmployee(empid);
		SearchFilter $filter = new SearchFilter();
		pages = this.branchOfficeService.readAllPagesSkip(rows, page, $filter, filter, emp.getBranchs());
		return pages;
	}

	/**
	 * 功能: 读取权限
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesAllRole", method = RequestMethod.POST)
	public PageResult<?> readPagesAllRole(int page, int rows, String filter, HttpServletRequest request) {
		PageResult<?> pages = null;
		SearchFilter $filter = super.searchFilter(request);
		pages = this.branchOfficeService.readPagesAllRole(rows, page, $filter, filter);
		return pages;
	}

	/**
	 * 功能：删除
	 * */
	@ResponseBody
	@RequestMapping("/delete")
	public ResultMsg delete(Integer id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.intValue() > 0) {
			if (this.branchUserService.delete(id)) {
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}
}
