package com.baofeng.work;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.EmployeType;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BaseEnums.Settlement;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsActoresService;

public class EmployeeActoresOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeActoresOnWorkListener.class);

	public EmployeeActoresOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		Employee emp = (Employee) analysis(arg);
		WorkManagers workManagers = (WorkManagers) commService.getService().get(WorkManagers.class.getName());
		IPlatformsActoresService platformsActoresService = (IPlatformsActoresService) commService.getService().get(IPlatformsActoresService.class.getName());
		try {
			if (emp.getActores() == com.baofeng.commons.entity.Operator.Actores.YES || emp.getEmployeType() == EmployeType.ACTORES) {
				Actores actores = platformsActoresService.findValidationSession(emp.getId());
				if (actores != null) {
					actores.setBranchs(emp.getBranchs());
					actores.setRealname(emp.getName());
					actores.setAliasname(emp.getAliasname());
					actores.setQq(emp.getQq());
					actores.setPhone(emp.getPhone());
					actores.setIdcard(emp.getIdcard());
					actores.setEntryTime(emp.getJoinDate());
					actores.setSex(emp.getSex());
					Float salary = emp.getSalary() == null ? new Float(0.00) : emp.getSalary();
					Float probationSalary = emp.getProbationSalary() == null ? new Float(0.00) : emp.getProbationSalary();
					Float otherSubsidy = emp.getOtherSubsidy() == null ? new Float(0.00) : emp.getOtherSubsidy();
					actores.setBasicSalary(salary + otherSubsidy);
					actores.setProbationSalary(salary + probationSalary);
					actores.setGenrer(emp.getGenrer());
					actores.setPushMoney(emp.getPushMoney().intValue());
					actores.setAddress(emp.getLiveAdress());
					actores.setEmail(emp.getEmail());
				} else {
					actores = new Actores();
					actores.setBranchs(emp.getBranchs());
					actores.setNumber(emp.getNumber());
					actores.setRealname(emp.getName());
					actores.setSex(emp.getSex());
					actores.setAliasname(emp.getAliasname());
					actores.setAddress(emp.getLiveAdress());
					actores.setIdcard(emp.getIdcard());
					actores.setGenre(LineGenres.OFFLINE);
					actores.setPhone(emp.getPhone());
					actores.setQq(emp.getQq());
					actores.setEmail(emp.getEmail());
					actores.setGenrer(emp.getGenrer());
					actores.setEntryTime(emp.getJoinDate());
					actores.setMinimumGuarantee(Float.valueOf(0));
					actores.setPushMoney(emp.getPushMoney().intValue());
					Float salary = emp.getSalary() == null ? new Float(0.00) : emp.getSalary();
					Float probationSalary = emp.getProbationSalary() == null ? new Float(0.00) : emp.getProbationSalary();
					Float otherSubsidy = emp.getOtherSubsidy() == null ? new Float(0.00) : emp.getOtherSubsidy();
					actores.setBasicSalary(salary + otherSubsidy);
					actores.setProbationSalary(salary + probationSalary);
					actores.setGenrer(emp.getGenrer());
					actores.setLabour(ActoresLabour.SYSTEM);
					actores.setEmployee(emp);
					actores.setSettl(Settlement.SINGLEPLATFORM);
				}
				boolean ft = false;
				if (actores.getId() != null)
					ft = true;
				if (platformsActoresService.saveActores(actores)) {
					if (ft)
						workManagers.onEvents(WorkNames.ACTORES_MID, actores);
					logger.info("添加员工同步为线下艺人成功!");
				}
			}
		} catch (Exception e) {
			logger.error("添加员工同步为线下艺人", e);
		}
	}
}
