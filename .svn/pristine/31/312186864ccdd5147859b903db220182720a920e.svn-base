package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Login;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.service.IOperatorService;
import com.baofeng.oa.bean.BranchOfficeBean;
import com.baofeng.oa.dao.BranchOfficeDAO;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Service("branchOfficeService")
public class BranchOfficeServiceImpl implements IBranchOfficeService {

	@Autowired
	private IOperatorService operatorService;
	@Autowired
	private BranchOfficeDAO branchOfficeDAO;

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter) {
		NPageResult page = this.branchOfficeDAO.readPages(pageSize, curPage, sortName, sortOrder);
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			List<BranchOfficeBean> listBean = new ArrayList<BranchOfficeBean>();
			for (Object o : page.getData()) {
				BranchOffice post = (BranchOffice) o;
				BranchOfficeBean bean = new BranchOfficeBean();
				bean.setIpgp(post.getIpgp());
				bean.setAddress(post.getAddress());
				bean.setEmail(post.getEmail());
				bean.setName(post.getName());
				bean.setNumberHead(post.getNumberHead());
				bean.setTelPhone(post.getTelPhone());
				bean.setId(post.getId().toString());
				bean.setIslogin(post.getIslogin() == 0 ? "否" : "是");
				listBean.add(bean);
			}
			page.setData(listBean);
		}
		return page;
	}

	@Override
	public BranchOfficeBean readBranch(Integer id) {
		if (id != null && id.intValue() > 0) {
			BranchOffice branch = this.branchOfficeDAO.readBranch(id);
			if (branch != null) {
				BranchOfficeBean bean = new BranchOfficeBean();
				bean.setIpgp(branch.getIpgp());
				bean.setAddress(branch.getAddress());
				bean.setEmail(branch.getEmail());
				bean.setId(branch.getId().toString());
				bean.setIslogin(branch.getIslogin().toString());
				bean.setName(branch.getName());
				bean.setNumberHead(branch.getNumberHead());
				bean.setTelPhone(branch.getTelPhone());
				return bean;
			}
		}
		return null;
	}

	@Override
	public boolean addBranch(BranchOffice post) {
		if (post != null && post.getId() != null && post.getId().intValue() > 0) {
			BranchOffice $branchs = this.branchOfficeDAO.readBranch(post.getId());
			$branchs.setAddress(post.getAddress());
			$branchs.setIpgp(post.getIpgp());
			$branchs.setEmail(post.getEmail());
			$branchs.setIslogin(post.getIslogin());
			$branchs.setName(post.getName());
			$branchs.setNumberHead(post.getNumberHead());
			$branchs.setTelPhone(post.getTelPhone());
			post = $branchs;
		}
		if (this.branchOfficeDAO.addBranch(post)) {
			Operator operator = this.operatorService.validation("admin" + post.getNumberHead());
			if (operator == null) {
				operator = new Operator();
				operator.setBranchs(post.getId());
				operator.setAccounts("admin" + post.getNumberHead());
				operator.setNumber(Integer.valueOf(0));
				operator.setPassword(Constants.hexToString("3132332E636F6D"));
				operator.setGenrer(Genres.MANAGER);
				operator.setSex(Sex.MAN);
				operator.setIslogin(Login.YES);
				this.operatorService.addUser(operator);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		if (id != null && id.intValue() > 0) {
			BranchOffice post = this.branchOfficeDAO.readBranch(id);
			if (post != null) {
				if (this.branchOfficeDAO.delete(post))
					return true;
			}
		}
		return false;
	}

	@Override
	public List<BranchOfficeBean> readAllBranchsByLogin() {
		List<BranchOffice> list = this.branchOfficeDAO.readAllBranchsByLogin();
		if (list != null && list.size() > 0) {
			List<BranchOfficeBean> listBean = new ArrayList<BranchOfficeBean>();
			for (BranchOffice post : list) {
				BranchOfficeBean bean = new BranchOfficeBean();
				bean.setName(post.getIpgp());
				bean.setId(post.getId().toString());
				listBean.add(bean);
			}
			return listBean;
		}
		return null;
	}

	@Override
	public PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter filter, String queryFilter, Integer branchsId) {
		return this.branchOfficeDAO.readAllPagesSkip(rows, page, filter, queryFilter, branchsId);
	}

	@Override
	public BranchOffice readBranchOffice(Integer id) {
		return this.branchOfficeDAO.readBranch(id);
	}

	@Override
	public PageResult<?> readPagesAllRole(int rows, int page, SearchFilter filter, String queryFilter) {
		return this.branchOfficeDAO.readPagesAllRole(rows, page, filter, queryFilter);
	}

	@Override
	public List<BranchOffice> findBranchOfficeList() {
		return this.branchOfficeDAO.findBranchOfficeList();
	}

	@Override
	public JSONObject readBranchOfficeSoc(Integer branchs) {
		BranchOffice branch = this.branchOfficeDAO.readBranch(branchs);
		if (branch.getSocialsecurity() != null && branch.getSocialsecurity().trim().length() > 0) {
			JSONObject json = JSONObject.parseObject(branch.getSocialsecurity());
			if (json != null)
				return json;
		}
		return null;
	}
	
	@Override
	public boolean updateBranchs(BranchOfficeBean bean) {
		if (bean != null && bean.getId() != null && bean.getId().trim().length() > 0) {
			BranchOffice branchOff = this.branchOfficeDAO.readBranch(Integer.valueOf(bean.getId()));
			if (bean.getSocialsecurity() != null) {
				JSONObject json = JSONObject.parseObject(bean.getSocialsecurity());
				branchOff.setSocialsecurity(json.toJSONString());
				if (this.branchOfficeDAO.addBranch(branchOff)) {
					return true;
				}
			}
		}
		return false;
	}

}
