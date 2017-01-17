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
import com.baofeng.oa.bean.PlatformsOnlineIncomeBean;
import com.baofeng.oa.dao.PlatformsOnlineIncomeDAO;
import com.baofeng.oa.entity.EmpAttendance;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsOnlineIncomeService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.SearchFilter;

@Service("platformsOnlineIncomeService")
public class PlatformsOnlineIncomeServiceImpl implements IPlatformsOnlineIncomeService {

	@Autowired
	private PlatformsOnlineIncomeDAO platformsOnlineIncomeDAO;
	@Autowired
	private IPlatformsActoresService platformsActore;
	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private IEmpAttendanceService empAttendanceService;

	@Override
	public void addPlatformsOnlineIncome(List<PlatformsOnlineIncome> listOn) {
		if (listOn != null && listOn.size() > 0) {
			for (PlatformsOnlineIncome income : listOn) {
				this.platformsOnlineIncomeDAO.addPlatformsOnlineIncome(income);
			}
		}
	}

	@Override
	public void savePlatformsOnlineOutlay(PlatformsOnlineIncome outlay) {
		this.platformsOnlineIncomeDAO.savePlatformsOnlineBySession(outlay);
	}

	/**
	 * 功能：线上艺人收支数据同步验证
	 * */
	@Override
	public boolean findValidation(Integer pactId, Integer branchs, Integer monthsId) {
		return this.platformsOnlineIncomeDAO.findValidation(pactId, branchs, monthsId);
	}

	@Override
	public List<PlatformsOnlineIncomeBean> readAllPlatformsOnlineIncome(String platId, Integer mId, String date, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		List<PlatformsOnlineIncome> list = this.platformsOnlineIncomeDAO.readAllPlatformsOnlineIncome(platId, mId, date1, date2, filter);
		List<PlatformsOnlineIncomeBean> listBean = new ArrayList<PlatformsOnlineIncomeBean>();
		if (list != null && list.size() > 0) {
			for (PlatformsOnlineIncome post : list) {
				PlatformsOnlineIncomeBean bean = new PlatformsOnlineIncomeBean();
				Platforms platforms = this.platformsService.readPlatforms(Integer.valueOf(post.getPlatformsId()));
				bean.setTax(platforms.getTax() != null ? platforms.getTax().toString() : "0.00");
				bean.setAudit(post.getAudit());
				bean.setPactId(post.getPactId());
				bean.setAliasname(post.getAliasname());
				bean.setAudit(post.getAudit());
				bean.setChannel(post.getChannel());
				bean.setChannelId(post.getChannelId());
				bean.setId(post.getId());
				bean.setName(post.getName());
				bean.setPlatforms(post.getPlatforms());
				bean.setPlatformsId(post.getPlatformsId());
				bean.setRemarks(post.getRemarks());

				bean.setRestitutionGift(String.format("%.2f", post.getDuductGift() == null ? 0.00f : post.getDuductGift()));
				bean.setPlatDeduct(String.format("%.2f", post.getPlatDeduct() == null ? 0.00 : post.getPlatDeduct()));
				bean.setPlatSubsidy(String.format("%.2f", post.getPlatSubsidy() == null ? 0.00 : post.getPlatSubsidy()));
				bean.setAttendance(String.valueOf(post.getAttendance() == null ? "0.00" : post.getAttendance().setScale(3, BigDecimal.ROUND_HALF_UP)));
				bean.setDeductTax(String.format("%.2f", post.getDeductTax() == null ? 0.00f : post.getDeductTax()));
				bean.setBgIncome(String.format("%.2f", post.getBgIncome() == null ? 0.00f : post.getBgIncome()));
				bean.setPushMoney(String.format("%.2f", post.getPushMoney() == null ? 0.00f : post.getPushMoney()));
				bean.setNetIncome(String.format("%.2f", post.getNetIncome() == null ? 0.00f : post.getNetIncome()));
				bean.setMinimumGuarantee(String.format("%.2f", post.getMinimumGuarantee() == null ? 0.00f : post.getMinimumGuarantee()));

				bean.setLaborService(String.format("%.2f", post.getLaborService() != null ? post.getLaborService() : new BigDecimal(0.00)));
				bean.setBasicSalaires(String.format("%.2f", post.getBasicSalaires() == null ? 0.00f : post.getBasicSalaires()));
				bean.setPushMoneySalaries(String.format("%.2f", post.getPushMoneySalaires() == null ? 0.00f : post.getPushMoneySalaires()));
				bean.setActoresSalary(String.format("%.2f", post.getActoresSalary() == null ? 0.00f : post.getActoresSalary()));
				bean.setNetProfit(String.format("%.2f", post.getNetIncome() == null ? 0.00f : post.getNetIncome()));

				listBean.add(bean);
			}
		}
		return listBean;
	}

	@Override
	public boolean updateIncome(List<PlatformsOnlineIncome> list) {
		if (list != null && list.size() > 0) {
			for (PlatformsOnlineIncome post : list) {
				if (post.getId() != null && post.getId().intValue() > 0) {
					PlatformsOnlineIncome $post = this.platformsOnlineIncomeDAO.readPlatformsOnlineIncome(post.getId());
					$post.setBgIncome(post.getBgIncome());
					$post.setDeductTax(post.getDeductTax());
					$post.setNetProfit(post.getNetProfit());
					$post.setDuductGift(post.getDuductGift());
					$post.setAttendance(post.getAttendance());
					$post.setPlatDeduct(post.getPlatDeduct());
					$post.setPlatSubsidy(post.getPlatSubsidy());
					$post.setRemarks(post.getRemarks());
					Platforms platforms = this.platformsService.readPlatforms(Integer.valueOf($post.getPlatformsId()));
					$post.make(platforms.getTax() == null ? new BigDecimal(0.00) : platforms.getTax());
					if (!this.platformsOnlineIncomeDAO.saveIncome($post)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public PlatformsOnlineIncome findPlatformsOnlineIncomeBySession(Integer pactId) {
		return this.platformsOnlineIncomeDAO.readPlatformsOnlineIncomeBySession(pactId);
	}

	@Override
	public List<PlatformsOnlineIncome> findPlatformsOnlineIncomeById(Integer pactId, Date date1, Date date2) {
		List<PlatformsActores> listp = this.platformsActore.findPlatformsActoresList(pactId);
		List<PlatformsOnlineIncome> list = new ArrayList<PlatformsOnlineIncome>();
		if (listp != null && listp.size() > 0) {
			for (PlatformsActores platAc : listp) {
				List<PlatformsOnlineIncome> $list = this.platformsOnlineIncomeDAO.readPlatformsOnlineIncomeById(platAc.getId(), date1, date2);
				if ($list != null && $list.size() > 0)
					list.add($list.get(0));
			}
		}
		return list;
	}

	@Override
	public boolean savePlatformsOnlineOutlayBySession(PlatformsOnlineIncome $online) {
		return this.platformsOnlineIncomeDAO.savePlatformsOnlineBySession($online);
	}

	@Override
	public boolean deletePlatformsActores(PlatformsActores pactores) {
		PlatformsOnlineIncome income = this.findPlatformsOnlineIncomeBySession(pactores.getId());
		if (income != null) {
			income.setMonths(null);
			return this.platformsOnlineIncomeDAO.deletePlatformsActores(income);
		}
		return false;
	}

	@Override
	public boolean findActValidation(String actsName, Integer branchs, Integer mid, String platId, String channelId, Date date1, Date date2) {
		return this.platformsOnlineIncomeDAO.findActValidation(actsName, branchs, mid, platId, channelId, date1, date2);
	}

	@Override
	public PlatformsOnlineIncomeBean editOn(PlatformsOnlineIncomeBean bean) {
		if (bean != null && bean.getId() != null && bean.getId().intValue() > 0) {
			PlatformsOnlineIncome income = this.platformsOnlineIncomeDAO.readPlatformsOnlineIncome(bean.getId());
			Platforms platforms = this.platformsService.readPlatforms(Integer.parseInt(income.getPlatformsId()));
			if (income != null) {
				income.setBgIncome(new BigDecimal(bean.getBgIncome() != null && bean.getBgIncome().trim().length() > 0 ? bean.getBgIncome() : "0.00"));
				income.setDeductTax(new BigDecimal(bean.getDeductTax() != null && bean.getDeductTax().trim().length() > 0 ? bean.getDeductTax() : "0.00"));
				income.setDuductGift(new BigDecimal(bean.getRestitutionGift() != null && bean.getRestitutionGift().trim().length() > 0 ? bean.getRestitutionGift() : "0.00"));
				income.setPlatDeduct(new BigDecimal(bean.getPlatDeduct() != null && bean.getPlatDeduct().trim().length() > 0 ? bean.getPlatDeduct() : "0.00"));
				income.setPlatSubsidy(new BigDecimal(bean.getPlatSubsidy() != null && bean.getPlatSubsidy().trim().length() > 0 ? bean.getPlatSubsidy() : "0.00"));
				income.setAttendance(new BigDecimal(bean.getAttendance() != null && bean.getAttendance().trim().length() > 0 ? bean.getAttendance() : "0.00"));
				income.make(platforms.getTax() == null ? new BigDecimal(0.00) : platforms.getTax());

				PlatformsOnlineIncomeBean post = new PlatformsOnlineIncomeBean();
				post.setBasicSalaires(String.format("%.2f", income.getBasicSalaires() == null ? 0.00 : income.getBasicSalaires()));
				post.setPushMoneySalaries(String.format("%.2f", income.getPushMoneySalaires() == null ? 0.00 : income.getPushMoneySalaires()));
				post.setLaborService(String.format("%.2f", income.getLaborService() == null ? 0.00 : income.getLaborService()));
				post.setActoresSalary(String.format("%.2f", income.getActoresSalary() == null ? 0.00 : income.getActoresSalary()));
				post.setNetProfit(String.format("%.2f", income.getNetIncome() == null ? 0.00 : income.getNetIncome()));
				return post;
			}
		}
		return null;
	}

	@Override
	public HSSFWorkbook export(List<PlatformsOnlineIncomeBean> listOn) {
		String[] excelHeader = { "主播艺名", "真实姓名", "频道", "出勤天数", "保底", "当月收入", "分成扣税", "礼物返还", "平台补贴", "平台扣除", "主播提成", "劳务服务费", "主播实际收入", "实际盈亏" };
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
		if (listOn != null && listOn.size() > 0) {
			for (int i = 0; i < listOn.size(); i++) {
				row = sheet.createRow(i + 1);
				row.setRowStyle(style);
				PlatformsOnlineIncomeBean bean = listOn.get(i);
				row.createCell(0).setCellValue(bean.getAliasname());
				row.createCell(1).setCellValue(bean.getName());
				row.createCell(2).setCellValue(bean.getChannel());
				row.createCell(3).setCellValue(bean.getAttendance());
				row.createCell(4).setCellValue(bean.getBasicSalaires());
				row.createCell(5).setCellValue(bean.getBgIncome());
				row.createCell(6).setCellValue(bean.getDeductTax());
				row.createCell(7).setCellValue(bean.getRestitutionGift());
				row.createCell(8).setCellValue(bean.getPlatSubsidy());
				row.createCell(9).setCellValue(bean.getPlatDeduct());
				row.createCell(10).setCellValue(bean.getPushMoneySalaries());
				row.createCell(11).setCellValue(bean.getLaborService());
				row.createCell(12).setCellValue(bean.getActoresSalary());
				row.createCell(13).setCellValue(bean.getNetIncome());
			}
		}
		return wb;
	}

	@Override
	public List<PlatformsOnlineIncome> findPlatformsOnlineList(Integer mid, Date date) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(date);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(date);
		return this.platformsOnlineIncomeDAO.findPlatformsOnlineList(mid, date1, date2);
	}

	@Override
	public boolean findPlatformsOnlineIncemeByName(String name, String aliasname, Integer platId, Date $date1, Date $date2) {
		PlatformsOnlineIncome income = this.platformsOnlineIncomeDAO.findPlatformsOnlineIncemeByName(name, aliasname, platId, $date1, $date2);
		if (income != null)
			return true;
		return false;
	}

	@Override
	public PlatformsOnlineIncome readAllPlatformsOnlineIncome(String channel, Integer branchs) {
		if (channel != null && branchs != null && branchs.intValue() > 0) {
			try {
				PlatformsOnlineIncome onlineIncome = this.platformsOnlineIncomeDAO.readPlatformsOnlineIncomeByName(channel, branchs);
				return onlineIncome;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public boolean addPlatformsOnlineIncome(PlatformsOnlineIncome lineIncome, HttpServletRequest request) {
		if (lineIncome != null) {
			if (lineIncome.getId() != null && lineIncome.getId().intValue() > 0) {
				this.monitorLogService.logsUpdate(request, lineIncome.getName() + "的报表");
			}
			if (lineIncome.getPactId() != null && lineIncome.getAttendance() != null) {
				EmpAttendance empa = this.empAttendanceService.readAttendanceById(lineIncome.getId());
				if (empa != null) {
					empa.setAttendance(Float.valueOf(String.valueOf(lineIncome.getAttendance())));
				}
				this.empAttendanceService.updateEmpAttendance(empa);
			}
			if (this.platformsOnlineIncomeDAO.addOnlineIncome(lineIncome)) {
				return true;
			}
		}
		return false;
	}

}
