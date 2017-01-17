package com.baofeng.work;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.Talent;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.ITalentService;

public class EmployeeTalentOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeTalentOnWorkListener.class);

	public EmployeeTalentOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {

		Employee emp = (Employee) analysis(arg);
		ITalentService talentService = (ITalentService) commService.getService().get(ITalentService.class.getName());
		try {
			if (emp.getEmployeType() == com.baofeng.oa.entity.BaseEnums.EmployeType.TALENT) {
				Talent talent = talentService.findTalentSession(emp.getId());
				if (talent != null) {
					talent.setName(emp.getName());
					talent.setNumber(String.valueOf(emp.getNumber()));
					talent.setAliasname(emp.getAliasname());
					talent.setSex(emp.getSex());
					talent.setPhone(emp.getPhone());
					talent.setQq(emp.getQq());
					talent.setBankCard(emp.getBankCard());
					talent.setBankAddress(emp.getBankAddress());
					talent.setGenrer(LineGenres.OFFLINE);
					talent.setBranchs(emp.getBranchs());
				} else {
					talent = new Talent();
					talent.setName(emp.getName());
					talent.setNumber(String.valueOf(emp.getNumber()));
					talent.setAliasname(emp.getAliasname());
					talent.setSex(emp.getSex());
					talent.setPhone(emp.getPhone());
					talent.setQq(emp.getQq());
					talent.setBankCard(emp.getBankCard());
					talent.setBankAddress(emp.getBankAddress());
					talent.setGenrer(LineGenres.OFFLINE);
					talent.setBranchs(emp.getBranchs());
					talent.setEmployee(emp);
				}
				if (talentService.saveTalent(talent)) {
					logger.info("添加员工同步为线下星探成功!");
				}
			}
		} catch (Exception e) {
			logger.error("添加员工同步为线下星探", e);
		}

	}

}
