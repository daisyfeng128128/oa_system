package com.baofeng.commons.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.dao.RolesDAO;
import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.RoleDetails;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.Roles;
import com.baofeng.commons.service.IRolesService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("rolesService")
public class RolesServiceImpl implements IRolesService {

	@Autowired
	private RolesDAO rolesDAO;

	@Override
	public NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter) {
		return this.rolesDAO.readAllPages(pageSize, curPage, sortName, sortOrder, filter);
	}

	@Override
	public boolean addRoles(Integer id, String name, String described, Roles roles, Map<RoleDetails, List<RoleDetailsAtts>> map) {
		if (roles != null && roles.getId() != null && roles.getId().intValue() > 0) {
			Roles $roles = this.readRoles(roles.getId());
			if ($roles != null) {
				$roles.setName(name);
				$roles.setDescribed(described);
				$roles.getDetails().clear();
				roles = $roles;
			}
		}
		if (map != null && map.size() > 0) {
			for (Iterator<RoleDetails> it = map.keySet().iterator(); it.hasNext();) {
				RoleDetails details = it.next();
				details.setRole(roles);
				List<RoleDetailsAtts> pageList = map.get(details);
				details.setDetails(pageList);
				roles.getDetails().add(details);
			}
		}
		return this.rolesDAO.saveRoles(roles);
	}

	/**
	 * 功能：添加
	 * */
	@Override
	public Roles readRoles(Integer id) {
		if (id != null && id > 0) {
			Roles roles = this.rolesDAO.readRoles(id);
			if (roles != null)
				return roles;
		}
		return null;
	}

	/**
	 * 功能：删除
	 * */
	@Override
	public boolean deleteRoles(Integer id) {
		if (id != null && id.intValue() > 0) {
			Roles roles = this.rolesDAO.readRoles(id);
			if (roles != null) {
				roles.setStatus(EntityStatus.DELETED);
				return this.rolesDAO.saveRoles(roles);
			}
		}
		return false;
	}

	/**
	 * 功能：读取所有权限
	 * */
	@Override
	public List<Roles> readRolesAlls(SearchFilter filter) {
		return this.rolesDAO.readRolesAlls(filter);
	}

	@Override
	public Roles findRolesBySession(String ruleName) {
		return this.rolesDAO.readRolesBySession(ruleName);
	}

	@Override
	public boolean saveRolesBySession(Roles roles) {
		return this.rolesDAO.saveRolesBySession(roles);
	}

	@Override
	public Roles findRolesByRolesName(String name,Integer branchs) {
		if(name !=null && name.trim().length()>0){
			return this.rolesDAO.findRolesByRolesName(name,branchs);
		}
		return null;
	}

}
