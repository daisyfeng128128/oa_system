package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.oa.dao.FinEarningsIncomeDAO;
import com.baofeng.oa.entity.FinEarningsIncome;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.service.IFinEarningsIncomeService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("finEarningsIncomeService")
public class FinEarningsIncomeServiceImpl implements IFinEarningsIncomeService {
	@Autowired
	private FinEarningsIncomeDAO finEarningsIncomeDAO;
	@Autowired
	private IPlatformsService platformsService; 

	@Override
	public boolean addEarnings(FinEarningsIncome finEarn) {
		if (finEarn != null && finEarn.getId() != null && finEarn.getId().intValue() > 0) {
			FinEarningsIncome $finEarn = this.finEarningsIncomeDAO.readFinEarningsIncome(finEarn.getId());
			if ($finEarn != null) {
				$finEarn.setPushMoney(finEarn.getPushMoney());
				$finEarn.setPushMoneySalary(finEarn.getPushMoneySalary());
				$finEarn.setBasicSalary(finEarn.getBasicSalary());
				$finEarn.setTotalIncome(finEarn.getTotalIncome());
				finEarn = $finEarn;
			}
		}
		if (this.finEarningsIncomeDAO.addFinEarningsIncome(finEarn)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteFinEarningsIncome(FinEarningsIncome earnIncome) {
		if (earnIncome != null) {
			earnIncome.setDetails(null);
			if (this.finEarningsIncomeDAO.deleteFinEarningsIncome(earnIncome)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<FinEarningsIncome> findAllFinEarningsIncome(Date date1, Date date2) {
		return this.finEarningsIncomeDAO.findAllFinEarningsIncome(date1, date2);
	}

	@Override
	public FinPlatformsIncome findPlatformsIncomeById(Integer id, String platformsId) {
		if (id != null && platformsId != null && platformsId.trim().length() > 0) {
			return this.finEarningsIncomeDAO.findPlatformsIncomeById(id, platformsId);
		}
		return null;

	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String date, String fastArg, String type,SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		NPageResult page = this.finEarningsIncomeDAO.readPages(pageSize, curPage, sortName, sortOrder, fastArg, date1, date2, type ,filter);
		List<Platforms> platList =  platformsService.findAllPlatforms(filter);
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (Object o : page.getData()) {
				FinEarningsIncome post = (FinEarningsIncome) o;
				Map<String, String> map = new HashMap<String, String>();
				map.put("number", String.format("%04d", post.getNumber()));
				if (post.getNumber() == null)
					map.put("number", "-");
				map.put("name", post.getName());
				map.put("aliasname", post.getAliasname());
				map.put("totalIncome", String.format("%.2f", post.getTotalIncome()));
				map.put("basicSalary", String.format("%.2f", post.getBasicSalary()));
				map.put("pushMoney", String.format("%.2f", post.getPushMoney()));
				map.put("pushMoneySalary", String.format("%.2f", post.getPushMoney()));
				if(platList != null && platList.size()>0){
					for(Platforms plat  : platList){
						map.put("plat" + plat.getId(), "0.00");
					}
				}
				if (post.getDetails() != null && post.getDetails().size() > 0) {
					for (FinPlatformsIncome fin : post.getDetails()) {
						map.put("plat" + fin.getPlatId(), String.format("%.2f", fin.getIncome()));
					}
				}
				list.add(map);
			}

			if (sortName != null && sortName.trim().length() > 0) {
				final String name = sortName;
				if (!"number".equals(name) || !"on".equals(type)) {
					if ("asc".equals(sortOrder)) {
						Collections.sort(list, new Comparator<Map<String, String>>() {
							@Override
							public int compare(Map<String, String> map1, Map<String, String> map2) {
								String a1 = "0";
								String a2 = "0";
								if (map1.containsKey(name)) {
									a1 = map1.get(name);
								}
								if (map2.containsKey(name)) {
									a2 = map2.get(name);
								}
								int num = 0;
								if (Double.valueOf(a1) - Double.valueOf(a2) > 0) {
									num = 1;
								} else if (Double.valueOf(a1) - Double.valueOf(a2) < 0) {
									num = -1;
								}
								return num;
							}
						});
					}

					if ("desc".equals(sortOrder)) {
						Collections.sort(list, new Comparator<Map<String, String>>() {
							@Override
							public int compare(Map<String, String> map1, Map<String, String> map2) {
								String a1 = "0";
								String a2 = "0";
								if (map1.containsKey(name)) {
									a1 = map1.get(name);
								}
								if (map2.containsKey(name)) {
									a2 = map2.get(name);
								}
								int num = 0;
								if (Double.valueOf(a2) - Double.valueOf(a1) > 0) {
									num = 1;
								} else if (Double.valueOf(a2) - Double.valueOf(a1) < 0) {
									num = -1;
								}
								return num;
							}
						});
					}
				}
			}
			page.setData(list);
		}
		return page;
	}

}
