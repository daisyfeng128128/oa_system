package com.baofeng.oa.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BaseEnums.PlatIncomeGenre;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActivityOutlay;
import com.baofeng.oa.entity.PlatformsAudits;
import com.baofeng.oa.entity.PlatformsMaintainOutlay;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.oa.entity.PlatformsMonthsTotalOutlay;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.SearchFilter;

@Repository("platformsMonthsTotalOutlayDAO")
public class PlatformsMonthsTotalOutlayDAO {

	@Autowired
	private IBaseDAO baseDAO;
	@Autowired
	private PlatformsDAO platformsDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public void addPlatformsMonthsOutlay(PlatformsMonthsTotalOutlay post) {
		if (post != null && post.getId() == null) {
			this.baseDAO.save(post);
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsMonthsTotalOutlay> readAllPlatformsMonthsOutlayDate() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonthsTotalOutlay.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<PlatformsMonthsTotalOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public PlatformsMonthsTotalOutlay readPlatformsMonthsOutlay(String platId, Integer mId, Date date1, Date date2, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonthsTotalOutlay.class);
			detachedCriteria.add(Restrictions.eq("platId", platId));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsMonthsTotalOutlay> list = (List<PlatformsMonthsTotalOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				PlatformsMonthsTotalOutlay outlay = list.get(0);
				if (outlay != null)
					return outlay;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 功能：线上艺人支出
	 * */
	@SuppressWarnings("unchecked")
	public BigDecimal findActOnlineOutlay(Integer mId) {
		BigDecimal sumOnline = new BigDecimal(0);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.setProjection(Projections.sum("actoresSalary"));
			List<Object> detailsList = (List<Object>) baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				Object o = detailsList.get(0);
				if (o instanceof BigDecimal) {
					sumOnline = (BigDecimal) o;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return sumOnline;
		}
		return sumOnline;
	}

	/**
	 * 功能：线下艺人支出
	 * */
	@SuppressWarnings("unchecked")
	public BigDecimal findActOfflineOutlay(Integer mId) {
		BigDecimal sumOffline = new BigDecimal(0);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.setProjection(Projections.sum("netOffincome"));
			List<Object> detailsList = (List<Object>) baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				Object o = detailsList.get(0);
				if (o instanceof BigDecimal) {
					sumOffline = (BigDecimal) o;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return sumOffline;
		}
		return sumOffline;
	}

	@SuppressWarnings("unchecked")
	public BigDecimal findManagerOutlay(Integer mId, Integer maxnum, LineGenres ganres) {
		BigDecimal sumBaseMoney = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManagerOutlay.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("genre", ganres));
			List<PlatformsManagerOutlay> list = (List<PlatformsManagerOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				for (PlatformsManagerOutlay details : list) {
					if (details != null) {
						// 底薪
						BigDecimal basicSalary = new BigDecimal(0.00);
						if (details.getAttendance() != null && details.getAttendance().floatValue() < Float.valueOf(maxnum).floatValue()) {
							basicSalary = new BigDecimal(details.getBasicSalary() == null ? 0.00f : details.getBasicSalary().floatValue());
							basicSalary = basicSalary.divide(new BigDecimal(maxnum), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(details.getAttendance()));
						} else {
							basicSalary = new BigDecimal(details.getBasicSalary() == null ? 0.00f : details.getBasicSalary().floatValue());
						}

						// 餐补
						BigDecimal mealAllowance = new BigDecimal(0.00);
						if (details.getAttendance() != null && details.getAttendance().floatValue() > 0) {
							mealAllowance = new BigDecimal(details.getMealAllowance() == null ? 0.00f : details.getMealAllowance().floatValue());
							if (details.getAttendance().floatValue() < Float.valueOf(maxnum).floatValue())
								mealAllowance = mealAllowance.multiply(new BigDecimal(details.getAttendance().floatValue()));
							else
								mealAllowance = mealAllowance.multiply(new BigDecimal(maxnum));
						}

						// 合计
						BigDecimal sumTotal = new BigDecimal(0.0);
						if (basicSalary != null)
							sumTotal = sumTotal.add(basicSalary);
						if (details.getPushMoney() != null)
							sumTotal = sumTotal.add(new BigDecimal(details.getPushMoney()));
						if (mealAllowance != null)
							sumTotal = sumTotal.add(mealAllowance);
						if (details.getOtherAllowance() != null)
							sumTotal = sumTotal.add(new BigDecimal(details.getOtherAllowance()));
						if (details.getDeduct() != null)
							sumTotal = sumTotal.subtract(new BigDecimal(details.getDeduct()));
						sumBaseMoney = sumBaseMoney.add(sumTotal);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return sumBaseMoney;
		}
		return sumBaseMoney;
	}

	/**
	 * YL : 财务报表在用
	 **/
	@SuppressWarnings("unchecked")
	public BigDecimal findMaintain(Integer mId) {
		BigDecimal sumBaseMoney = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMaintainOutlay.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsMaintainOutlay> list = (List<PlatformsMaintainOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				for (PlatformsMaintainOutlay details : list) {
					float baseMoney = 0f;
					if (details != null && details.getTopup() != null)
						baseMoney = baseMoney + details.getTopup().floatValue();
					if (details != null && details.getPersonalGift() != null)
						baseMoney = baseMoney + details.getPersonalGift().floatValue();
					if (details != null && details.getTransferFee() != null)
						baseMoney = baseMoney + details.getTransferFee().floatValue();
					if (details != null && details.getRestitution() != null)
						baseMoney = baseMoney - details.getRestitution().floatValue();
					if (details != null && details.getTax() != null)
						baseMoney = baseMoney + details.getTax().floatValue();
					sumBaseMoney = sumBaseMoney.add(new BigDecimal(baseMoney));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return sumBaseMoney;
		}
		return sumBaseMoney;
	}

	/**
	 * 功能：线上艺人本月收入
	 * */
	@SuppressWarnings("unchecked")
	public BigDecimal findOnlineIncome(Integer mid) {
		BigDecimal sumBaseMoney = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mid));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsOnlineIncome> detailsList = (List<PlatformsOnlineIncome>) baseDAO.findAllByCriteria(detachedCriteria);
			for (PlatformsOnlineIncome details : detailsList) {
				if (details != null && details.getBgIncome() != null) {
					BigDecimal bgIncome = details.getBgIncome();
					BigDecimal deductTax = details.getDeductTax() == null ? new BigDecimal(0.00) : details.getDeductTax();
					Platforms platforms = this.platformsDAO.findPlatforms(Integer.valueOf(details.getPlatformsId()));
					if (platforms != null && platforms.getIncomeGenre() != null && platforms.getIncomeGenre() == PlatIncomeGenre.SHUIQIAN) {
						bgIncome = bgIncome.subtract(deductTax);
					}
					sumBaseMoney = sumBaseMoney.add(bgIncome);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return sumBaseMoney;
		}
		return sumBaseMoney;
	}

	/**
	 * 功能：线下艺人本月收入
	 * */
	@SuppressWarnings("unchecked")
	public BigDecimal findOfflineIncome(Integer mid) {
		BigDecimal sumBaseMoney = new BigDecimal(0);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mid));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsOfflineIncome> detailsList = (List<PlatformsOfflineIncome>) baseDAO.findAllByCriteria(detachedCriteria);
			for (PlatformsOfflineIncome details : detailsList) {
				if (details != null && details.getIncome() != null) {
					BigDecimal bgIncome = details.getIncome();
					BigDecimal deductTax = details.getDeductTax() == null ? new BigDecimal(0.00) : details.getDeductTax();
					Platforms platforms = this.platformsDAO.findPlatforms(Integer.valueOf(details.getPlatformsId()));
					if (platforms != null && platforms.getIncomeGenre() != null && platforms.getIncomeGenre() == PlatIncomeGenre.SHUIQIAN) {
						bgIncome = bgIncome.subtract(deductTax);
					}
					sumBaseMoney = sumBaseMoney.add(bgIncome);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return sumBaseMoney;
		}
		return sumBaseMoney;
	}

	/**
	 * 功能：线上艺人上月收入
	 * */
	@SuppressWarnings("unchecked")
	public BigDecimal findLastOnlineIncome(String platId, Date $date1, Date $date2) {
		BigDecimal sumBaseMoney = new BigDecimal(0);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.add(Restrictions.eq("platformsId", platId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			List<PlatformsOnlineIncome> list = (List<PlatformsOnlineIncome>) baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				for (PlatformsOnlineIncome details : list) {
					if (details != null && details.getBgIncome() != null) {
						BigDecimal bgIncome = details.getBgIncome();
						BigDecimal deductTax = details.getDeductTax() == null ? new BigDecimal(0.00) : details.getDeductTax();
						Platforms platforms = this.platformsDAO.findPlatforms(Integer.valueOf(details.getPlatformsId()));
						if (platforms != null && platforms.getIncomeGenre() != null && platforms.getIncomeGenre() == PlatIncomeGenre.SHUIQIAN) {
							bgIncome = bgIncome.subtract(deductTax);
						}
						sumBaseMoney = sumBaseMoney.add(bgIncome);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return sumBaseMoney;
		}
		return sumBaseMoney;
	}

	/**
	 * 功能：线下艺人上月收入
	 * */
	@SuppressWarnings("unchecked")
	public BigDecimal findLastOfflineIncome(String platId, Date $date1, Date $date2) {
		BigDecimal sumBaseMoney = new BigDecimal(0);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.add(Restrictions.eq("platformsId", platId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			List<PlatformsOfflineIncome> list = (List<PlatformsOfflineIncome>) baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				for (PlatformsOfflineIncome details : list) {
					if (details != null && details.getIncome() != null) {
						BigDecimal bgIncome = details.getIncome();
						BigDecimal deductTax = details.getDeductTax() == null ? new BigDecimal(0.00) : details.getDeductTax();
						Platforms platforms = this.platformsDAO.findPlatforms(Integer.valueOf(details.getPlatformsId()));
						if (platforms != null && platforms.getIncomeGenre() != null && platforms.getIncomeGenre() == PlatIncomeGenre.SHUIQIAN) {
							bgIncome = bgIncome.subtract(deductTax);
						}
						sumBaseMoney = sumBaseMoney.add(bgIncome);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return sumBaseMoney;
		}
		return sumBaseMoney;
	}

	/**
	 * 功能：活动总支出 YL ： 财务报表也在用
	 * */
	@SuppressWarnings("unchecked")
	public BigDecimal findActivity(Integer mId) {
		BigDecimal sumBaseMoney = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsActivityOutlay.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsActivityOutlay> list = (List<PlatformsActivityOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				for (PlatformsActivityOutlay details : list) {
					if (details.getTotalMoney() != null) {
						sumBaseMoney = sumBaseMoney.add(new BigDecimal(details.getTotalMoney()));
						if (details.getTax() != null)
							sumBaseMoney = sumBaseMoney.add(details.getTax());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return sumBaseMoney;
		}
		return sumBaseMoney;
	}

	public boolean savePlatformsMonthsOutlay(PlatformsMonthsTotalOutlay outlay) {
		try {
			if (outlay != null && outlay.getId() != null && outlay.getId().intValue() > 0) {
				this.baseDAO.update(outlay);
			} else {
				this.baseDAO.save(outlay);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public PlatformsMonthsTotalOutlay readAllPlatformsMonthsOutlay(String id) {
		if (id != null && id.trim().length() > 0) {
			PlatformsMonthsTotalOutlay outlay = (PlatformsMonthsTotalOutlay) this.baseDAO.get(PlatformsMonthsTotalOutlay.class, Integer.valueOf(id));
			if (outlay != null)
				return outlay;
		}
		return null;
	}

	/**
	 * 功能：生成营收总表数据验证
	 * 
	 * @param platId
	 * */
	@SuppressWarnings("unchecked")
	public PlatformsMonthsTotalOutlay buildPlatformsMonthsOutlay(Integer branchs, Integer platId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonthsTotalOutlay.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("platId", String.valueOf(platId)));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsMonthsTotalOutlay> list = (List<PlatformsMonthsTotalOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				PlatformsMonthsTotalOutlay outlay = list.get(0);
				if (outlay != null)
					return outlay;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 功能：获取上月后台收入
	 * 
	 * @param filter
	 * */
	@SuppressWarnings("unchecked")
	public PlatformsMonthsTotalOutlay findPlatformsMonthsOutlyaByLast(Integer branchs, String platId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonthsTotalOutlay.class);
			detachedCriteria.add(Restrictions.eq("platId", String.valueOf(platId)));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsMonthsTotalOutlay> list = (List<PlatformsMonthsTotalOutlay>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				PlatformsMonthsTotalOutlay outlay = list.get(0);
				if (outlay != null)
					return outlay;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean savePlatformsAudits(PlatformsAudits audits) {
		try {
			if (audits != null && audits.getId() != null && audits.getId().intValue() > 0) {
				this.baseDAO.update(audits);
			} else {
				this.baseDAO.save(audits);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public PlatformsAudits readPlatfromsAuditesByMid(Integer mId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsAudits.class);
			detachedCriteria.createAlias("months", "m").add(Restrictions.eq("m.id", mId));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<PlatformsAudits> list = (List<PlatformsAudits>) baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				PlatformsAudits audits = list.get(0);
				return audits;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsMonthsTotalOutlay> readAllPlatformsMonthsOutlayByAudit(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonthsTotalOutlay.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<PlatformsMonthsTotalOutlay> list = (List<PlatformsMonthsTotalOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PlatformsMonthsTotalOutlay findPlatformsMonthsOutlay(String platId, Integer branchs, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonthsTotalOutlay.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("platId", String.valueOf(platId)));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsMonthsTotalOutlay> list = (List<PlatformsMonthsTotalOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				PlatformsMonthsTotalOutlay outlay = list.get(0);
				if (outlay != null)
					return outlay;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsMonthsTotalOutlay> readAllPlatformsMonthsOutlayByDate(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonthsTotalOutlay.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsMonthsTotalOutlay> list = (List<PlatformsMonthsTotalOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 功能：线下劳务费
	 * */
	@SuppressWarnings("unchecked")
	public BigDecimal findOfflineLaborService(Integer mId) {
		BigDecimal sumOffline = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.setProjection(Projections.sum("laborService"));
			List<Object> detailsList = (List<Object>) baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				Object o = detailsList.get(0);
				if (o instanceof BigDecimal) {
					sumOffline = (BigDecimal) o;
				}
			}
			return sumOffline;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 功能：线上劳务费
	 * */
	@SuppressWarnings("unchecked")
	public BigDecimal findOnlineLaborService(Integer mId) {
		BigDecimal sumOnline = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.setProjection(Projections.sum("laborService"));
			List<Object> detailsList = (List<Object>) baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				Object o = detailsList.get(0);
				if (o instanceof BigDecimal) {
					sumOnline = (BigDecimal) o;
				}
			}
			return sumOnline;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public PlatformsMonthsTotalOutlay readPlatformsMonths(Date date1, Date date2, Integer platId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonthsTotalOutlay.class);
			detachedCriteria.add(Restrictions.eq("platId", platId.toString()));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsMonthsTotalOutlay> list = (List<PlatformsMonthsTotalOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				PlatformsMonthsTotalOutlay outlay = list.get(0);
				if (outlay != null)
					return outlay;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsMonthsTotalOutlay> findPlatformsMonthsOutlayList(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonthsTotalOutlay.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsMonthsTotalOutlay> outlayList = (List<PlatformsMonthsTotalOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return outlayList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Platforms readPlatforms(String platId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Platforms.class);
			detachedCriteria.add(Restrictions.eq("id", Integer.valueOf(platId)));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<Platforms> outlayList = (List<Platforms>) baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return outlayList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public PlatformsMonthsTotalOutlay readPlatformsMonthsTotalOutlay(Integer platId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonthsTotalOutlay.class);
			detachedCriteria.add(Restrictions.eq("platId", String.valueOf(platId)));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<PlatformsMonthsTotalOutlay> outlayList = (List<PlatformsMonthsTotalOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return outlayList.get(0);
			}
		} catch (Exception e) {
	
		}
		return null;
	}
}
