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
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.SearchFilter;

@Repository("platformsOnlineIncomeDAO")
public class PlatformsOnlineIncomeDAO {

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

	public void addPlatformsOnlineIncome(PlatformsOnlineIncome income) {
		try {
			if (income != null) {
				this.baseDAO.save(income);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsOnlineIncome> readAllPlatformsOnlineIncome(String platId, Integer mId, Date beginDate, Date endDate, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("platformsId", platId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			detachedCriteria.addOrder(Order.desc("channel"));
			detachedCriteria.addOrder(Order.desc("pactId"));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<PlatformsOnlineIncome> list = (List<PlatformsOnlineIncome>) baseDAO.findAllByCriteria(detachedCriteria);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public PlatformsOnlineIncome readPlatformsOnlineIncome(Integer id) {
		if (id != null && id.intValue() > 0) {
			return (PlatformsOnlineIncome) this.baseDAO.get(PlatformsOnlineIncome.class, id);
		}
		return null;
	}

	public boolean saveIncome(PlatformsOnlineIncome post) {

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

	public boolean savePlatformsOnlineBySession(PlatformsOnlineIncome outlay) {
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

	public boolean findValidation(Integer pactId, Integer branchs, Integer monthsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("pactId", pactId.toString()));
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", monthsId));
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
	public List<PlatformsOnlineIncome> readPlatformsOnlineIncomeList(Integer monthsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", monthsId));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return (List<PlatformsOnlineIncome>) outlayList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 功能：总计线上收入
	 * */
	public BigDecimal sumOnlineIncome(PlatformsMonthsOutlay months) {
		BigDecimal sumOnline = new BigDecimal(0);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", months.getId()));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				for (Object o : outlayList) {
					PlatformsOnlineIncome details = (PlatformsOnlineIncome) o;
					if (details != null && details.getBgIncome().doubleValue() > 0) {
						BigDecimal bgIncome = details.getBgIncome();
						BigDecimal deductTax = details.getDeductTax() == null ? new BigDecimal(0.00) : details.getDeductTax();
						Platforms platforms = this.platformsDAO.findPlatforms(Integer.valueOf(details.getPlatformsId()));
						if (platforms != null && platforms.getIncomeGenre() != null && platforms.getIncomeGenre() == PlatIncomeGenre.SHUIQIAN) {
							bgIncome = bgIncome.subtract(deductTax);
						}
						sumOnline = sumOnline.add(bgIncome);
					}
				}
				return sumOnline;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return sumOnline;
		}
		return sumOnline;
	}

	public PlatformsOnlineIncome readPlatformsOnlineIncomeBySession(Integer pactId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.add(Restrictions.eq("pactId", pactId.toString()));
			detachedCriteria.add(Restrictions.eq("audit", Audit.WRITE));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				PlatformsOnlineIncome $online = (PlatformsOnlineIncome) outlayList.get(0);
				return $online;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsOnlineIncome> readPlatformsOnlineIncomeById(Integer pactId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.add(Restrictions.eq("pactId", pactId.toString()));
			detachedCriteria.add(Restrictions.eq("audit", Audit.WRITE));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return (List<PlatformsOnlineIncome>) outlayList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean deletePlatformsActores(PlatformsOnlineIncome income) {
		try {
			if (income != null)
				this.baseDAO.delete(income);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean findActValidation(String actsName, Integer branchs, Integer mid, String platId, String channelId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
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

	@SuppressWarnings("unchecked")
	public List<PlatformsOnlineIncome> findPlatformsOnlineList(Integer mid, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.createAlias("months", "m").add(Restrictions.eq("m.id", mid));
			List<PlatformsOnlineIncome> outlayList = (List<PlatformsOnlineIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
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
	public PlatformsOnlineIncome findPlatformsOnlineIncomeByActId(Integer pactId, Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.add(Restrictions.eq("pactId", pactId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			List<PlatformsOnlineIncome> incomeList = (List<PlatformsOnlineIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (incomeList != null && incomeList.size() > 0) {
				return incomeList.get(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PlatformsOnlineIncome findPlatformsOnlineIncemeByName(String name, String aliasname, Integer platId, Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.add(Restrictions.eq("name", name));
			detachedCriteria.add(Restrictions.eq("aliasname", aliasname));
			detachedCriteria.add(Restrictions.eq("platformsId", platId.toString()));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsOnlineIncome> incomeList = (List<PlatformsOnlineIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (incomeList != null && incomeList.size() > 0) {
				return incomeList.get(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PlatformsOnlineIncome readPlatformsOnlineIncomeByName(String channel, Integer branchs) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOnlineIncome.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("channel", channel));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsOnlineIncome> list = (List<PlatformsOnlineIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean addOnlineIncome(PlatformsOnlineIncome lineIncome) {
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
