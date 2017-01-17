package com.baofeng.work;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.commons.entity.Operator.Login;
import com.baofeng.commons.entity.RoleDetails;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.entity.Roles;
import com.baofeng.commons.service.IRolesService;
import com.baofeng.commons.service.IUsersService;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.oa.service.IPlatformsManagerService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.MD5;

public class PlatformsOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(PlatformsOnWorkListener.class);

	public PlatformsOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	
	@Override
	public void update(Observable o, Object arg) {

		Platforms plat = (Platforms) analysis(arg);
		String date = Constants.convert(new Date(), Constants.format7);
		Date date1 = DateUtils.addMonths(Constants.convert(date, Constants.format7), -1);
		Date date2 = DateUtils.addMonths(Constants.convert(date + "-" + CustomDateUtils.Handler.daysInMonth(date) + " 23:59:59", Constants.format2), -1);
		IUsersService usersService = (IUsersService) commService.getService().get(IUsersService.class.getName());
		IRolesService rolesService = (IRolesService) commService.getService().get(IRolesService.class.getName());
		IManagerService managerService = (IManagerService) commService.getService().get(IManagerService.class.getName());
		IPlatformsManagerService platformsManagerService = (IPlatformsManagerService) commService.getService().get(IPlatformsManagerService.class.getName());
		IPlatformsMonthsOutlayService platformsMonthsOutlayService = (IPlatformsMonthsOutlayService) commService.getService().get(IPlatformsMonthsOutlayService.class.getName());
		try {
			// 同步添加营收总表
			platformsMonthsOutlayService.savePlatformsMonthsOutlay(plat.getBranchs(), plat, date1, date2);
			if (plat.getPlatManager() != null && plat.getPlatManager().getId() != null) {
				Employee emp = plat.getPlatManager();
				// 生成助理管理表
				Managers manager = managerService.findManagerByEmpIdSession(emp.getId());
				if (manager == null) {
					manager = new Managers();
					manager.setEmployee(emp);
					manager.setBranchs(emp.getBranchs());
					manager.setNumber(emp.getNumber());
					manager.setAliasname(emp.getAliasname());
					manager.setPhone(emp.getPhone());
					manager.setQq(emp.getQq());
					manager.setRealname(emp.getName());
					manager.setSex(emp.getSex());
					manager.setAddress(emp.getLiveAdress());
					manager.setGenre(LineGenres.OFFLINE);
					manager.setPositions("平台主管");
					manager.setEntryTime(emp.getJoinDate());
					Float probationSalary = emp.getProbationSalary() == null ? new Float(0.00) : emp.getProbationSalary();
					Float salary = emp.getSalary() == null ? new Float(0.00) : emp.getSalary();
					Float otherSubsidy = emp.getOtherSubsidy() == null ? new Float(0.00) : emp.getOtherSubsidy();
					manager.setProbationSalary(salary + probationSalary);
					manager.setBasicSalary(salary + otherSubsidy);
					manager.setLabour(ActoresLabour.SYSTEM);
					manager.setGenrer(emp.getGenrer());
					managerService.saveManagerBySession(manager);
				}
				// 生成到频道助理表
				PlatformsManager plam = null;
				List<?> platList = platformsManagerService.findPlatformsManagerBySession(manager.getId());
				if (platList != null && platList.size() > 0) {
					plam = (PlatformsManager) platList.get(0);
				}
				if (plam == null) {
					PlatformsManager $plam = new PlatformsManager();
					$plam.setPlat(plat);
					$plam.setManager(manager);
					if (plat.getChannels() != null && plat.getChannels().size() > 0)
						$plam.setChannel(plat.getChannels().get(0));
					$plam.setBranchs(emp.getBranchs());
					platformsManagerService.savePlatformsManagerBySession($plam);
				}
				// 生成权限表
				String ruleName = String.valueOf(plat.getPlatName() + "平台");
				Roles roles = rolesService.findRolesBySession(ruleName);
				if (roles == null) {
					roles = new Roles();
					roles.setName(ruleName);
					roles.setBranchs(emp.getBranchs());
					roles.setDescribed("添加直播平台自动生成");

					// 直播平台
					RoleDetails $plat1 = new RoleDetails();
					$plat1.setItemUrl("plats/show.do");
					$plat1.setRole(roles);
					RoleDetailsAtts atts1 = new RoleDetailsAtts();
					atts1.setOpName(plat.getPlatName());
					atts1.setOpkey(plat.getId().toString());
					atts1.setRoleDetails($plat1);
					$plat1.getDetails().add(atts1);
					roles.getDetails().add($plat1);

					// 艺人管理
					RoleDetails $actores = new RoleDetails();
					$actores.setItemUrl("actores/show.do");
					$actores.setRole(roles);

					RoleDetailsAtts add = new RoleDetailsAtts();
					add.setOpName("新增");
					add.setOpkey("add");
					add.setRoleDetails($actores);
					$actores.getDetails().add(add);

					RoleDetailsAtts mid = new RoleDetailsAtts();
					mid.setOpName("修改");
					mid.setOpkey("mid");
					mid.setRoleDetails($actores);
					$actores.getDetails().add(mid);

					RoleDetailsAtts del = new RoleDetailsAtts();
					del.setOpName("删除");
					del.setOpkey("del");
					del.setRoleDetails($actores);
					$actores.getDetails().add(del);
					roles.getDetails().add($actores);

					// 频道助理
					RoleDetails $plam = new RoleDetails();
					$plam.setItemUrl("platManager/show.do");
					$plam.setRole(roles);

					add = new RoleDetailsAtts();
					add.setOpName("新增");
					add.setOpkey("add");
					add.setRoleDetails($plam);
					$plam.getDetails().add(add);

					del = new RoleDetailsAtts();
					del.setOpName("删除");
					del.setOpkey("del");
					del.setRoleDetails($plam);
					$plam.getDetails().add(del);
					roles.getDetails().add($plam);

					// 场控人员
					RoleDetails $third = new RoleDetails();
					$third.setItemUrl("thirdManager/show.do");
					$third.setRole(roles);

					add = new RoleDetailsAtts();
					add.setOpName("新增");
					add.setOpkey("add");
					add.setRoleDetails($third);
					$third.getDetails().add(add);

					mid = new RoleDetailsAtts();
					mid.setOpName("修改");
					mid.setOpkey("mid");
					mid.setRoleDetails($third);
					$third.getDetails().add(mid);

					del = new RoleDetailsAtts();
					del.setOpName("删除");
					del.setOpkey("del");
					del.setRoleDetails($third);
					$third.getDetails().add(del);
					roles.getDetails().add($third);

					// 平台流水
					RoleDetails $months = new RoleDetails();
					$months.setItemUrl("months/show.do");
					$months.setRole(roles);

					RoleDetailsAtts save = new RoleDetailsAtts();
					save.setOpName("保存");
					save.setOpkey("save");
					save.setRoleDetails($months);
					$months.getDetails().add(save);
					roles.getDetails().add($months);

					// 提交月报
					RoleDetails $outlay = new RoleDetails();
					$outlay.setItemUrl("outlay/show.do");
					$outlay.setRole(roles);

					save = new RoleDetailsAtts();
					save.setOpName("保存");
					save.setOpkey("save");
					save.setRoleDetails($outlay);
					$outlay.getDetails().add(save);

					RoleDetailsAtts audit = new RoleDetailsAtts();
					audit.setOpName("提交审核");
					audit.setOpkey("audit");
					audit.setRoleDetails($outlay);
					$outlay.getDetails().add(audit);
					roles.getDetails().add($outlay);

					// 线上艺人
					RoleDetails $online = new RoleDetails();
					$online.setItemUrl("actoresOnline/showOnline.do");
					$online.setRole(roles);

					add = new RoleDetailsAtts();
					add.setOpName("新增");
					add.setOpkey("add");
					add.setRoleDetails($online);
					$online.getDetails().add(add);

					mid = new RoleDetailsAtts();
					mid.setOpName("修改");
					mid.setOpkey("mid");
					mid.setRoleDetails($online);
					$online.getDetails().add(mid);

					del = new RoleDetailsAtts();
					del.setOpName("删除");
					del.setOpkey("del");
					del.setRoleDetails($online);
					$online.getDetails().add(del);

					RoleDetailsAtts leave = new RoleDetailsAtts();
					leave.setOpName("离职");
					leave.setOpkey("leave");
					leave.setRoleDetails($online);
					$online.getDetails().add(leave);

					RoleDetailsAtts become = new RoleDetailsAtts();
					become.setOpName("转正");
					become.setOpkey("become");
					become.setRoleDetails($online);
					$online.getDetails().add(become);

					roles.getDetails().add($online);

					// 线下艺人(签约)
					RoleDetails $offline = new RoleDetails();
					$offline.setItemUrl("actoresOnline/showOffline.do");
					$offline.setRole(roles);

					add = new RoleDetailsAtts();
					add.setOpName("新增");
					add.setOpkey("add");
					add.setRoleDetails($offline);
					$offline.getDetails().add(add);

					mid = new RoleDetailsAtts();
					mid.setOpName("修改");
					mid.setOpkey("mid");
					mid.setRoleDetails($offline);
					$offline.getDetails().add(mid);

					del = new RoleDetailsAtts();
					del.setOpName("删除");
					del.setOpkey("del");
					del.setRoleDetails($offline);
					$offline.getDetails().add(del);
					roles.getDetails().add($offline);

					// 助理管理
					RoleDetails $manager = new RoleDetails();
					$manager.setItemUrl("manager/show.do");
					$manager.setRole(roles);

					add = new RoleDetailsAtts();
					add.setOpName("新增");
					add.setOpkey("add");
					add.setRoleDetails($manager);
					$manager.getDetails().add(add);

					mid = new RoleDetailsAtts();
					mid.setOpName("修改");
					mid.setOpkey("mid");
					mid.setRoleDetails($manager);
					$manager.getDetails().add(mid);

					del = new RoleDetailsAtts();
					del.setOpName("删除");
					del.setOpkey("del");
					del.setRoleDetails($manager);
					$manager.getDetails().add(del);

					roles.getDetails().add($manager);
					rolesService.saveRolesBySession(roles);
				}
				// 生成登陆帐号表
				RoleUsers users = usersService.findRoleUsersBySession(emp.getId());
				if (users == null && roles != null) {
					users = new RoleUsers();
					users.setBranchs(emp.getBranchs());
					emp.setPassword(MD5.MD5Encode("a123456"));
					emp.setIslogin(Login.YES);
					users.setUser(emp);
					users.getDetails().add(roles);
					usersService.saveRoleUsersBySession(users);
				}
			}
		} catch (Exception e) {
			logger.error("添加平台同步其他操作错误", e);
		}
	
	}

}
