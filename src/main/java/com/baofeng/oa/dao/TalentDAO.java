package com.baofeng.oa.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.Talent;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("talentDAO")
public class TalentDAO {
	
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String fastArg, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria  = DetachedCriteria.forClass(Talent.class);
			if(fastArg != null  && fastArg.trim().length()>0){
				Disjunction disjunction = Restrictions.disjunction();
				disjunction.add(Restrictions.like("name", fastArg,MatchMode.ANYWHERE));
				disjunction.add(Restrictions.like("aliasname", fastArg,MatchMode.ANYWHERE));
				detachedCriteria.add(disjunction);
			}
			detachedCriteria.addOrder(Order.desc("genrer"));
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Talent readTalent(Integer id) {
		try {
			if(id != null && id.intValue()>0){
				return (Talent)this.baseDAO.get(Talent.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteTalent(Talent talent) {
		try {
			if(talent != null){
				this.baseDAO.delete(talent);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	public boolean addTalent(Talent talent) {
		try {
			if(talent != null && talent.getId() != null && talent.getId().intValue()>0){
				this.baseDAO.update(talent);
			}else{
				this.baseDAO.save(talent);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Talent> findAllTalent() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Talent.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Talent>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Talent readTalentBySession(Integer empId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Talent.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("employee", "em").add(Restrictions.eq("em.id", empId));
			List<Talent> list = (List<Talent>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				Talent act = (Talent) list.get(0);
				return act;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean saveTalent(Talent post) {
		try {
			if (post != null && post.getId() != null && post.getId().intValue() > 0) {
				this.baseDAO.update(post);
			} else {
				this.baseDAO.save(post);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
