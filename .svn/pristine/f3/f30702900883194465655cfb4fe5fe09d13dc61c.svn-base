package com.baofeng.oa.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.BaseEnums.Audit;
import com.baofeng.oa.entity.BaseEnums.PlatIncomeGenre;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.SearchFilter;

@Repository("offlineIncomeDAO")
public class PlatformsOfflineIncomeDAO {

	@Autowired
	private IBaseDAO baseDAO;
	@Autowired
	private PlatformsDAO platformsDAO;

	public void addPlatformsOfflineIncome(PlatformsOfflineIncome income) {
		try {
			if (income != null) {
				this.baseDAO.save(income);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsOfflineIncome> readAllPlatformsOfflineIncome(String platId, Integer mId, Date beginDate, Date endDate, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("platformsId", platId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("number"));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<PlatformsOfflineIncome>) baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public PlatformsOfflineIncome readPlatformsOfflineIncome(Integer id) {
		if (id != null && id.intValue() > 0) {
			return (PlatformsOfflineIncome) this.baseDAO.get(PlatformsOfflineIncome.class, id);
		}
		return null;
	}

	public boolean update(PlatformsOfflineIncome post) {
		try {
			if (post != null) {
				this.baseDAO.update(post);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean savePlatformsOfflineOutlayBySession(PlatformsOfflineIncome outlay) {
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

	public boolean findValidation(Integer pactId, Integer branchs, Integer monthsId, String platId, String channelId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("pactId", pactId.toString()));
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", monthsId));
			detachedCriteria.add(Restrictions.eq("platformsId", platId));
			if (channelId != null && channelId.trim().length() > 0)
				detachedCriteria.add(Restrictions.eq("channelId", channelId));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsOfflineIncome> readPlatformsOfflineIncomeList(Integer monthsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", monthsId));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return (List<PlatformsOfflineIncome>) outlayList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 功能：总计当月线上主播收入
	 * */
	public BigDecimal sumOfflineIncome(PlatformsMonthsOutlay months) {
		BigDecimal sumOffline = new BigDecimal(0);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", months.getId()));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				for (Object o : outlayList) {
					PlatformsOfflineIncome details = (PlatformsOfflineIncome) o;
					if (details != null && details.getIncome() != null && details.getIncome().doubleValue() > 0) {
						BigDecimal bgIncome = details.getIncome();
						BigDecimal deductTax = details.getDeductTax() == null ? new BigDecimal(0.00) : details.getDeductTax();
						Platforms platforms = this.platformsDAO.findPlatforms(Integer.valueOf(details.getPlatformsId()));
						if (platforms != null && platforms.getIncomeGenre() != null && platforms.getIncomeGenre() == PlatIncomeGenre.SHUIQIAN) {
							bgIncome = bgIncome.subtract(deductTax);
						}
						sumOffline = sumOffline.add(bgIncome);
					}
				}
				return sumOffline;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return sumOffline;
		}
		return sumOffline;
	}

	public PlatformsOfflineIncome readPlatformsOfflineIncomeBySession(Integer pactId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.add(Restrictions.eq("pactId", pactId.toString()));
			detachedCriteria.add(Restrictions.eq("audit", Audit.WRITE));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				PlatformsOfflineIncome $offline = (PlatformsOfflineIncome) outlayList.get(0);
				return $offline;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean deletePlatformsActores(PlatformsOfflineIncome income) {
		try {
			if (income != null)
				this.baseDAO.delete(income);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 功能：添 加活动号
	 * */
	public boolean findActValidation(String actsName, Integer branchs, Integer mid, String platId, String channelId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("name", actsName));
			detachedCriteria.add(Restrictions.eq("aliasname", actsName));
			detachedCriteria.add(Restrictions.eq("platformsId", platId));
			/*if (channelId != null)
				detachedCriteria.add(Restrictions.eq("channelId", channelId));*/
			detachedCriteria.createAlias("months", "m").add(Restrictions.eq("m.id", mid));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}

	public List<?> findPlatformsOnlineList(Integer mid, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.createAlias("months", "m").add(Restrictions.eq("m.id", mid));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return outlayList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsActores> readAllPlatformsActores(Integer actId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsActores.class);
			detachedCriteria.createAlias("actores", "actores").add(Restrictions.eq("actores.id", actId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<PlatformsActores>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public PlatformsOfflineIncome findPlatformsOfflineIncomeById(Integer pactId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.add(Restrictions.eq("pactId", pactId.toString()));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<PlatformsOfflineIncome> list = (List<PlatformsOfflineIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PlatformsOfflineIncome findPlatformsOfflineIncomeByNumber(Integer number, Integer platId, Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.add(Restrictions.eq("number", number));
			detachedCriteria.add(Restrictions.eq("platformsId", platId.toString()));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			List<PlatformsOfflineIncome> incomeList = (List<PlatformsOfflineIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (incomeList != null && incomeList.size() > 0) {
				return incomeList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PlatformsOfflineIncome readPlatformsOfflineIncomeByChannel(String channel, Integer branchs) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("channel", channel));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsOfflineIncome> list = (List<PlatformsOfflineIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean addOfflineIncome(PlatformsOfflineIncome lineIncome) {
		try {
			if (lineIncome != null && lineIncome.getId() != null && lineIncome.getId().intValue() > 0) {
				this.baseDAO.update(lineIncome);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
