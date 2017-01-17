package com.baofeng.oa.quartz;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BaseEnums.PlatformGenre;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.FinancialAccounts;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IFinancialAccountsService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;
import com.baofeng.oa.service.IPlatformsManagerService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.oa.service.IPlatformsMonthsService;
import com.baofeng.oa.service.IPlatformsOfflineIncomeService;
import com.baofeng.oa.service.IPlatformsOnlineIncomeService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;

/**
 * 功能：平台报表数据同步
 * */
public class CPlatformsTask {

	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private IPlatformsActoresService actoresService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IPlatformsOnlineIncomeService onlineIncomeService;
	@Autowired
	private IPlatformsManagerService platformsManagerService;
	@Autowired
	private IPlatformsManagerOutlayService managerOutlayService;
	@Autowired
	private IPlatformsOfflineIncomeService offlineIncomeService;
	@Autowired
	private IPlatformsMonthsOutlayService platformsMonthsOutlayService;
	@Autowired
	private IPlatformsMonthsService platformsMonthsService;
	@Autowired
	private IFinancialAccountsService financialAccountsService;
	@Autowired
	private IFinancialAccountsService financialExpensesService;

	private static final Logger logger = LoggerFactory.getLogger(CPlatformsTask.class);

	public void doExec() {
		// 营收总表
		Date date1 = CustomDateUtils.Handler.lastMonthFirstDay(new Date());
		Date date2 = CustomDateUtils.Handler.lastMonthEndDay(new Date());
		List<Platforms> platList = this.platformsService.findAllPlatforms();
		List<BranchOffice> branchList = this.branchOfficeService.findBranchOfficeList();
		if (platList != null && platList.size() > 0 && branchList != null && branchList.size() > 0) {
			for (Platforms plat : platList) {
				for (BranchOffice office : branchList) {
					if (this.platformsMonthsOutlayService.readPlatformsMonthsOutlay(plat.getId(), office.getId(), date1, date2) == null) {
						PlatformsMonthsOutlay months = this.platformsMonthsOutlayService.savePlatformsMonthsOutlay(office.getId(), plat, date1, date2);
						// 管理支出数据同步
						addManagerOutlay(months, date1, date2);
						logger.info("管理支出数据同步成功!");
						
						// 线上艺人收支数据同步
						addOnlineIncome(LineGenres.ONLINE, months, date1, date2);
						logger.info("线上艺人收支数据同步成功!");

						// 线下艺人收支数据同步
						addOfflineIncome(LineGenres.OFFLINE, months, date1, date2);
						logger.info("线下艺人收支数据同步成功!");

						// 生成财务支出报表
						addFinancialAccounts(months, date1, date2);
						logger.info("生成财务支出报表成功!");

					}
				}
			}
		}
	}

	/**
	 * 功能：生成财务支出报表
	 * 
	 * @param months
	 * */
	private void addFinancialAccounts(PlatformsMonthsOutlay months, Date date1, Date date2) {
		JSONArray master = Constants.readToJson("MESSAGE_MASTER");
		JSONArray other = Constants.readToJson("MESSAGE_OTHER");

		int i = 0;
		for (Iterator<?> it = master.iterator(); it.hasNext();) {
			JSONObject o = (JSONObject) it.next();
			String key = (String) o.get("key");
			String value = (String) o.getString("text");
			FinancialAccounts $finacs = this.financialAccountsService.findFinancialAccounts("master", key, months.getBranchs(), date1, date2);
			if ($finacs == null) {
				$finacs = new FinancialAccounts();
				$finacs.setIndexs(i++);
				$finacs.setOneSubjectKey("master");
				$finacs.setTwoSubject(key);
				$finacs.setTwoSubjectMsg(value);
				$finacs.setCreateDT(date1);
				$finacs.setBranchs(months.getBranchs());
				this.financialAccountsService.saveFinancialAccounts($finacs);
			}
		}
		i = 0;
		for (Iterator<?> it = other.iterator(); it.hasNext();) {
			JSONObject o = (JSONObject) it.next();
			String key = (String) o.get("key");
			String value = (String) o.getString("text");
			FinancialAccounts $finacs = this.financialAccountsService.findFinancialAccounts("other", key, months.getBranchs(), date1, date2);
			if ($finacs == null) {
				$finacs = new FinancialAccounts();
				$finacs.setIndexs(i++);
				$finacs.setOneSubjectKey("other");
				$finacs.setTwoSubject(key);
				$finacs.setTwoSubjectMsg(value);
				$finacs.setCreateDT(date1);
				$finacs.setBranchs(months.getBranchs());
				this.financialAccountsService.saveFinancialAccounts($finacs);
			}
		}
	}

	/**
	 * 功能：管理生成报表数据
	 * */
	private void addManagerOutlay(PlatformsMonthsOutlay months, Date date1, Date date2) {
		try {
			List<?> managerList = this.platformsManagerService.findAllPlatformsManager();
			if (managerList != null && managerList.size() > 0) {
				for (Object o : managerList) {
					PlatformsManager manager = (PlatformsManager) o;
					if (manager.getPlat() != null && manager.getPlat().getId().intValue() == Integer.valueOf(months.getPlatId()).intValue()
							&& manager.getBranchs().intValue() == months.getBranchs().intValue()) {
						String platId = manager.getPlat().getId().toString();
						String channelId = (manager.getChannel() == null ? "" : manager.getChannel().getId().toString());
						if (!this.managerOutlayService.findActValidation("场控", months.getId(), platId, channelId, date1, date2)) {
							PlatformsManagerOutlay $outlay = new PlatformsManagerOutlay();
							$outlay.setName("场控");
							$outlay.setAliasname("场控");
							$outlay.setMonths(months);
							$outlay.setBranchs(manager.getBranchs());
							$outlay.setBasicSalary(new Float(0));
							$outlay.setMealAllowance(new Float(0));
							$outlay.setGenre(LineGenres.ONLINE);
							$outlay.setPositions("场控");
							$outlay.setAttendance(new Float(0));
							$outlay.setDeduct(new Float(0));
							$outlay.setPushMoney(new Float(0));
							$outlay.setCreateDT(date1);
							if (channelId != null && channelId.trim().length() > 0) {
								$outlay.setChannel(manager.getChannel().getChannels());
								$outlay.setChannelId(channelId);
							}
							if (manager.getPlat() != null) {
								$outlay.setPlatforms(manager.getPlat().getPlatName());
								$outlay.setPlatformsId(platId);
							}
							this.managerOutlayService.savePlatformsManagerOutlay($outlay);
						}
						if (!this.managerOutlayService.findValidation(manager.getId(), months.getId(), platId, channelId, date1, date2)) {
							PlatformsManagerOutlay outlay = new PlatformsManagerOutlay();
							outlay.setBranchs(manager.getBranchs());
							outlay.setAliasname(manager.getManager().getAliasname());
							outlay.setBasicSalary(manager.getManager().getBasicSalary());
							if (manager.getManager().getLabour() == ActoresLabour.SYSTEM)
								if (manager.getManager().getGenrer() == Genres.SHIYONG)
									outlay.setBasicSalary(manager.getManager().getProbationSalary());
							outlay.setMealAllowance(manager.getManager().getFoodSubsidy() == null ? 0f : manager.getManager().getFoodSubsidy());
							outlay.setGenre(manager.getManager().getGenre());
							outlay.setName(manager.getManager().getRealname());
							outlay.setNumber(manager.getManager().getNumber());
							outlay.setPositions(manager.getManager().getPositions());
							outlay.setAttendance(Float.valueOf(0));
							outlay.setDeduct(Float.valueOf(0));
							outlay.setPushMoney(Float.valueOf(0));
							if (manager.getChannel() != null) {
								outlay.setChannel(manager.getChannel().getChannels());
								outlay.setChannelId(manager.getChannel().getId().toString());
							}
							if (manager.getPlat() != null) {
								outlay.setPlatforms(manager.getPlat().getPlatName());
								outlay.setPlatformsId(manager.getPlat().getId().toString());
							}
							outlay.setManagerId(manager.getId().toString());
							outlay.setMonths(months);
							outlay.setCreateDT(date1);
							this.managerOutlayService.savePlatformsManagerOutlay(outlay);
						}
					}
				}
				managerList.clear();
				managerList = null;
			}
		} catch (Exception e) {
			logger.error("管理支出数据同步", e);
		}
	}

	/**
	 * 功能：生成线上艺人收支报表数据
	 * */
	private void addOnlineIncome(LineGenres genrer, PlatformsMonthsOutlay months, Date date1, Date date2) {
		try {
			List<PlatformsActores> actoresList = this.actoresService.findAllPlatformsActores(genrer);
			if (actoresList != null && actoresList.size() > 0) {
				for (PlatformsActores actores : actoresList) {
					if (actores.getPlat() != null && actores.getPlat().getId().intValue() == Integer.valueOf(months.getPlatId()).intValue()
							&& actores.getBranchs().intValue() == months.getBranchs().intValue()) {
						String platId = actores.getPlat().getId().toString();
						String channelId = (actores.getChannel() == null ? null : actores.getChannel().getId().toString());
						if (!this.onlineIncomeService.findValidation(actores.getId(), actores.getBranchs(), months.getId())) {
							if (!this.onlineIncomeService.findActValidation("活动号", actores.getBranchs(), months.getId(), platId, channelId, date1, date2)) {
								PlatformsOnlineIncome $online = new PlatformsOnlineIncome();
								$online.setName("活动号");
								$online.setAliasname("活动号");
								$online.setMonths(months);
								$online.setCreateDT(date1);
								/*if (actores.getChannel() != null) {
									$online.setChannel(actores.getChannel().getChannels());
									$online.setChannelId(actores.getChannel().getId().toString());
								}*/
								if (actores.getPlat() != null) {
									$online.setPlatforms(actores.getPlat().getPlatName());
									$online.setPlatformsId(actores.getPlat().getId().toString());
								}
								$online.setBranchs(actores.getBranchs());
								this.onlineIncomeService.savePlatformsOnlineOutlay($online);
							}
							PlatformsOnlineIncome outlay = new PlatformsOnlineIncome();
							if (actores.getActores() != null) {
								outlay.setPactId(actores.getId().toString());
								outlay.setAliasname(actores.getActores().getAliasname());
								outlay.setPushMoney(new BigDecimal(actores.getPushMoney() == null ? 0 : actores.getPushMoney()));
								outlay.setMinimumGuarantee(actores.getCostArtists());
								if (actores.getCostArtists() == null) {
									outlay.setMinimumGuarantee(new BigDecimal(actores.getActores().getMinimumGuarantee() == null ? 0 : actores.getActores().getMinimumGuarantee()));
								}
								if (actores.getActores().getGenrer() == Genres.SHIYONG) {
									outlay.setPushMoney(new BigDecimal(0f));
								}
								outlay.setActoresSalary(new BigDecimal(0));
								outlay.setAttendance(new BigDecimal(0));
								outlay.setBgIncome(new BigDecimal(0));
								outlay.setNetIncome(new BigDecimal(0));
								outlay.setNetProfit(new BigDecimal(0));
								outlay.setDeductTax(new BigDecimal(0));
								outlay.setName(actores.getActores().getRealname());
							}
							if (actores.getChannel() != null) {
								outlay.setChannel(actores.getChannel().getChannels());
								outlay.setChannelId(actores.getChannel().getId().toString());
							} else {
								if (actores.getPlat().getFormGenre() == PlatformGenre.ZHIBOJIAN) {
									outlay.setChannel(actores.getNumber());
								}
							}
							if (actores.getPlat() != null) {
								outlay.setPlatforms(actores.getPlat().getPlatName());
								outlay.setPlatformsId(actores.getPlat().getId().toString());
							}
							outlay.setMonths(months);
							outlay.setCreateDT(date1);
							outlay.setBranchs(actores.getBranchs());
							this.onlineIncomeService.savePlatformsOnlineOutlay(outlay);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("线上艺人收支数据同步", e);
		}
	}

	/**
	 * 功能：线下艺人收支数据同步
	 * */
	private void addOfflineIncome(LineGenres genrer, PlatformsMonthsOutlay months, Date date1, Date date2) {
		try {
			List<PlatformsActores> actoresList = this.actoresService.findAllPlatformsActores(genrer);
			if (actoresList != null && actoresList.size() > 0) {
				for (PlatformsActores actores : actoresList) {
					if (actores.getPlat() != null && actores.getPlat().getId().intValue() == Integer.valueOf(months.getPlatId()).intValue()
							&& actores.getBranchs().intValue() == months.getBranchs().intValue()) {
						String platId = actores.getPlat().getId().toString();
						String channelId = (actores.getChannel() == null ? null : actores.getChannel().getId().toString());
						if (!this.offlineIncomeService.findValidation(actores.getId(), actores.getBranchs(), months.getId(), platId, channelId, date1, date2)) {
							if (!this.offlineIncomeService.findActValidation("活动号", actores.getBranchs(), months.getId(), platId, channelId, date1, date2)) {
								PlatformsOfflineIncome $outlay = new PlatformsOfflineIncome();
								$outlay.setName("活动号");
								$outlay.setAliasname("活动号");
								$outlay.setMonths(months);
								$outlay.setCreateDT(date1);
								/*if (actores.getChannel() != null) {
									$outlay.setChannel(actores.getChannel().getChannels());
									$outlay.setChannelId(actores.getChannel().getId().toString());
								}*/
								if (actores.getPlat() != null) {
									$outlay.setPlatforms(actores.getPlat().getPlatName());
									$outlay.setPlatformsId(actores.getPlat().getId().toString());
								}
								$outlay.setBranchs(actores.getBranchs());
								this.offlineIncomeService.savePlatformsOfflineOutlay($outlay);
							}
							PlatformsOfflineIncome outlay = new PlatformsOfflineIncome();
							if (actores.getActores() != null) {
								outlay.setPactId(actores.getId().toString());
								if (actores.getPushMoney() != null)
									outlay.setPushMoney(new BigDecimal(actores.getPushMoney() == null ? 0 : actores.getPushMoney()));
								outlay.setBasicSalary(actores.getCostArtists());
								if (actores.getCostArtists() == null) {
									outlay.setBasicSalary(new BigDecimal(actores.getActores().getBasicSalary() == null ? 0 : actores.getActores().getBasicSalary()));
								}
								outlay.setAliasname(actores.getActores().getAliasname());
								outlay.setName(actores.getActores().getRealname());
								outlay.setNumber(actores.getActores().getNumber());
							}
							if (actores.getChannel() != null) {
								outlay.setChannel(actores.getChannel().getChannels());
								outlay.setChannelId(actores.getChannel().getId().toString());
							} else {
								if (actores.getPlat().getFormGenre() == PlatformGenre.ZHIBOJIAN) {
									outlay.setChannel(actores.getNumber());
								}
							}
							if (actores.getPlat() != null) {
								outlay.setPlatforms(actores.getPlat().getPlatName());
								outlay.setPlatformsId(actores.getPlat().getId().toString());
							}
							outlay.setMonths(months);
							outlay.setCreateDT(date1);
							outlay.setBranchs(actores.getBranchs());
							this.offlineIncomeService.savePlatformsOfflineOutlay(outlay);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("线下艺人收支数据同步", e);
		}
	}
}
