package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.baofeng.oa.entity.BigTopDetails;
import com.baofeng.oa.entity.FinancialAccounts;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IBigtopService;
import com.baofeng.oa.service.IFinancialAccountsService;
import com.baofeng.utils.CustomDateUtils;

/**
 * 功能：同步大型充值
 * */
public class BigTopOnWorkListener implements BaseObserver {

	private CommService commService;

	private String[] subjects;

	private static final Logger logger = LoggerFactory.getLogger(BigTopOnWorkListener.class);

	public BigTopOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void update(Observable o, Object arg) {
		try {
			List<FinancialAccounts> finList = null;
			if (arg != null && arg instanceof Object[]) {
				Object[] args = (Object[]) arg;
				if (args.length > 0)
					finList = (List<FinancialAccounts>) args[0];
				if (args.length > 1)
					subjects = (String[]) args[1];
			}
			IBigtopService bigtopService = (IBigtopService) commService.getService().get(IBigtopService.class.getName());
			IFinancialAccountsService financialAccountsService = (IFinancialAccountsService) commService.getService().get(IFinancialAccountsService.class.getName());
			if (finList != null && finList.size() > 0) {
				for (String subject : this.subjects) {
					if (subject != null && subject.trim().length() > 0) {
						for (FinancialAccounts bean : finList) {
							if (bean != null && subject.equals(bean.getOneSubjectKey() + "_" + bean.getTwoSubject())) {
								Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(bean.getCreateDT());
								Date date2 = CustomDateUtils.Handler.currentMonthEndDay(bean.getCreateDT());
								FinancialAccounts fincial = financialAccountsService.findFinancialAccounts(bean.getOneSubjectKey(), bean.getTwoSubject(), bean.getBranchs(), date1,
										date2);
								if (fincial != null) {
									JSONObject json = JSONObject.parseObject(fincial.getFinValues());
									for (Iterator<?> it = json.keySet().iterator(); it.hasNext();) {
										String key = (String) it.next();
										String value = json.getString(key);
										if (value != null && value.trim().length() > 0 && key.startsWith("v")) {
											Integer platId = Integer.valueOf(key.substring(1));
											if (platId != null && platId.intValue() > 0) {
												BigTopDetails details = bigtopService.findBigtopByTopup(platId, date1, date2);
												if (details == null) {
													details = new BigTopDetails();
													details.setCreateDT(bean.getCreateDT());
													details.setPlatId(platId);
													details.setBranchs(fincial.getBranchs());
												}
												details.setAskfees(new BigDecimal(value));
												bigtopService.saveBigtop(details);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("同步大型充值");
		}
	}
}
