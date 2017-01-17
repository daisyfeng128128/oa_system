package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.oa.bean.FinSalariesOnlineBean;
import com.baofeng.oa.dao.FinSalariesOnlineDAO;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Department;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.oa.entity.FinSalariesOnline;
import com.baofeng.oa.entity.FinSalariesTalent;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IFinSalariesOnlineService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("finSalariesOnlineService")
public class FinSalariesOnlineServiceImpl implements IFinSalariesOnlineService {
	@Autowired
	private FinSalariesOnlineDAO finSalariesOnlineDAO;
	@Autowired
	private IActoresService actoresService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private IPlatformsMonthsOutlayService platformsMonthsOutlayService;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	@Override
	public void updateFinPlatformsIncome(FinPlatformsIncome finIncome) {
		if (finIncome != null) {
			this.finSalariesOnlineDAO.updateFinPlatformsIncome(finIncome);
		}
	}

	@Override
	public void addSalary(List<FinSalariesOnline> listCheck) {
		if (listCheck != null && listCheck.size() > 0) {
			for (FinSalariesOnline post : listCheck) {
				this.finSalariesOnlineDAO.addSalary(post);
			}
		}
	}

	@Override
	public boolean findValidation(String name, Integer id, Date date1, Date date2) {
		return this.finSalariesOnlineDAO.findValidation(name, id, date1, date2);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String date, Integer type, String fastArg, Integer all, String plat, String sex,
			SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		String name = "";
		if (type == 4) {
			name = Actores.class.getName();
		} else if (type == 3) {
			name = Managers.class.getName();
		}
		List<Integer> listId = new ArrayList<Integer>();
		if (plat != null && plat.trim().length() > 0) {
			listId = this.finSalariesOnlineDAO.readFinPlatformsIncome(plat);
		}
		NPageResult page = this.finSalariesOnlineDAO.readPages(pageSize, curPage, sortName, sortOrder, date1, date2, name, fastArg, all, listId, sex, filter);
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			List<FinSalariesOnlineBean> listBean = new ArrayList<FinSalariesOnlineBean>();
			for (Object o : page.getData()) {
				FinSalariesOnline post = (FinSalariesOnline) o;
				FinSalariesOnlineBean bean = new FinSalariesOnlineBean();
				bean.setBilled(post.getBilled().toString());
				bean.setAliasname(post.getAliasname());
				bean.setRealExpenditure(String.format("%.2f", post.getRealExpenditure() != null ? post.getRealExpenditure() : 0.00));
				bean.setBasicSalary(String.format("%.2f", post.getBasicSalary() != null ? post.getBasicSalary() : 0.00));
				bean.setId(post.getId());
				if (post.getDetails() != null && post.getDetails().size() > 1)
					bean.setName("<span style='color:#e35d00'>" + post.getName() + "</span>");
				else
					bean.setName(post.getName());
				bean.setRealitySalary(String.format("%.2f", post.getRealitySalary()));
				bean.setRemark(post.getRemarks());
				if (Actores.class.getName().equals(post.getEntityClass())) {
					Actores actores = this.actoresService.readActoresById(post.getEntityId());
					if (actores != null) {
						if (actores.getBankCard() != null) {
							bean.setBankCard(actores.getBankCard().replaceAll("(.{4})", "$1 "));
						}
						bean.setBankAddress(actores.getBankAddress());
					}
				} else if (Managers.class.getName().equals(post.getEntityClass())) {
					Managers manager = this.managerService.readManagers(post.getEntityId());
					if (manager != null) {
						if (manager.getBankCard() != null)
							bean.setBankCard(manager.getBankCard().replaceAll("(.{4})", "$1 "));
						bean.setBankAddress(manager.getBankCardAccount());
					}
				}
				listBean.add(bean);
			}
			page.setData(listBean);
		}
		return page;
	}

	@Override
	public BigDecimal findSumOnlineActores(Integer branchs, Class<Actores> clazz, Date $date1, Date $date2) {
		return this.finSalariesOnlineDAO.findSumOnlineActores(branchs, clazz, $date1, $date2);
	}

	@Override
	public BigDecimal findSumOnlineManagers(Integer branchs, Class<Managers> clazz, Date $date1, Date $date2) {
		return this.finSalariesOnlineDAO.findSumOnlineManagers(branchs,clazz, $date1, $date2);
	}

	@Override
	public FinSalariesOnline readFinSalariesOnline(String id) {
		FinSalariesOnline online = this.finSalariesOnlineDAO.readFinSalariesOnline(id);
		if (online != null) {
			return online;
		}
		return null;
	}

	@Override
	public boolean addSalariesOnline(FinSalariesOnline post) {
		if (post != null) {
			return this.finSalariesOnlineDAO.addSalary(post);
		}
		return false;
	}

	@Override
	public FinSalariesOnline findFinSalariesOnlineByActores(Integer id, String name, Date $date1, Date $date2) {
		return this.finSalariesOnlineDAO.findFinSalariesOnlineByActores(id, name, $date1, $date2);
	}

	@Override
	public boolean saveFinSalariesOnline(FinSalariesOnline salaries) {
		return this.finSalariesOnlineDAO.saveFinSalariesOnline(salaries);
	}

	@Override
	public FinPlatformsIncome findPlatformsIncomeById(Integer id, String platformsId) {
		return this.finSalariesOnlineDAO.findPlatformsIncomeById(id, platformsId);
	}

	@Override
	public void savePlatformsIncome(FinPlatformsIncome incomeDetails) {
		this.finSalariesOnlineDAO.savePlatformsIncome(incomeDetails);
	}

	@Override
	public boolean updateFinSalariesOnline(List<String> listId) {
		if (listId != null && listId.size() > 0) {
			for (String id : listId) {
				if (id != null && id.trim().length() > 0) {
					FinSalariesOnline online = this.finSalariesOnlineDAO.readFinSalariesOnline(id);
					if (online != null && online.getBilled() != Grant.UNKNOWN) {
						online.setBilled(Grant.PUBLISHED);
						if (!this.finSalariesOnlineDAO.addSalary(online)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public List<FinSalariesOnline> readAllFinSalariesOnlineManager(String date, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		return this.finSalariesOnlineDAO.readAllFinSalariesOnlineManager(date1, date2, filter);
	}

	@Override
	public List<FinSalariesOnline> readAllFinSalariesOnlineActores(String date, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		return this.finSalariesOnlineDAO.readAllFinSalariesOnlineActores(date1, date2, filter);
	}

	@Override
	public HSSFWorkbook export(List<FinSalaries> listOff, List<FinSalaries> listActoresOff, List<FinSalariesOnline> listOnline, List<FinSalariesOnline> listActoresOnline,
			List<FinSalariesTalent> listTalent) {
		String[] excelHeader = { "员工号", "姓名", "艺名", "部门", "职位", "工行", "交行", "实发工资", "公司实际支出工资", "发放状态", "工资卡账号", "开户行地址" };
		String[] $excelHeader = { "姓名", "艺名", "保底", "实发工资", "公司实际支出工资", "发放状态", "工资卡账号", "开户行地址" };
		String[] talentHeader = { "姓名", "艺名", "性别", "星探工资", "发放状态", "工资卡账号", "开户行地址" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("频道管理(线下)");
		HSSFSheet sheet1 = wb.createSheet("艺人主播(线下)");
		HSSFSheet sheet2 = wb.createSheet("频道管理(线上)");
		HSSFSheet sheet3 = wb.createSheet("艺人主播(线上)");
		HSSFSheet sheet4 = wb.createSheet("星探");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFRow row1 = sheet1.createRow((int) 0);
		HSSFRow row2 = sheet2.createRow((int) 0);
		HSSFRow row3 = sheet3.createRow((int) 0);
		HSSFRow row4 = sheet4.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		BranchOffice branchs = null;
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
				FinSalaries finSalaries = listOff.get(i);
				if (branchs == null)
					branchs = this.branchOfficeService.readBranchOffice(finSalaries.getBranchs());
				if (branchs != null)
					row.createCell(0).setCellValue(branchs.getNumberHead() + String.format("%04d", finSalaries.getNumber()));
				else
					row.createCell(0).setCellValue(String.format("%04d", finSalaries.getNumber()));
				row.createCell(1).setCellValue(finSalaries.getName());
				row.createCell(2).setCellValue(finSalaries.getAliasname());
				row.createCell(3).setCellValue(finSalaries.getDeptName());
				row.createCell(4).setCellValue(finSalaries.getPositionsName());
				row.createCell(5).setCellValue(finSalaries.getIcbcBank() != null ? finSalaries.getIcbcBank().toString() : "0.0");
				row.createCell(6).setCellValue(finSalaries.getBocomBank() != null ? finSalaries.getBocomBank().toString() : "0.0");
				row.createCell(7).setCellValue(finSalaries.getRealitySalary() != null ? finSalaries.getRealitySalary().toString() : "0.0");
				row.createCell(8).setCellValue(finSalaries.getRealExpenditure() != null ? finSalaries.getRealExpenditure().toString() : "0.0");
				row.createCell(9).setCellValue(finSalaries.getBilled() == Grant.UNBILLED ? "未发" : finSalaries.getBilled() == Grant.PUBLISHED ? "已发" : "未通过");
				row.createCell(10).setCellValue(finSalaries.getEmployee().getBankCard());
				row.createCell(11).setCellValue(finSalaries.getEmployee().getBankAddress());
			}
		}

		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row1.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
			sheet1.autoSizeColumn(i);
			sheet1.setColumnWidth(i, 32 * 150);
		}

		if (listActoresOff != null && listActoresOff.size() > 0) {
			for (int i = 0; i < listActoresOff.size(); i++) {
				row1 = sheet1.createRow(i + 1);
				row1.setRowStyle(style);
				FinSalaries finSalaries = listActoresOff.get(i);
				if (branchs == null)
					branchs = this.branchOfficeService.readBranchOffice(finSalaries.getBranchs());
				if (branchs != null)
					row1.createCell(0).setCellValue(branchs.getNumberHead() + String.format("%04d", finSalaries.getNumber()));
				else
					row1.createCell(0).setCellValue(String.format("%04d", finSalaries.getNumber()));
				row1.createCell(1).setCellValue(finSalaries.getName());
				row1.createCell(2).setCellValue(finSalaries.getAliasname());
				row1.createCell(3).setCellValue(finSalaries.getDeptName());
				row1.createCell(4).setCellValue(finSalaries.getPositionsName());
				row1.createCell(5).setCellValue(finSalaries.getIcbcBank() != null ? finSalaries.getIcbcBank().toString() : "0.0");
				row1.createCell(6).setCellValue(finSalaries.getBocomBank() != null ? finSalaries.getBocomBank().toString() : "0.0");
				row1.createCell(7).setCellValue(finSalaries.getRealitySalary() != null ? finSalaries.getRealitySalary().toString() : "0.0");
				row1.createCell(8).setCellValue(finSalaries.getRealExpenditure() != null ? finSalaries.getRealExpenditure().toString() : "0.0");
				row1.createCell(9).setCellValue(finSalaries.getBilled() == Grant.UNBILLED ? "未发" : finSalaries.getBilled() == Grant.PUBLISHED ? "已发" : "未通过");
				row1.createCell(10).setCellValue(finSalaries.getEmployee().getBankCard());
				row1.createCell(11).setCellValue(finSalaries.getEmployee().getBankAddress());
			}
		}

		for (int i = 0; i < $excelHeader.length; i++) {
			HSSFCell cell = row2.createCell(i);
			cell.setCellValue($excelHeader[i]);
			cell.setCellStyle(style);
			sheet2.autoSizeColumn(i);
			sheet2.setColumnWidth(i, 32 * 150);
		}

		if (listOnline != null && listOnline.size() > 0) {
			for (int i = 0; i < listOnline.size(); i++) {
				row2 = sheet2.createRow(i + 1);
				row2.setRowStyle(style);
				FinSalariesOnline finSalariesOnline = listOnline.get(i);
				Managers manager = this.managerService.readManagers(finSalariesOnline.getEntityId());
				if (manager != null) {
					row2.createCell(6).setCellValue(manager.getBankCard() != null ? manager.getBankCard().replaceAll("(.{4})", "$1 ") : "");
					row2.createCell(7).setCellValue(manager.getBankCardAccount() != null ? manager.getBankCardAccount() : "");
				}
				row2.createCell(0).setCellValue(finSalariesOnline.getName());
				row2.createCell(1).setCellValue(finSalariesOnline.getAliasname());
				row2.createCell(2).setCellValue(finSalariesOnline.getBasicSalary() != null ? finSalariesOnline.getBasicSalary().toString() : "0.0");
				row2.createCell(3).setCellValue(finSalariesOnline.getRealitySalary() != null ? finSalariesOnline.getRealitySalary().toString() : "0.0");
				row2.createCell(4).setCellValue(finSalariesOnline.getRealExpenditure() != null ? finSalariesOnline.getRealExpenditure().toString() : "0.0");
				row2.createCell(5).setCellValue(finSalariesOnline.getBilled() == Grant.UNBILLED ? "未发" : finSalariesOnline.getBilled() == Grant.PUBLISHED ? "已发" : "未通过");
			}
		}

		for (int i = 0; i < $excelHeader.length; i++) {
			HSSFCell cell = row3.createCell(i);
			cell.setCellValue($excelHeader[i]);
			cell.setCellStyle(style);
			sheet3.autoSizeColumn(i);
			sheet3.setColumnWidth(i, 32 * 150);
		}
		if (listActoresOnline != null && listActoresOnline.size() > 0) {
			for (int i = 0; i < listActoresOnline.size(); i++) {
				row3 = sheet3.createRow(i + 1);
				row3.setRowStyle(style);
				FinSalariesOnline finSalariesOnline = listActoresOnline.get(i);
				Actores actores = this.actoresService.readActoresById(finSalariesOnline.getEntityId());
				if (actores != null) {
					row3.createCell(6).setCellValue(actores.getBankCard() != null ? actores.getBankCard().replaceAll("(.{4})", "$1 ") : "");
					row3.createCell(7).setCellValue(actores.getBankAddress() != null ? actores.getBankAddress() : "");
				}
				row3.createCell(0).setCellValue(finSalariesOnline.getName());
				row3.createCell(1).setCellValue(finSalariesOnline.getAliasname());
				row3.createCell(2).setCellValue(finSalariesOnline.getBasicSalary() != null ? finSalariesOnline.getBasicSalary().toString() : "0.0");
				row3.createCell(3).setCellValue(finSalariesOnline.getRealitySalary() != null ? finSalariesOnline.getRealitySalary().toString() : "0.0");
				row3.createCell(4).setCellValue(finSalariesOnline.getRealExpenditure() != null ? finSalariesOnline.getRealExpenditure().toString() : "0.0");
				row3.createCell(5).setCellValue(finSalariesOnline.getBilled() == Grant.UNBILLED ? "未发" : finSalariesOnline.getBilled() == Grant.PUBLISHED ? "已发" : "未通过");
			}
		}
		for (int i = 0; i < talentHeader.length; i++) {
			HSSFCell cell = row4.createCell(i);
			cell.setCellValue(talentHeader[i]);
			cell.setCellStyle(style);
			sheet4.autoSizeColumn(i);
			sheet4.setColumnWidth(i, 32 * 150);
		}
		if (listTalent != null && listTalent.size() > 0) {
			for (int i = 0; i < listTalent.size(); i++) {
				row4 = sheet4.createRow(i + 1);
				row4.setRowStyle(style);
				FinSalariesTalent FinSalariesTalent = listTalent.get(i);
				row4.createCell(0).setCellValue(FinSalariesTalent.getName());
				row4.createCell(1).setCellValue(FinSalariesTalent.getAliasname());
				row4.createCell(2).setCellValue(FinSalariesTalent.getSex() == Sex.WOMAN ? "女" : "男");
				row4.createCell(3).setCellValue(FinSalariesTalent.getTaSalary() != null ? FinSalariesTalent.getTaSalary().toString() : "0.0");
				row4.createCell(4).setCellValue(FinSalariesTalent.getBill() == Grant.UNBILLED ? "未发" : FinSalariesTalent.getBill() == Grant.PUBLISHED ? "已发" : "未通过");
				row4.createCell(5).setCellValue(FinSalariesTalent.getBankCard());
				row4.createCell(6).setCellValue(FinSalariesTalent.getBankAddress());
			}
		}
		return wb;
	}

	@Override
	public List<FinSalariesOnline> findAllFinSalariesOnline(Date $date1, Date $date2) {
		return this.finSalariesOnlineDAO.findAllFinSalariesOnline($date1, $date2);
	}

	@Override
	public boolean addFinSalariesOnline(FinSalariesOnline fin) {
		if (fin != null && fin.getId() != null && fin.getId().intValue() > 0) {
			FinSalariesOnline $fin = this.finSalariesOnlineDAO.readFinSalariesOnline(fin.getId().toString());
			if ($fin != null) {
				BigDecimal realSalary = new BigDecimal(0.00);
				realSalary = $fin.getRealitySalary().add($fin.getOtherDeduct() == null ? new BigDecimal(0.00) : $fin.getOtherDeduct())
						.subtract($fin.getOtherSubsidy() != null ? $fin.getOtherSubsidy() : new BigDecimal(0.00));
				realSalary = realSalary.subtract(fin.getOtherDeduct()).add(fin.getOtherSubsidy());
				$fin.setOtherDeduct(fin.getOtherDeduct());
				$fin.setOtherSubsidy(fin.getOtherSubsidy());
				$fin.setRemarks(fin.getRemarks());

				$fin.setRealExpenditure(realSalary);

				$fin.setRealitySalary(realSalary);
				fin = $fin;

			}
		}
		if (this.finSalariesOnlineDAO.saveFinSalariesOnline(fin)) {
			return true;
		}
		return false;
	}

	@Override
	public List<FinPlatformsIncome> findIncomeList(Integer salariesId) {
		return this.finSalariesOnlineDAO.findIncomeList(salariesId);
	}

	@Override
	public List<FinSalariesOnline> readAllFinSalariesOnline(String date, String plat, String sex, Integer branchs) {
		List<Integer> listId = new ArrayList<Integer>();
		if (plat != null && plat.trim().length() > 0) {
			listId = this.finSalariesOnlineDAO.readFinPlatformsIncome(plat);
		}
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		return this.finSalariesOnlineDAO.readAllFinSalariesOnline(date1, date2, listId, sex, branchs);
	}

	@Override
	public HSSFWorkbook partExcel(List<FinSalariesOnline> list) {
		String[] $excelHeader = { "姓名", "艺名", "保底", "实发工资", "公司实际支出工资", "发放状态", "工资卡账号", "开户行地址" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet3 = wb.createSheet("艺人主播(线上)");
		HSSFRow row3 = sheet3.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		for (int i = 0; i < $excelHeader.length; i++) {
			HSSFCell cell = row3.createCell(i);
			cell.setCellValue($excelHeader[i]);
			cell.setCellStyle(style);
			sheet3.autoSizeColumn(i);
			sheet3.setColumnWidth(i, 32 * 150);
		}
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				row3 = sheet3.createRow(i + 1);
				row3.setRowStyle(style);
				FinSalariesOnline finSalariesOnline = list.get(i);
				Actores actores = this.actoresService.readActoresById(finSalariesOnline.getEntityId());
				if (actores != null) {
					row3.createCell(6).setCellValue(actores.getBankCard() != null ? actores.getBankCard().replaceAll("(.{4})", "$1 ") : "");
					row3.createCell(7).setCellValue(actores.getBankAddress() != null ? actores.getBankAddress() : "");
				}
				row3.createCell(0).setCellValue(finSalariesOnline.getName());
				row3.createCell(1).setCellValue(finSalariesOnline.getAliasname());
				row3.createCell(2).setCellValue(finSalariesOnline.getBasicSalary() != null ? finSalariesOnline.getBasicSalary().toString() : "0.0");
				row3.createCell(3).setCellValue(finSalariesOnline.getRealitySalary() != null ? finSalariesOnline.getRealitySalary().toString() : "0.0");
				row3.createCell(4).setCellValue(finSalariesOnline.getRealExpenditure() != null ? finSalariesOnline.getRealExpenditure().toString() : "0.0");
				row3.createCell(5).setCellValue(finSalariesOnline.getBilled() == Grant.UNBILLED ? "未发" : finSalariesOnline.getBilled() == Grant.PUBLISHED ? "已发" : "未通过");
			}
		}
		return wb;
	}

	@Override
	public List<FinSalariesOnline> findFinSalariesOnlineByClass(Class<Actores> clazz, Date $date1, Date $date2) {
		return this.finSalariesOnlineDAO.findFinSalariesOnlineByClass(clazz, $date1, $date2);
	}

	@Override
	public HSSFWorkbook exportFinSalaries(Map<String, List<FinSalaries>> map,List<Department> listdep) {
		String[] excelHeader = { "员工号", "姓名", "艺名", "工商银行", "交通银行", "实发工资", "公司实际支出工资", "发放状态", "工资卡账号", "开户地址","备注"};
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle style = wb.createCellStyle();
		BranchOffice branchs = null;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		if (listdep != null &&  listdep.size() > 0 ) {
				for (int j = 0; j < listdep.size(); j++) {
					HSSFSheet sheet = wb.createSheet(j+1+"-"+listdep.get(j).getName());
					HSSFRow row = sheet.createRow((int) 0);
					for (int i = 0; i < excelHeader.length; i++) {
						HSSFCell cell = row.createCell(i);
						cell.setCellValue(excelHeader[i]);
						cell.setCellStyle(style);
						sheet.autoSizeColumn(i);
						sheet.setColumnWidth(i, 32 * 150);
					}
					
					if (map.get(listdep.get(j).getId().toString()) != null) {
						for (int i = 0; i < map.get(listdep.get(j).getId().toString()).size(); i++) {
								FinSalaries finSalaries = map.get(listdep.get(j).getId().toString()).get(i);
									row = sheet.createRow(i+1);
									row.setRowStyle(style);
									if (branchs == null)
										branchs = this.branchOfficeService.readBranchOffice(finSalaries.getBranchs());
									if (branchs != null)
										row.createCell(0).setCellValue(branchs.getNumberHead() + String.format("%04d", finSalaries.getNumber()));
									else
										row.createCell(0).setCellValue(String.format("%04d", finSalaries.getNumber()));
									row.createCell(1).setCellValue(finSalaries.getName());
									row.createCell(2).setCellValue(finSalaries.getAliasname());
									row.createCell(3).setCellValue(finSalaries.getIcbcBank() != null ? finSalaries.getIcbcBank().toString() : "0.0");
									row.createCell(4).setCellValue(finSalaries.getBocomBank() != null ? finSalaries.getBocomBank().toString() : "0.0");
									row.createCell(5).setCellValue(finSalaries.getRealitySalary() != null ? finSalaries.getRealitySalary().toString() : "0.0");
									row.createCell(6).setCellValue(finSalaries.getRealExpenditure() != null ? finSalaries.getRealExpenditure().toString() : "0.0");
									row.createCell(7).setCellValue(finSalaries.getBilled() == Grant.UNBILLED ? "未发" : finSalaries.getBilled() == Grant.PUBLISHED ? "已发" : "未通过");
									row.createCell(8).setCellValue(finSalaries.getEmployee().getBankCard());
									row.createCell(9).setCellValue(finSalaries.getEmployee().getBankAddress());
									row.createCell(10).setCellValue(finSalaries.getEmployee().getRemarks());
								}
							}
						}
				}
		return wb;
	}

}
