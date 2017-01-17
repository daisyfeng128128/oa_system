package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.AssetsBean;
import com.baofeng.oa.dao.AssetsDAO;
import com.baofeng.oa.entity.Assets;
import com.baofeng.oa.entity.BaseEnums.HeadPass;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.service.IAssetsService;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("assetsService")
public class AssetsServiceImpl implements IAssetsService {
	@Autowired
	private AssetsDAO assetsDAO;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName,String sortOrder,String branchsMsg, SearchFilter searchFilter) {
		NPageResult rows = this.assetsDAO.readPages(pageSize, curPage, sortName, sortOrder, branchsMsg,searchFilter);
		if (rows !=null  && rows.getData().size() > 0) {
			List<AssetsBean> beanList = new ArrayList<AssetsBean>();
				for (Object o : rows.getData()) {
					Assets assets = (Assets) o;
					AssetsBean bean = new AssetsBean();
					bean.setThisMonth(assets.getThisMonth());
					bean.setAssetsMoney(assets.getAssetsMoney());
					bean.setBranchsMsg(branchsMsg);
					bean.setHeadPass(assets.getHeadPass() == HeadPass.UNKNOWN ? "待处理" : (assets.getHeadPass() == HeadPass.PASSED ? "通过" : "未通过"));
					bean.setRemarks(assets.getRemarks());
					beanList.add(bean);
				}
				rows.setData(beanList);
			}
		return rows;
	}

	@Override
	public Assets readAllassetsByDate(Integer branchs, Date $date1, Date $date2) {
		if (branchs != null && branchs.intValue() > 0) {
			try {
				Assets emp = this.assetsDAO.readAllassetsByDate(branchs, $date1, $date2);
				return emp;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public boolean addAssets(Assets assets, HttpServletRequest request) {
		if (assets != null) {
			if (this.assetsDAO.addAssets(assets)) {
				return true;
			}
		}
		return false;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readPagesAduit(int pageSize, int curPage,String sortName, String sortOrder,String branchsMsg, SearchFilter searchFilter) {
		NPageResult rows = this.assetsDAO.readPages(pageSize, curPage, sortName, sortOrder, branchsMsg,searchFilter);
		if (rows !=null  && rows.getData().size() > 0) {
			List<AssetsBean> beanList = new ArrayList<AssetsBean>();
				for (Object o : rows.getData()) {
					Assets assets = (Assets) o;
					AssetsBean bean = new AssetsBean();
					bean.setId(assets.getId());
					bean.setEmpName(assets.getEmployee().getName());
					bean.setFile(assets.getFile());
					bean.setBranchs(assets.getBranchs().toString());
					BranchOffice boffice = this.branchOfficeService.readBranchOffice(Integer.valueOf(bean.getBranchs()));
					if (boffice != null)
						bean.setBranchsMsg(boffice.getIpgp());
					bean.setCompleteDT(assets.getCompleteDT());
					bean.setThisMonth(assets.getThisMonth());
					bean.setHeadPass(assets.getHeadPass() == HeadPass.UNKNOWN ? "待处理" : (assets.getHeadPass() == HeadPass.PASSED ? "通过" : "未通过"));
					bean.setRemarks(assets.getRemarks());
					bean.setOpinion(assets.getOpinion());
					beanList.add(bean);
				}
				rows.setData(beanList);
			}
		return rows;
	}

	@Override
	public boolean updatendAssets(AssetsBean bean, RoleUsers users,HttpServletRequest request) {
		if (bean.getId() != null && bean.getId().intValue() > 0) {
			Assets assets = this.assetsDAO.readAssets(bean.getId());
			if (assets != null) {
				Employee employee = this.employeeService.readEmployee(users.getUser().getId());
				assets.setOpinion(bean.getOpinion());
				assets.setHeadPass(HeadPass.PASSED);
				assets.setEmployee(employee);
				if (this.assetsDAO.addAssets(assets)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Assets readAssetsById(Integer id, SearchFilter searchFilter) {
		return this.assetsDAO.readAssets(id);
	}

	@Override
	public Assets readAllassetsByThisMonth(Date date) {
		return this.assetsDAO.readAssetsByThisMonth(date);
	}

}
