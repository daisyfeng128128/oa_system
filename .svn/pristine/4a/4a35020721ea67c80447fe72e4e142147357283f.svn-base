package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.oa.bean.SalaryOnlineBean;
import com.baofeng.oa.dao.SalaryOnlineDAO;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.FinPlatfromsIncome;
import com.baofeng.oa.entity.FinSalaryOnline;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.oa.service.ISalaryOnlineService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("salaryOnlineService")
public class SalaryOnlineServiceImpl implements ISalaryOnlineService {

	@Autowired
	private SalaryOnlineDAO salaryOnlineDAO;
	@Autowired
	private IPlatformsService platformsService;

	@Override
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public NPageResult<?> readAllSalaryOnline(int pageSize, int curPage, String sortName, String sortOrder, String date,  String see, SearchFilter filter,String fastArg) {

		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		List<SalaryOnlineBean> listBean = new ArrayList<SalaryOnlineBean>();

		NPageResult page = this.salaryOnlineDAO.readAllSalaryOnline(pageSize, curPage, sortName, sortOrder, filter,see, date1, date2,fastArg);
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();

		if (page != null && page.getData() != null && page.getData().size() > 0) {
			for (Object o : page.getData()) {
				FinSalaryOnline post = (FinSalaryOnline) o;
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", post.getName());
				map.put("aliasname", post.getAliasname());
				map.put("setRemarks", post.getRemarks());
				List<Platforms> list = this.platformsService.findAllPlatforms(filter);
				if (list != null && list.size() > 0) {
					for (Platforms platforms : list) {
						map.put("plat" + platforms.getId(), String.format("%.2f", 0.00));
						if (post.getIncome() != null && post.getIncome().size() > 0) {
							for (FinPlatfromsIncome platfromsIncome : post.getIncome()) {
								if (platfromsIncome.getPlatId() == platforms.getId()) {
									map.put("plat" + platfromsIncome.getPlatId(),
											String.format("%.2f", platfromsIncome.getIncome() != null ? platfromsIncome.getIncome() : 0.00));
								}
							}
						}
					}
				}
				if (post.getIncome() != null && post.getIncome().size() > 0) {
					for (FinPlatfromsIncome platfromsIncome : post.getIncome()) {
						map.put("plat" + platfromsIncome.getPlatId(),
								String.format("%.2f", platfromsIncome.getIncome() != null ? platfromsIncome.getIncome() : 0.00));
					}
				}
				map.put("id", post.getId().toString());
				map.put("attendance", post.getAttendance() != null ? post.getAttendance().toString() : "" + 0.00);
				map.put("totalIncome", post.getTotalIncome() != null ? post.getTotalIncome().toString() : "" + 0.00);
				map.put("basicSalary", post.getBasicSalary() != null ? post.getBasicSalary().toString() : "" + 0.00);
				map.put("gotherDeduct", post.getOtherDeduct() != null ? post.getOtherDeduct().toString() : "" + 0.00);
				map.put("pushMoney", (post.getPushMoney() != null ? post.getPushMoney().toString() : "" + 0.00)+"%");
				map.put("pushMoneySalary", post.getPushMoneySalary() != null ? post.getPushMoneySalary().toString() : "" + 0.00);
				map.put("deductTax", post.getDeductTax() != null ? post.getDeductTax().toString() : "" + 0.00);

				map.put("platSubsidy", post.getPlatSubsidy() != null ? post.getPlatSubsidy().toString() : "" + 0.00);
				map.put("platDeduct", post.getPlatDeduct() != null ? post.getPlatDeduct().toString() : "" + 0.00);

				map.put("realitySalary", post.getRealitySalary() != null ? post.getRealitySalary().toString() : "" + 0.00);
				map.put("netProfit", post.getNetProfit() != null ? post.getNetProfit().toString() : "" + 0.00);
				if (post.getBilled() != null && post.getBilled() == Grant.PUBLISHED)
					map.put("bill", "已发");
				else
					map.put("bill", "未发");
				listMap.add(map);
			}

		}
		page.setData(listMap);
		return page;
	}

	@Override
	public boolean updateSalary(List<FinSalaryOnline> list) {
		if (list != null && list.size() > 0) {
			for (FinSalaryOnline post : list) {
				this.salaryOnlineDAO.addSalary(post);
			}
			return true;
		}
		return false;
	}

	@Override
	public FinSalaryOnline readFinOnline(Integer id) {
		if (id != null) {
			return this.salaryOnlineDAO.readSalaryOnlineByid(id);
		}
		return null;
	}

	@Override
	public void addSalary(List<FinSalaryOnline> listSal) {
		if (listSal != null) {
			for (FinSalaryOnline post : listSal) {
				this.salaryOnlineDAO.addSalary(post);
			}
		}
	}

	@Override
	public boolean addIncome(FinPlatfromsIncome finPlatfromsIncome) {
		return this.salaryOnlineDAO.addIncome(finPlatfromsIncome);
	}

	@Override
	public boolean findSalaryOnline(Integer id, Date date1, Date date2) {
		return this.salaryOnlineDAO.findValidation(id, date1, date2);
	}

	@Override
	public List<FinSalaryOnline> findAllFinSalaryOnlineByDate(Date date1, Date date2) {
		return this.salaryOnlineDAO.findAllFinSalaryOnlineByDate(date1, date2);
	}

	@Override
	public FinPlatfromsIncome findFinPlatfromsIncome(Integer salaryId, String platformsId, Date date1, Date date2) {
		return this.salaryOnlineDAO.findAllFinSalaryOnlineByDate(salaryId, platformsId, date1, date2);
	}

	@Override
	public boolean updateFinPlatformsIncome(FinPlatfromsIncome finIncome) {
		return this.salaryOnlineDAO.updateFinPlatformsIncome(finIncome);
	}

	

	@Override
	public boolean saveFinSalaryOfflineByList(List<String> list) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
				FinSalaryOnline salary = this.salaryOnlineDAO.readSalaryOnlineByid(Integer.valueOf(id));
				if (salary != null) {
					salary.setBilled(Grant.PUBLISHED);
					if (!this.salaryOnlineDAO.addSalary(salary)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public List<FinSalaryOnline> readAllsalaryOnlineByDate(Date date1, Date date2) {
		return this.salaryOnlineDAO.readAllsalaryOnlineByDate(date1,date2);
	}

	@Override
	public boolean check(String date) {
		return false;
	}

}
