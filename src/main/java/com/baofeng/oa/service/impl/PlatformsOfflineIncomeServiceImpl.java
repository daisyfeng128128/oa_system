package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.PlatformsOfflineIncomeBean;
import com.baofeng.oa.dao.PlatformsOfflineIncomeDAO;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.EmpAttendance;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsOfflineIncomeService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.SearchFilter;

@Service("platformsOfflineIncomeService")
public class PlatformsOfflineIncomeServiceImpl implements IPlatformsOfflineIncomeService {

	@Autowired
	private PlatformsOfflineIncomeDAO offlineIncome;
	@Autowired
	private IEmpAttendanceService empAttendanceService;
	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private IPlatformsActoresService actoresService;

	@Override
	public void addPlatformsOfflineIncome(List<PlatformsOfflineIncome> listOn) {
		if (listOn != null && listOn.size() > 0) {
			for (PlatformsOfflineIncome income : listOn) {
				this.offlineIncome.addPlatformsOfflineIncome(income);
			}
		}
	}

	@Override
	public void savePlatformsOfflineOutlay(PlatformsOfflineIncome outlay) {
		this.offlineIncome.savePlatformsOfflineOutlayBySession(outlay);
	}

	/**
	 * 功能：线下艺人收支数据同步验证
	 * */
	@Override
	public boolean findValidation(Integer pactId, Integer branchs, Integer mid, String platId, String channelId, Date date1, Date date2) {
		return this.offlineIncome.findValidation(pactId, branchs, mid, platId, channelId, date1, date2);
	}

	@Override
	public List<PlatformsOfflineIncomeBean> readAllPlatformsOfflineIncome(String platId, Integer mId, String date, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		List<PlatformsOfflineIncome> list = this.offlineIncome.readAllPlatformsOfflineIncome(platId, mId, date1, date2, filter);
		List<PlatformsOfflineIncomeBean> listBean = new ArrayList<PlatformsOfflineIncomeBean>();
		if (list != null && list.size() > 0) {
			BranchOffice branchs = null;
			for (PlatformsOfflineIncome income : list) {
				PlatformsOfflineIncomeBean bean = new PlatformsOfflineIncomeBean();
				Platforms platforms = this.platformsService.readPlatforms(Integer.valueOf(income.getPlatformsId()));
				bean.setAttendance(String.format("%.2f", income.getAttendance() != null ? income.getAttendance() : 0.00f));
				Float attendance = this.empAttendanceService.findAttendanceByNumberAndDate(income.getNumber(), income.getBranchs(), date);
				bean.setTax(platforms.getTax() != null ? platforms.getTax().toString() : "0.00");
				bean.setAttendance(String.format("%.2f", attendance));
				bean.setAudit(income.getAudit());
				bean.setActoresId(income.getPactId());
				bean.setAudit(income.getAudit());
				bean.setChannel(income.getChannel());
				bean.setChannelId(income.getChannelId());
				bean.setId(income.getId());
				bean.setLaborService(String.format("%.2f", income.getLaborService() != null ? income.getLaborService() : new BigDecimal(0.00)));
				bean.setPlatforms(income.getPlatforms());
				bean.setPlatformsId(income.getPlatformsId());
				bean.setRemarks(income.getRemarks());
				bean.setName(income.getName() == null ? "-" : income.getName());
				if (branchs == null)
					branchs = this.branchOfficeService.readBranchOffice(income.getBranchs());
				if (branchs != null)
					bean.setNumber(income.getNumber() == null ? "-" : branchs.getNumberHead() + String.format("%04d", income.getNumber().intValue()));
				else
					bean.setNumber(income.getNumber() == null ? "-" : String.format("%04d", income.getNumber().intValue()));
				bean.setAliasname(income.getAliasname() == null ? "-" : income.getAliasname());
				bean.setIncome(String.format("%.2f", income.getIncome() == null ? 0.00f : income.getIncome()));
				bean.setRestitutionGift(String.format("%.2f", income.getDuductGift() == null ? 0.00f : income.getDuductGift()));
				bean.setTaxPoint(String.format("%.2f", income.getDeductTax() == null ? 0.00f : income.getDeductTax()));
				bean.setPushMoney(String.format("%.2f", income.getPushMoney() == null ? 0.00f : income.getPushMoney()));
				bean.setBasicSalary(String.format("%.2f", income.getBasicSalary() == null ? 0.00f : income.getBasicSalary()));
				bean.setPlatDeduct(String.format("%.2f", income.getPlatDeduct() == null ? 0.00 : income.getPlatDeduct()));
				bean.setPlatSubsidy(String.format("%.2f", income.getPlatSubsidy() == null ? 0.00 : income.getPlatSubsidy()));

				bean.setRealBasicSalary(String.format("%.2f", income.getBasicSalaires() == null ? 0.00 : income.getBasicSalaires()));
				bean.setRealPushMoney(String.format("%.2f", income.getPushMoneySalaires() == null ? 0.00 : income.getPushMoneySalaires()));
				bean.setLaborService(String.format("%.2f", income.getLaborService() == null ? 0.00 : income.getLaborService()));
				bean.setNetIncome(String.format("%.2f", income.getNetIncome() == null ? 0.00 : income.getNetIncome()));
				bean.setNetOffincome(String.format("%.2f", income.getNetOffincome() == null ? 0.00 : income.getNetOffincome()));
				listBean.add(bean);
			}
		}
		return listBean;
	}

	@Override
	public boolean update(List<PlatformsOfflineIncome> list) {
		if (list != null && list.size() > 0) {
			for (PlatformsOfflineIncome post : list) {
				PlatformsOfflineIncome income = this.offlineIncome.readPlatformsOfflineIncome(post.getId());
				Platforms platforms = this.platformsService.readPlatforms(Integer.parseInt(income.getPlatformsId()));
				income.setAttendance(post.getAttendance());
				income.setIncome(post.getIncome());
				income.setDeductTax(post.getDeductTax());
				income.setDuductGift(post.getDuductGift());
				income.setPlatDeduct(post.getPlatDeduct());
				income.setPlatSubsidy(post.getPlatSubsidy());
				income.setRemarks(post.getRemarks());
				post = income;
				income.make(platforms.getTax() == null ? new BigDecimal(0.00) : platforms.getTax());
				if (this.offlineIncome.update(post)) {
					if (income.getPactId() != null && income.getAttendance() != null) {
						EmpAttendance empa = this.empAttendanceService.readEmpAttendanceByDateByNumber(income.getNumber(), income.getBranchs(), income.getCreateDT());
						if (empa != null) {
							empa.setAttendance(income.getAttendance());
						} else {
							empa = new EmpAttendance();
							empa.setNumber(income.getNumber());
							empa.setAttendance(income.getAttendance());
							empa.setBranchs(income.getBranchs());
							empa.setCreateDT(income.getCreateDT());
							PlatformsActores pa = this.platformsService.readPlatformsActores(Integer.valueOf(income.getPactId()));
							if (pa != null) {
								empa.setEmployee(pa.getActores().getEmployee());
							}
						}
						this.empAttendanceService.updateEmpAttendance(empa);
					}
				}
			}
		}
		return true;
	}

	@Override
	public PlatformsOfflineIncome findPlatformsOfflineIncomeBySession(Integer pactId) {
		return this.offlineIncome.readPlatformsOfflineIncomeBySession(pactId);
	}

	@Override
	public boolean savePlatformsOfflineIncomeBySession(PlatformsOfflineIncome $offline) {
		return this.offlineIncome.savePlatformsOfflineOutlayBySession($offline);
	}

	@Override
	public boolean deletePlatformsActores(PlatformsActores pactores) {
		PlatformsOfflineIncome income = this.findPlatformsOfflineIncomeBySession(pactores.getId());
		if (income != null) {
			income.setMonths(null);
			return this.offlineIncome.deletePlatformsActores(income);
		}
		return false;
	}

	@Override
	public boolean findActValidation(String actsName, Integer branchs, Integer mid, String platId, String channelId, Date date1, Date date2) {
		return this.offlineIncome.findActValidation(actsName, branchs, mid, platId, channelId, date1, date2);
	}

	@Override
	public PlatformsOfflineIncomeBean editOff(PlatformsOfflineIncomeBean bean) {
		if (bean != null && bean.getId() != null && bean.getId().intValue() > 0) {
			PlatformsOfflineIncome income = this.offlineIncome.readPlatformsOfflineIncome(bean.getId());
			Platforms platforms = this.platformsService.readPlatforms(Integer.parseInt(income.getPlatformsId()));
			if (income != null) {
				income.setIncome(new BigDecimal(bean.getIncome() != null ? bean.getIncome() : "0.00"));
				income.setDeductTax(new BigDecimal(bean.getTaxPoint() != null ? bean.getTaxPoint() : "0.00"));
				income.setDuductGift(new BigDecimal(bean.getRestitutionGift() != null ? bean.getRestitutionGift() : "0.00"));
				income.setPlatDeduct(new BigDecimal(bean.getPlatDeduct() != null ? bean.getPlatDeduct() : "0.00"));
				income.setPlatSubsidy(new BigDecimal(bean.getPlatSubsidy() != null ? bean.getPlatSubsidy() : "0.00"));
				income.make(platforms.getTax() == null ? new BigDecimal(0.00) : platforms.getTax());

				PlatformsOfflineIncomeBean post = new PlatformsOfflineIncomeBean();
				post.setRealBasicSalary(String.format("%.2f", income.getBasicSalaires() == null ? 0.00 : income.getBasicSalaires()));
				post.setRealPushMoney(String.format("%.2f", income.getPushMoneySalaires() == null ? 0.00 : income.getPushMoneySalaires()));
				post.setLaborService(String.format("%.2f", income.getLaborService() == null ? 0.00 : income.getLaborService()));
				post.setNetIncome(String.format("%.2f", income.getNetIncome() == null ? 0.00 : income.getNetIncome()));
				post.setNetOffincome(String.format("%.2f", income.getNetOffincome() == null ? 0.00 : income.getNetOffincome()));
				return post;
			}
		}
		return null;
	}

	@Override
	public HSSFWorkbook export(List<PlatformsOfflineIncomeBean> listOff) {
		String[] excelHeader = { "员工号", "艺名", "姓名", "频道", "出勤天数", "当月收入", "分成扣税", "公司礼物返还", "平台补贴", "平台扣除", "底薪", "提成", "劳务服务费", "主播实际收入", "艺人盈亏" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("频道管理(线下)");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(i, 32 * 150);
		}
		if (listOff != null && listOff.size() > 0) {
			for (int i = 0; i < listOff.size(); i++) {
				row = sheet.createRow(i + 1);
				row.setRowStyle(style);
				PlatformsOfflineIncomeBean bean = listOff.get(i);
				row.createCell(0).setCellValue(bean.getNumber());
				row.createCell(1).setCellValue(bean.getAliasname());
				row.createCell(2).setCellValue(bean.getName());
				row.createCell(3).setCellValue(bean.getChannel());
				row.createCell(4).setCellValue(bean.getAttendance());
				row.createCell(5).setCellValue(bean.getIncome());
				row.createCell(6).setCellValue(bean.getTaxPoint());
				row.createCell(7).setCellValue(bean.getRestitutionGift());
				row.createCell(8).setCellValue(bean.getPlatSubsidy());
				row.createCell(9).setCellValue(bean.getPlatDeduct());
				row.createCell(10).setCellValue(bean.getRealBasicSalary());
				row.createCell(11).setCellValue(bean.getRealPushMoney());
				row.createCell(12).setCellValue(bean.getLaborService());
				row.createCell(13).setCellValue(bean.getNetOffincome());
				row.createCell(14).setCellValue(bean.getNetIncome());
			}
		}
		return wb;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PlatformsOfflineIncome> findPlatformsOfflineList(Integer mid, Date date) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(date);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(date);
		return (List<PlatformsOfflineIncome>) this.offlineIncome.findPlatformsOnlineList(mid, date1, date2);
	}

	@Override
	public List<PlatformsOfflineIncome> findPlatformsOfflineIncomeById(Integer actId, Date date1, Date date2) {
		List<PlatformsActores> list = this.offlineIncome.readAllPlatformsActores(actId);
		List<PlatformsOfflineIncome> listOff = new ArrayList<PlatformsOfflineIncome>();
		if (list != null && list.size() > 0) {
			for (PlatformsActores actores : list) {
				PlatformsOfflineIncome income = this.offlineIncome.findPlatformsOfflineIncomeById(actores.getId(), date1, date2);
				if (income != null)
					listOff.add(income);
			}
		}
		return listOff;
	}

	@Override
	public boolean findPlatformsOfflineIncomeByNumber(Integer number, Integer platId, Date $date1, Date $date2) {
		PlatformsOfflineIncome income = this.offlineIncome.findPlatformsOfflineIncomeByNumber(number, platId, $date1, $date2);
		if (income != null)
			return true;
		return false;
	}

	public PlatformsOfflineIncome readAllPlatformsOfflineIncome(String channel, Integer branchs) {
		if (channel != null && branchs != null && branchs.intValue() > 0) {
			try {
				PlatformsOfflineIncome offlineIncome = this.offlineIncome.readPlatformsOfflineIncomeByChannel(channel, branchs);
				return offlineIncome;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public boolean addPlatformsOfflineIncome(PlatformsOfflineIncome lineIncome, HttpServletRequest request) {
		if (lineIncome != null) {
			if (lineIncome.getId() != null && lineIncome.getId().intValue() > 0) {
				this.monitorLogService.logsUpdate(request, lineIncome.getName() + "的报表");
			}
			if (lineIncome.getPactId() != null && lineIncome.getAttendance() != null) {
				EmpAttendance empa = this.empAttendanceService.readEmpAttendanceByDateByNumber(lineIncome.getNumber(), lineIncome.getBranchs(), lineIncome.getCreateDT());
				if (empa != null) {
					empa.setAttendance(lineIncome.getAttendance());
				}
				this.empAttendanceService.updateEmpAttendance(empa);
			}
			if (this.offlineIncome.addOfflineIncome(lineIncome)) {
				return true;
			}
		}
		return false;
	}

}
