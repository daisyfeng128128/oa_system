package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.dao.UsersDAO;
import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.Operator.Login;
import com.baofeng.commons.entity.RoleDetails;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.entity.Roles;
import com.baofeng.commons.service.IRolesService;
import com.baofeng.oa.bean.BranchLoginBean;
import com.baofeng.oa.dao.BranchUserDAO;
import com.baofeng.oa.entity.BaseEnums.Overdue;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.BranchUser;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IBranchUserService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("branchUserService")
public class BranchUserServiceImpl implements IBranchUserService {

	@Autowired
	private BranchUserDAO branchUserDAO;
	@Autowired
	private IRolesService rolesService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private UsersDAO usersDAO;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter) {
		NPageResult page = this.branchUserDAO.readPages(pageSize, curPage, sortName, sortOrder, filter);
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			BranchOffice branchOffice = null;
			List<BranchLoginBean> listBean = new ArrayList<BranchLoginBean>();
			for (Object o : page.getData()) {
				BranchUser post = (BranchUser) o;
				BranchLoginBean bean = new BranchLoginBean();
				bean.setCreateDT(post.getCreateDT());
				bean.setDepart(post.getDepart());
				bean.setEmail(post.getEmail());
				bean.setEndTime(post.getEndTime());
				bean.setId(post.getId().toString());
				bean.setName(post.getName());
				if (branchOffice == null)
					branchOffice = this.branchOfficeService.readBranchOffice(post.getUser().getUser().getBranchs());
				if (branchOffice != null)
					bean.setNumber(branchOffice.getNumberHead() + String.format("%04d", post.getNumber()));
				else
					bean.setNumber(String.format("%04d", post.getNumber()));
				bean.setOverdues(post.getOverdues() == Overdue.NO ? "正常" : "过期");
				bean.setOwnBranchs(post.getOwnBranchs());
				bean.setPhone(post.getPhone());
				bean.setPositions(post.getPositions());
				if (post != null) {
					String role2 = null;
					List<Object> l = new ArrayList<Object>();
					Set<Roles> role = null;
					String role1 = null;
					if (post.getDetails() != null && post.getRole() == null) {
						role =  post.getDetails();
					}else{
						role1 =  post.getRole().getName();
					}
					if (role != null) {
						Iterator<Roles> it = role.iterator();
						 while(it.hasNext()){
							 role2= it.next().getName();
							 l.add(role2);
						 }
						 StringBuilder result=new StringBuilder();
					        boolean flag=false;
					        for (Object string : l) {
					            if (flag) {
					                result.append(",");
					            }else {
					                flag=true;
					            }
					            result.append(string);
					        }
						bean.setRole(result.toString());
					}else{
						StringBuilder result=new StringBuilder();
						result.append(role1);
						bean.setRole(result.toString());
					}
				}
				bean.setTarBranchs(post.getTarBranchs().getIpgp());
				listBean.add(bean);
			}
			page.setData(listBean);
		}
		return page;
	}

	@Override
	public boolean addBranchLogin(BranchUser branch) {
		if (branch != null && branch.getId() != null && branch.getId().intValue() > 0) {
			BranchUser $branch = this.branchUserDAO.readBranchLogin(branch.getId());
			if ($branch != null) {
				$branch.setEndTime(branch.getEndTime());
				$branch.setEmail(branch.getEmail());
				$branch.setDetails(branch.getDetails());
				branch = $branch;
			}
		} else {
			HashSet<Roles> role = new HashSet<Roles>();
			Roles roles = rolesService.findRolesByRolesName("普通员工", branch.getTarBranchs().getId());
			if (roles == null) {
				roles = new Roles();
				roles.setDescribed("注册员工自动生成");
				roles.setName("普通员工");
				roles.setBranchs(branch.getTarBranchs().getId());
				
				// 修改密码
				RoleDetails roleDetails1 = new RoleDetails();
				roleDetails1.setItemUrl("users/midPwd.do");
				roleDetails1.setRole(roles);
				if (branch.getTarBranchs() != null)
					roleDetails1.setBranchs(branch.getTarBranchs().getId());
				roles.getDetails().add(roleDetails1);
				rolesService.saveRolesBySession(roles);
			}
			role.add(roles);
			branch.setDetails((Set<Roles>) role);

		}
		if (this.branchUserDAO.addBranchLogin(branch)) {
			return true;
		}
		return false;
	}

	@Override
	public BranchUser readBranchLogin(Integer id) {
		if (id != null && id.intValue() > 0) {
			return this.branchUserDAO.readBranchLogin(id);
		}
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		if (id != null && id.intValue() > 0) {
			BranchUser branch = this.branchUserDAO.readBranchLogin(id);
			if (branch != null) {
				branch.setStatus(EntityStatus.DELETED);
				if (this.branchUserDAO.addBranchLogin(branch)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean checkBranchUser(BranchUser branch) {
		if (branch != null && branch.getUser() != null && branch.getUser().getId() != null) {
			if (this.branchUserDAO.checkBranchUser(branch.getUser().getId(), branch.getTarBranchs().getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public BranchUser readBranchLoginbyNum(Integer number) {
		if (number != null && number.intValue() > 0) {
			return this.branchUserDAO.readBranchLoginbyNum(number);
		}
		return null;
	}

	@Override
	public boolean deletebyNum(Integer number, RoleUsers users) {
		if (number != null && number.intValue() > 0) {
			BranchUser branch = this.branchUserDAO.readBranchLoginbyNum(number);
			Operator operator = users.getUser();
			users.setUser(null);
			users.getDetails().clear();
			operator.setIslogin(Login.NO);
			this.usersDAO.saveRoleUsers(users);
			this.usersDAO.getBaseDAO().update(operator);
			if (branch != null) {
				branch.setStatus(EntityStatus.DELETED);
				if (this.branchUserDAO.addBranchLogin(branch)) {
					return true;
				}
			}
		}
		return false;
	}

}
