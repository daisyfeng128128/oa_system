package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.baofeng.oa.entity.FinancialAccounts;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActivityOutlay;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IFinancialAccountsService;
import com.baofeng.oa.service.IPlatformsActivityOutlayService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.CustomDateUtils;

/**
 * 功能：同步财务支出到月报运营支出
 * */
public class OperationOnWorkListener implements BaseObserver {

	private String[] exclude;
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(MidAdvancetOnWorkListener.class);

	public OperationOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		try {
			List<FinancialAccounts> finList = null;
			if (arg != null && arg instanceof Object[]) {
				Object[] args = (Object[]) arg;
				if (args.length > 0)
					finList = (List<FinancialAccounts>) args[0];
				if (args.length > 1) {
					exclude = new String[args.length - 1];
					System.arraycopy(args, 1, exclude, 0, args.length - 1);
				}
			}
			IPlatformsService platformsService = (IPlatformsService) commService.getService().get(IPlatformsService.class.getName());
			IFinancialAccountsService financialAccountsService = (IFinancialAccountsService) commService.getService().get(IFinancialAccountsService.class.getName());
			IPlatformsActivityOutlayService activityOutlayService = (IPlatformsActivityOutlayService) commService.getService().get(IPlatformsActivityOutlayService.class.getName());
			IPlatformsMonthsOutlayService platformsMonthsOutlayService = (IPlatformsMonthsOutlayService) commService.getService()
					.get(IPlatformsMonthsOutlayService.class.getName());
			String excludes = Arrays.toString(this.exclude);
			if (finList != null && finList.size() > 0) {
				for (FinancialAccounts bean : finList) {
					if (bean != null && bean.getOneSubjectKey().equals("master") && !excludes.contains(bean.getOneSubjectKey() + "_" + bean.getTwoSubject())) {
						Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(bean.getCreateDT());
						Date date2 = CustomDateUtils.Handler.currentMonthEndDay(bean.getCreateDT());
						FinancialAccounts fincial = financialAccountsService.findFinancialAccounts(bean.getOneSubjectKey(), bean.getTwoSubject(), bean.getBranchs(), date1, date2);
						if (fincial != null) {
							JSONObject json = JSONObject.parseObject(fincial.getFinValues());
							for (Iterator<?> it = json.keySet().iterator(); it.hasNext();) {
								String key = (String) it.next();
								String value = json.getString(key);
								if (value != null && value.trim().length() > 0 && key.startsWith("v")) {
									Integer platId = Integer.valueOf(key.substring(1));
									if (platId != null && platId.intValue() > 0) {
										Platforms $platforms = platformsService.findPlatforms(platId);
										PlatformsActivityOutlay acuivity = activityOutlayService.findActivityOutlayByPlatId(fincial.getTwoSubjectMsg(), platId, date1, date2);
										if (acuivity == null) {
											PlatformsMonthsOutlay months = platformsMonthsOutlayService.findPlatformsMonthsOutlay(platId.toString(), bean.getBranchs(), date1,
													date2);
											if (months != null) {
												acuivity = new PlatformsActivityOutlay();
												acuivity.setMonths(months);
												acuivity.setBranchs(months.getBranchs());
												acuivity.setActivity(fincial.getTwoSubjectMsg());
												acuivity.setPlatformsId(platId.toString());
												acuivity.setPlatforms($platforms.getPlatName());
												acuivity.setTax(new BigDecimal(0.00));
												acuivity.setCreateDT(date1);
											}
										}
										acuivity.setTotalMoney(new Float(value));
										activityOutlayService.savePlatformsActivityOutlay(acuivity);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("同步财务支出到月报运营支出失败");
		}
	}
}
