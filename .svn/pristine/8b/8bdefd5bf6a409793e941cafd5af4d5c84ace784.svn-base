package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.oa.bean.FinSalariesTalentBean;
import com.baofeng.oa.dao.FinSalariesTalentDAO;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.FinSalariesTalent;
import com.baofeng.oa.service.IFinSalariesTalentService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("finSalariesTalentService")
public class FinSalariesTalentServiceImpl implements IFinSalariesTalentService {

	@Autowired
	private FinSalariesTalentDAO finSalariesTalentDAO;

	@Override
	public boolean findValidation(Integer id, Date date1, Date date2) {
		if (id != null && id.intValue() > 0) {
			return this.finSalariesTalentDAO.findValidation(id, date1, date2);
		}
		return false;
	}

	@Override
	public void addFinSalariesTalentList(List<FinSalariesTalent> listSal) {
		if (listSal != null && listSal.size() > 0) {
			for (FinSalariesTalent fin : listSal) {
				this.finSalariesTalentDAO.addFinSalariesTalent(fin);
			}
		}
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String date, String fastArg, Integer all, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		NPageResult page = this.finSalariesTalentDAO.readPages(pageSize, curPage, sortName, sortOrder, date1, date2, fastArg, all, filter);
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			List<FinSalariesTalentBean> listBean = new ArrayList<FinSalariesTalentBean>();
			for (Object o : page.getData()) {
				FinSalariesTalent talent = (FinSalariesTalent) o;
				FinSalariesTalentBean bean = new FinSalariesTalentBean();
				bean.setId(talent.getId().toString());
				bean.setAliasname(talent.getAliasname());
				bean.setBankAddress(talent.getBankAddress());
				bean.setBankCard(talent.getBankCard());
				bean.setBill(talent.getBill() == Grant.PUBLISHED ? "已发" : "未发");
				bean.setName(talent.getName());
				bean.setTaSalary(String.format("%.2f", talent.getTaSalary() != null ? talent.getTaSalary() : new BigDecimal(0.00)));
				bean.setSex(talent.getSex() == Sex.WOMAN ? "女" : "男");
				bean.setRemarks(talent.getRemarks());
				bean.setRealExpenditure(String.format("%.2f", talent.getRealExpenditure() != null ? talent.getRealExpenditure() : new BigDecimal(0.00)));
				bean.setRealitySalary(String.format("%.2f", talent.getRealitySalary() != null ? talent.getRealitySalary() : new BigDecimal(0.00)));
				listBean.add(bean);
			}
			page.setData(listBean);
		}
		return page;
	}

	@Override
	public boolean save(Integer branchs, List<FinSalariesTalentBean> listBean) {
		if (listBean != null && listBean.size() > 0) {
			for (FinSalariesTalentBean bean : listBean) {
				if (bean != null && bean.getId() != null) {
					FinSalariesTalent talent = this.finSalariesTalentDAO.readFinSalariesTalent(Integer.valueOf(bean.getId()));
					if (talent != null) {
						talent.setTaSalary(new BigDecimal(bean.getTaSalary() != null ? bean.getTaSalary() : "0.00"));
						if (!this.finSalariesTalentDAO.addFinSalariesTalent(talent)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean saveFinSalariesTalentByList(List<String> list) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
				if (id != null && id.trim().length() > 0) {
					FinSalariesTalent talent = this.finSalariesTalentDAO.readFinSalariesTalent(Integer.valueOf(id));
					if (talent != null) {
						talent.setBill(Grant.PUBLISHED);
						if (!this.finSalariesTalentDAO.addFinSalariesTalent(talent)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public List<FinSalariesTalent> readAllFinSalariesTalent(String date, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		return this.finSalariesTalentDAO.readAllFinSalariesTalent(date1, date2, filter);
	}

	@Override
	public FinSalariesTalentBean readSalariesTalent(String id) {
		if (id != null && id.trim().length() > 0) {
			FinSalariesTalent talent = this.finSalariesTalentDAO.readFinSalariesTalent(Integer.valueOf(id));
			if (talent != null) {
				FinSalariesTalentBean bean = new FinSalariesTalentBean();
				bean.setAliasname(talent.getAliasname());
				bean.setBankAddress(talent.getBankAddress());
				bean.setBankCard(talent.getBankCard());
				bean.setId(talent.getId().toString());
				bean.setName(talent.getName());
				bean.setSex(talent.getSex() == Sex.WOMAN ? "女" : "男");
				bean.setTaSalary(talent.getTaSalary() != null ? String.format("%.2f", talent.getTaSalary()) : "0.00");
				bean.setOtherDeduct(String.format("%.2f", talent.getOtherDeduct() != null ? talent.getOtherDeduct() : new BigDecimal(0.00)));
				bean.setOtherSubsidy(String.format("%.2f", talent.getOtherSubsidy() != null ? talent.getOtherSubsidy() : new BigDecimal(0.00)));
				bean.setRemarks(talent.getRemarks());
				bean.setRealExpenditure(String.format("%.2f", talent.getRealExpenditure() != null ? talent.getRealExpenditure() : new BigDecimal(0.00)));
				bean.setRealitySalary(String.format("%.2f", talent.getRealitySalary() != null ? talent.getRealitySalary() : new BigDecimal(0.00)));
				return bean;
			}
		}
		return null;
	}

	@Override
	public boolean addFinSalariesTalent(FinSalariesTalent talent) {
		if (talent != null && talent.getId() != null && talent.getId().intValue() > 0) {
			FinSalariesTalent $talent = this.finSalariesTalentDAO.readFinSalariesTalent(talent.getId());
			$talent.setTaSalary(talent.getTaSalary());
			$talent.setOtherDeduct(talent.getOtherDeduct());
			$talent.setOtherSubsidy(talent.getOtherSubsidy());
			$talent.setRemarks(talent.getRemarks());
			talent = $talent;
		}
		BigDecimal realExpenditure = new BigDecimal(0.00);
		realExpenditure = talent.getTaSalary().add(talent.getOtherSubsidy()).subtract(talent.getOtherDeduct());
		talent.setRealExpenditure(realExpenditure);
		talent.setRealitySalary(realExpenditure);
		if (this.finSalariesTalentDAO.addFinSalariesTalent(talent)) {
			return true;
		}
		return false;
	}

	@Override
	public List<FinSalariesTalent> findAllFinSalariesTalent(Date $date1, Date $date2) {
		return this.finSalariesTalentDAO.findAllFinSalariesTalent($date1, $date2);
	}

	@Override
	public BigDecimal findSumOnlineTalents(Integer branchs, Date $date1, Date $date2) {
		return this.finSalariesTalentDAO.findSumOnlineTalents(branchs, $date1, $date2);
	}

}
