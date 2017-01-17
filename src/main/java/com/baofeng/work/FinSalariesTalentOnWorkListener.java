package com.baofeng.work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.FinSalariesTalent;
import com.baofeng.oa.entity.Talent;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IFinSalariesTalentService;
import com.baofeng.oa.service.ITalentService;
import com.baofeng.utils.CustomDateUtils;
/**
 * 功能：同步星探工资
 * */
public class FinSalariesTalentOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(FinSalariesTalentOnWorkListener.class);

	public FinSalariesTalentOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		FinSalariesTalent salariesTalent = (FinSalariesTalent) analysis(arg);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(salariesTalent.getCreateDT());
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(salariesTalent.getCreateDT());
		ITalentService talentService = (ITalentService) commService.getService().get(ITalentService.class.getName());
		IFinSalariesTalentService finSalariesTalentService = (IFinSalariesTalentService) commService.getService().get(IFinSalariesTalentService.class.getName());
		try {
			List<Talent> list = talentService.findAllTalent();
			List<FinSalariesTalent> listSal = new ArrayList<FinSalariesTalent>();
			if (list != null && list.size() > 0) {
				for (Talent talent : list) {
					if (!finSalariesTalentService.findValidation(talent.getId(), date1, date2)) {
						FinSalariesTalent finSalariesTalent = new FinSalariesTalent();
						finSalariesTalent.setAliasname(talent.getAliasname());
						finSalariesTalent.setBankAddress(talent.getBankAddress());
						finSalariesTalent.setBankCard(talent.getBankCard());
						finSalariesTalent.setCreateDT(date1);
						finSalariesTalent.setName(talent.getName());
						finSalariesTalent.setSex(talent.getSex());
						finSalariesTalent.setTalent(talent);
						finSalariesTalent.setBill(Grant.UNKNOWN);
						finSalariesTalent.setBranchs(talent.getBranchs());
						listSal.add(finSalariesTalent);
					}
				}
			}
			finSalariesTalentService.addFinSalariesTalentList(listSal);
		} catch (Exception e) {
			logger.error("同步星探工资有误", e);
		}
	}
}
