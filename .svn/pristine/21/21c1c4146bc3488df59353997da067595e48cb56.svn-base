package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.ActoresBean;
import com.baofeng.oa.bean.FinSalariesAdjustsBean;
import com.baofeng.oa.dao.FinSalariesAdjustsDAO;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.Adjust;
import com.baofeng.oa.entity.BaseEnums.Audit;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.FinSalariesAdjusts;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IFinSalariesAdjustsService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("finSalariesAdjustsService")
public class FinSalariesAdjustsServiceImpl implements IFinSalariesAdjustsService {
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IActoresService actoresService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private FinSalariesAdjustsDAO finSalariesAdjustsDAO;
	@Autowired
	private IPlatformsActoresService platformsActoresService;
	
	@Override
	public List<ActoresBean> readPlatformsActores(Actores actores, List<RoleDetailsAtts> platList) {
		return this.finSalariesAdjustsDAO.readPlatformsActores(actores, platList);
	}

	@Override
	public boolean saveFinSalariesAdjusts(FinSalariesAdjustsBean bean, RoleUsers users, Integer branchs, HttpServletRequest request) {
		FinSalariesAdjusts adjust = new FinSalariesAdjusts();
		Platforms platforms = this.platformsService.readPlatforms(Integer.parseInt(bean.getPlatName()));
		Actores actores = this.actoresService.readActoresById(Integer.parseInt(bean.getId()));
		Employee employee = this.employeeService.readEmployee(users.getUser().getId());
		if (actores != null) {
			adjust.setBranchs(branchs);
			adjust.setRealname(actores.getRealname());
			adjust.setAliasname(actores.getAliasname());
			adjust.setSex(actores.getSex());
			adjust.setPlatId(platforms.getId().toString());
			adjust.setPlatName(platforms.getPlatName());
			adjust.setGenre(actores.getGenre());
			if (actores.getGenre() == LineGenres.ONLINE)
				adjust.setSbasicSalary(new BigDecimal(actores.getMinimumGuarantee() == null ? 0.00 : actores.getMinimumGuarantee()));
			else
				adjust.setSbasicSalary(new BigDecimal(actores.getBasicSalary() == null ? 0.00 : actores.getBasicSalary()));
			adjust.setSpushMoney(new BigDecimal(actores.getPushMoney() == null ? 0.00 : actores.getPushMoney()));
			adjust.setNbasicSalary(new BigDecimal(bean.getNbasicSalary()));
			adjust.setNpushMoney(new BigDecimal(bean.getNpushMoney()));
			adjust.setAdjustMsg(bean.getAdjustMsg());
			adjust.setActores(actores);
			adjust.setAdjust(Adjust.UNKNOWN);
			adjust.setEmployee(employee);
			if (this.finSalariesAdjustsDAO.saveFinSalariesAdjusts(adjust)) {
				this.monitorLogService.logsAdd(request, "调薪申请" + (adjust.getRealname() == null ? adjust.getAliasname() : adjust.getRealname()) + " ID: " + adjust.getId());
				return true;
			}
		}
		return false;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter, String ads, List<RoleDetailsAtts> platList) {
		NPageResult rows = this.finSalariesAdjustsDAO.readPages(pageSize, curPage, sortName, sortOrder, filter, ads, platList);
		if (rows != null && rows.getData().size() > 0) {
			List<FinSalariesAdjustsBean> beanList = new ArrayList<FinSalariesAdjustsBean>();
			for (Object o : rows.getData()) {
				FinSalariesAdjusts adjust = (FinSalariesAdjusts) o;
				FinSalariesAdjustsBean bean = new FinSalariesAdjustsBean();
				bean.setId(adjust.getId().toString());
				bean.setRealname(adjust.getRealname());
				bean.setAliasname(adjust.getAliasname());
				bean.setSex(adjust.getSex() == Sex.WOMAN ? "女" : "男");
				bean.setPlatName(adjust.getPlatName());
				bean.setGenre(adjust.getGenre() == LineGenres.ONLINE ? "线上艺人" : "线下艺人");
				bean.setSbasicSalary(adjust.getSbasicSalary().toString());
				bean.setSpushMoney(String.format("%d%% %n", adjust.getSpushMoney() == null ? 0 : adjust.getSpushMoney().intValue()));
				bean.setNbasicSalary(adjust.getNbasicSalary().toString());
				bean.setNpushMoney(String.format("%d%% %n", adjust.getNpushMoney() == null ? 0 : adjust.getNpushMoney().intValue()));
				bean.setAdjustMsg(adjust.getAdjustMsg());
				bean.setAdjust(adjust.getAdjust() == Adjust.UNKNOWN ? "待审" : (adjust.getAdjust() == Adjust.PASSED ? "通过" : "拒绝"));
				bean.setEmployee(adjust.getEmployee() == null ? "" : adjust.getEmployee().getName());
				bean.setCreateDT(adjust.getCreateDT());
				bean.setPassTime(adjust.getPassTime());
				beanList.add(bean);
			}
			rows.setData(beanList);
		}
		return rows;
	}

	@Override
	public FinSalariesAdjusts readFinSalariesAdjuest(String id) {
		return this.finSalariesAdjustsDAO.readFinSalariesAdjuest(id);
	}

	@Override
	public boolean updatePassedFinSalariesAdjusts(String ids, HttpServletRequest request) {
		String[] $ids = ids.split(",");
		for (String id : $ids) {
			FinSalariesAdjusts adjust = this.readFinSalariesAdjuest(id);
			if (adjust != null) {
				adjust.setAdjust(Adjust.PASSED);
				adjust.setPassTime(new Date());
				if (this.finSalariesAdjustsDAO.saveFinSalariesAdjusts(adjust)) {
					this.workManagers.onEvents(WorkNames.MIDADJUST, adjust);
					this.monitorLogService.logsUpdate(request, "通过调薪申请" + adjust.getRealname() + " ID: " + adjust.getId());
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean updateRejectFinSalariesAdjusts(String ids, HttpServletRequest request) {
		String[] $ids = ids.split(",");
		for (String id : $ids) {
			FinSalariesAdjusts adjust = this.readFinSalariesAdjuest(id);
			if (adjust != null) {
				adjust.setAdjust(Adjust.FAILED);
				if (this.finSalariesAdjustsDAO.saveFinSalariesAdjusts(adjust)) {
					this.monitorLogService.logsUpdate(request, "拒绝调薪申请" + adjust.getRealname() + " ID: " + adjust.getId());
				}
			}
		}
		return true;
	}

	@Override
	public boolean deleteFinSalariesAdjusts(String ids, HttpServletRequest request) {
		String[] $ids = ids.split(",");
		for (String id : $ids) {
			FinSalariesAdjusts adjust = this.readFinSalariesAdjuest(id);
			if (adjust != null) {
				adjust.setStatus(EntityStatus.DELETED);
				if (this.finSalariesAdjustsDAO.saveFinSalariesAdjusts(adjust)) {
					this.monitorLogService.logsDelete(request, "删除调薪申请" + adjust.getRealname() + " ID: " + adjust.getId());
				}
			}
		}
		return true;
	}

	@Override
	public boolean checkUp(Integer id) {
		if(id != null && id.intValue() > 0){
			List<PlatformsActores> pacList = this.platformsActoresService.readPlatformsActoresByActoresId(id);
			Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(new Date());
			Date date2 = CustomDateUtils.Handler.currentMonthEndDay(new Date() );
			if(pacList != null  && pacList.size() > 0){
				for(PlatformsActores pact : pacList){
					if(pact.getPlat() != null){
						PlatformsMonthsOutlay outlay  = this.finSalariesAdjustsDAO.readPlatformsMonthsOutlay(pact.getPlat().getId(),date1,date2,pact.getActores().getBranchs());
						if(outlay != null && (outlay.getAudit() == Audit.PASS || outlay.getAudit() == Audit.AUDIT || outlay.getAudit() == Audit.FINALPASS)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
