package com.baofeng.oa.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.BranchUser;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

/** 分公司登陆 */
@Repository("branchUserDAO")
public class BranchUserDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchUser.class);
			if (filter.getRules().size() > 0) {
				Disjunction disjunction = Restrictions.disjunction();
				detachedCriteria.createAlias("tarBranchs", "t");
				disjunction.add(Restrictions.eq("branchs", filter.getRules().get(0).getData()));
				disjunction.add(Restrictions.eq("t.id", filter.getRules().get(0).getData()));
				detachedCriteria.add(disjunction);
			}
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("overdues"));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public BranchUser readBranchLogin(Integer id) {
		try {
			if (id != null && id.intValue() > 0) {
				return (BranchUser) this.baseDAO.get(BranchUser.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addBranchLogin(BranchUser branch) {
		try {
			if (branch != null && branch.getId() != null && branch.getId().intValue() > 0) {
				this.baseDAO.update(branch);
			} else {
				this.baseDAO.save(branch);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(BranchUser branch) {
		try {
			if (branch != null) {
				this.baseDAO.delete(branch);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public BranchUser readBranchUser(Integer ruid, Integer branchs) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchUser.class);
			detachedCriteria.createAlias("user", "u").add(Restrictions.eq("u.id", ruid));
			detachedCriteria.createAlias("tarBranchs", "t").add(Restrictions.eq("t.id", branchs));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<BranchUser> userList = (List<BranchUser>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (userList != null && userList.size() > 0) {
				BranchUser user = userList.get(0);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public boolean checkBranchUser(Integer roleUsersId, Integer branchsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchUser.class);
			detachedCriteria.createAlias("user", "u").add(Restrictions.eq("u.id", roleUsersId));
			detachedCriteria.createAlias("tarBranchs", "t").add(Restrictions.eq("t.id", branchsId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<BranchUser> userList = (List<BranchUser>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (userList != null && userList.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public BranchUser readBranchLoginbyNum(Integer number) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchUser.class);
			detachedCriteria.add(Restrictions.eq("number", number));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<BranchUser> incomeList = (List<BranchUser>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (incomeList != null && incomeList.size() > 0) {
				return incomeList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
