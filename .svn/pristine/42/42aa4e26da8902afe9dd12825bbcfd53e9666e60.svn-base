package com.baofeng.oa.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baofeng.oa.bean.FinancialAccountsBean;
import com.baofeng.oa.entity.FinancialAccounts;
import com.baofeng.utils.SearchFilter;

public interface IFinancialAccountsService {

	Map<String, Map<String, Object>> readAllFinancialAccounts(String subjects, Integer branchs, String date);

	boolean addFinancialAccounts(List<FinancialAccountsBean> listBean, SearchFilter filter);

	boolean addFinancialAccounts(String name, String subjects, String money, String date, Integer branchs);

	FinancialAccounts findFinancialAccounts(String one, String two, Integer branchs, Date date1, Date date2);

	void saveFinancialAccounts(FinancialAccounts $finacs);

	Object sumFinancialAccounts(Map<String, Map<String, Object>> data, String... args);

	boolean findFinancialAccounts(String name, String subjects, String date);

	Map<String, Map<String, Object>> readAllFinancialAccountsNew(String subjects, String date, SearchFilter filter);

}
