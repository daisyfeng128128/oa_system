package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.bean.CostCheckBean;
import com.baofeng.oa.dao.CostCheckDAO;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.CostCheck;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.ICostCheckService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("costCheckService")
public class CostCheckServiceImpl implements ICostCheckService {

	@Autowired
	private CostCheckDAO costCheckDAO;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	@Override
	public boolean addCostCheckList(List<CostCheck> list) {
		if (list != null && list.size() > 0) {
			for (CostCheck cost : list) {
				if (!this.costCheckDAO.addCostCheck(cost)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter) {

		NPageResult page = this.costCheckDAO.readPages(pageSize, curPage, sortName, sortOrder, filter);
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			List<CostCheckBean> listBean = new ArrayList<CostCheckBean>();
			BranchOffice branchOffice = null;
			for (Object o : page.getData()) {
				CostCheck post = (CostCheck) o;
				CostCheckBean bean = new CostCheckBean();
				bean.setAliasname(post.getActores().getAliasname());
				bean.setName(post.getActores().getRealname());
				bean.setCostArtists(post.getCostArtists());
				bean.setId(post.getId().toString());
				if (post.getActores().getGenre() == LineGenres.ONLINE) {
					bean.setBasicSalary(post.getActores().getMinimumGuarantee() != null ? String.format("%.2f", post.getActores().getMinimumGuarantee()) : "0.00");
					bean.setNumber("-");
				} else if (post.getActores().getGenrer() == Genres.SHIYONG) {
					bean.setBasicSalary(post.getActores().getProbationSalary() != null ? String.format("%.2f", post.getActores().getProbationSalary()) : "0.00");
					if (branchOffice == null)
						branchOffice = this.branchOfficeService.readBranchOffice(post.getBranchs());
					if (branchOffice != null)
						bean.setNumber(post.getActores().getNumber() != null ? branchOffice.getNumberHead() + String.format("%04d", post.getActores().getNumber()) : "-");
					else
						bean.setNumber(post.getActores().getNumber() != null ? String.format("%04d", post.getActores().getNumber()) : "-");
				} else {
					bean.setBasicSalary(post.getActores().getBasicSalary() != null ? String.format("%.2f", post.getActores().getBasicSalary()) : "0.00");
					if (branchOffice == null)
						branchOffice = this.branchOfficeService.readBranchOffice(post.getBranchs());
					if (branchOffice != null)
						bean.setNumber(post.getActores().getNumber() != null ? branchOffice.getNumberHead() + String.format("%04d", post.getActores().getNumber()) : "-");
					else
						bean.setNumber(post.getActores().getNumber() != null ? String.format("%04d", post.getActores().getNumber()) : "-");
				}
				listBean.add(bean);
			}
			page.setData(listBean);
		}
		return page;
	}

	@Override
	public boolean deleteCostCheck() {
		List<CostCheck> listCost = this.costCheckDAO.readAllCostCheck();
		if(listCost != null && listCost.size()>0  ){
			for(CostCheck cost : listCost){
				cost.setActores(null);
				if(this.costCheckDAO.addCostCheck(cost)){
					if(!this.costCheckDAO.deleteCost(cost))
						return false;
				}else{
					return false;
				}
			}
		}
		return true;
	}

	 

}
