package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baofeng.oa.bean.FinancialAccountsBean;
import com.baofeng.oa.dao.FinancialAccountsDAO;
import com.baofeng.oa.entity.FinancialAccounts;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IFinancialAccountsService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.oa.service.IPlatformsMaintainOutlayService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.oa.service.ISalaryOnlineService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("financialAccountsService")
public class FinancialAccountsServiceImpl implements IFinancialAccountsService {

	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IActoresService actoresService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private ISalaryOnlineService salaryOnlineService;
	@Autowired
	private FinancialAccountsDAO financialAccountsDAO;
	@Autowired
	private IPlatformsMaintainOutlayService maintainOutlayService;
	@Autowired
	private IPlatformsMonthsOutlayService platformsMonthsOutlayService;
	
	/**
	 * 功能：添加自定义支出项目
	 * */
	@Override
	public boolean addFinancialAccounts(String name, String subjects, String money, String date, Integer branchs) {
		Date $date = Constants.convert(date, Constants.format7);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay($date);
		FinancialAccounts financial = new FinancialAccounts();
		financial.setBranchs(branchs);
		financial.setOneSubjectKey(subjects);
		financial.setTwoSubjectMsg(name);
		financial.setCreateDT(date1);
		if (this.financialAccountsDAO.saveFinancialAccounts(financial)) {
			financial.setTwoSubject(financial.getId() + "");
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Map<String, Object>> readAllFinancialAccounts(String subjects, Integer branchs, String date) {
		Date $date = Constants.convert(date, Constants.format7);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay($date);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay($date);
		Map<String, Map<String, Object>> $map = new HashMap<String, Map<String, Object>>();
		List<FinancialAccounts> finList = this.financialAccountsDAO.readAllFinancialAccounts(branchs, subjects, date1, date2);
		if (finList != null && finList.size() > 0) {
			for (FinancialAccounts $msg : finList) {
				Map<String, Object> $temp = new HashMap<String, Object>();
				if ($msg.getOneSubjectKey() != null) {
					String one_twoKey = $msg.getOneSubjectKey() + "_" + $msg.getTwoSubject();
					$temp.put("name", one_twoKey);
					$temp.put("twoSubject", $msg.getTwoSubjectMsg());
					JSONObject vals = null;
					if ($msg.getFinValues() == null) {
						vals = new JSONObject();
						vals.put("fees", $msg.getFiscalCharges());
					} else {
						BigDecimal sum = new BigDecimal(0.00);
						vals = JSONObject.parseObject($msg.getFinValues());
						for (Iterator<?> it = vals.entrySet().iterator(); it.hasNext();) {
							Entry<String, Object> entry = (Entry<String, Object>) it.next();
							String key = entry.getKey();
							Object val = entry.getValue();
							if (val != null && !val.equals("") && !"total".equals(key)) {
								try {
									sum = sum.add(new BigDecimal(val.toString().trim()));
								} catch (Exception e) {
								}
							}
						}
						vals.put("total", new BigDecimal(sum.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					$temp.put("v", vals);
					$temp.put("remark", $msg.getRemark());
					$map.put($msg.getTwoSubject(), $temp);
				}
			}
		}
		return $map;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object sumFinancialAccounts(Map<String, Map<String, Object>> data, String... args) {
		BigDecimal sum = new BigDecimal(0.00);
		if (data != null && data.size() > 0) {
			List<String> exclusionsList = Arrays.asList(args);
			for (Iterator<String> it = data.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				Map<String, Object> map = data.get(key);
				if (!exclusionsList.contains(key)) {
					JSONObject json = (JSONObject) map.get("v");
					if (json != null && json.size() > 0) {
						for (Iterator<?> jit = json.entrySet().iterator(); jit.hasNext();) {
							Entry<String, Object> entry = (Entry<String, Object>) jit.next();
							String kes = entry.getKey();
							Object vals = entry.getValue();
							if (vals != null && !vals.equals("") && !exclusionsList.contains(kes)) {
								try {
									sum = sum.add(new BigDecimal(vals.toString().trim()));
								} catch (Exception e) {
								}
							}
						}
					}
				}
			}
		}
		return sum.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	@Override
	public boolean addFinancialAccounts(List<FinancialAccountsBean> paramsList, SearchFilter filter) {
		if (paramsList != null && paramsList.size() > 0) {
			Date date1 = null;
			Date date2 = null;
			List<FinancialAccounts> finList = new ArrayList<FinancialAccounts>();
			List<String> oneList = new ArrayList<String>();
			for (FinancialAccountsBean bean : paramsList) {
				if (bean.getId() != null) {
					String[] $ids = bean.getId().split("_");
					String one = $ids[0];
					String two = $ids[1];
					oneList.add(one);
					Date $date = Constants.convert(bean.getDate(), Constants.format7);
					date1 = CustomDateUtils.Handler.currentMonthFirstDay($date);
					date2 = CustomDateUtils.Handler.currentMonthEndDay($date);
					FinancialAccounts $fincial = this.financialAccountsDAO.readFinancialAccounts(one, two, Integer.valueOf(filter.getRules().get(0).getData().toString()), date1,
							date2);
					if ($fincial != null) {
						JSONObject json = JSONObject.parseObject(bean.getNumber());
						$fincial.setFinValues(json.toJSONString());
						this.financialAccountsDAO.saveFinancialAccounts($fincial);
						finList.add($fincial);
					}
				}
			}
			this.workManagers.onEvents(WorkNames.MASTER_STANDBY, finList, "master_standby");
			this.workManagers.onEvents(WorkNames.OPERATION, finList, "master_salraies", "master_standby");
		}
		return true;
	}

	@Override
	public boolean findFinancialAccounts(String name, String subjects, String date) {
		Date $date = Constants.convert(date, Constants.format7);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay($date);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay($date);
		FinancialAccounts accounts = this.financialAccountsDAO.findFinancialAccountsByName(name, subjects, date1, date2);
		if (accounts != null) {
			return true;
		}
		return false;
	}

	@Override
	public FinancialAccounts findFinancialAccounts(String one, String two, Integer branchs, Date date1, Date date2) {
		return this.financialAccountsDAO.readFinancialAccounts(one, two, branchs, date1, date2);
	}

	@Override
	public void saveFinancialAccounts(FinancialAccounts $finacs) {
		this.financialAccountsDAO.saveFinancialAccounts($finacs);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Map<String, Object>> readAllFinancialAccountsNew(String subjects, String date, SearchFilter filter) {
		Date $date = Constants.convert(date, Constants.format7);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay($date);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay($date);
		Map<String, Map<String, Object>> $map = new HashMap<String, Map<String, Object>>();
		List<FinancialAccounts> finList = this.financialAccountsDAO.readAllFinancialAccountsNew(subjects, date1, date2, filter);
		if (finList != null && finList.size() > 0) {
			for (FinancialAccounts $msg : finList) {
				Map<String, Object> $temp = new HashMap<String, Object>();
				if ($msg.getOneSubjectKey() != null) {
					String one_twoKey = $msg.getOneSubjectKey() + "_" + $msg.getTwoSubject();
					$temp.put("name", one_twoKey);
					$temp.put("twoSubject", $msg.getTwoSubjectMsg());
					JSONObject vals = null;
					if ($msg.getFinValues() == null) {
						vals = new JSONObject();
						vals.put("fees", $msg.getFiscalCharges());
					} else {
						BigDecimal sum = new BigDecimal(0.00);
						vals = JSONObject.parseObject($msg.getFinValues());
						for (Iterator<?> it = vals.entrySet().iterator(); it.hasNext();) {
							Entry<String, Object> entry = (Entry<String, Object>) it.next();
							String key = entry.getKey();
							Object val = entry.getValue();
							if (val != null && !val.equals("") && !"total".equals(key)) {
								try {
									sum = sum.add(new BigDecimal(val.toString().trim()));
								} catch (Exception e) {
								}
							}
						}
						vals.put("total", new BigDecimal(sum.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					$temp.put("v", vals);
					$temp.put("remark", $msg.getRemark());
					$map.put($msg.getTwoSubject(), $temp);
				}
			}
		}
		return $map;
	}
}
