package com.baofeng.oa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.oa.entity.BigTopDetails;
import com.baofeng.utils.Constants;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("bigtopDAO")
public class BigtopDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter, String platId, String date) {
		try {
			Date date1 = Constants.convert(date + "-01-01 00:00:00", Constants.format2);
			Date date2 = Constants.convert(date + "-12-31 23:59:59", Constants.format2);
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BigTopDetails.class);
			if (platId != null && platId.trim().length() > 0) {
				detachedCriteria.add(Restrictions.eq("platId", Integer.valueOf(platId)));
			}
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.asc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public BigTopDetails readBigtop(String id) {
		BigTopDetails details = (BigTopDetails) this.baseDAO.get(BigTopDetails.class, Integer.valueOf(id));
		return details;
	}

	public boolean saveBigTop(BigTopDetails details) {
		try {
			if (details.getId() != null && details.getId().intValue() > 0) {
				this.baseDAO.update(details);
			} else {
				this.baseDAO.save(details);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<BigTopDetails> readBigTopDetails() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BigTopDetails.class);
			detachedCriteria.addOrder(Order.asc("createDT"));
			List<BigTopDetails> detailsList = (List<BigTopDetails>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null) {
				return detailsList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public List<?> readBigTopDetailsList(String date, String platId, SearchFilter filter) {
		try {
			Date date1 = Constants.convert(date + "-01-01 00:00:00", Constants.format2);
			Date date2 = Constants.convert(date + "-12-31 23:59:59", Constants.format2);
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BigTopDetails.class);
			if (platId != null && platId.trim().length() > 0) {
				detachedCriteria.add(Restrictions.eq("platId", Integer.valueOf(platId)));
			}
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public BigTopDetails readBigtopByOutlay(String platId, Integer outlayId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BigTopDetails.class);
			if (platId != null && platId.trim().length() > 0) {
				detachedCriteria.add(Restrictions.eq("platId", Integer.valueOf(platId)));
			}
			detachedCriteria.createAlias("outlay", "outlay").add(Restrictions.eq("outlay.id", outlayId));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<BigTopDetails> detailsList = (List<BigTopDetails>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				BigTopDetails details = detailsList.get(0);
				return details;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean deleteBigTopDetails(BigTopDetails details) {
		if (details != null) {
			this.baseDAO.delete(details);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public BigTopDetails findBigtopByTopup(Integer platId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BigTopDetails.class);
			detachedCriteria.add(Restrictions.eq("platId", Integer.valueOf(platId)));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<BigTopDetails> detailsList = (List<BigTopDetails>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				BigTopDetails details = detailsList.get(0);
				return details;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}
}
