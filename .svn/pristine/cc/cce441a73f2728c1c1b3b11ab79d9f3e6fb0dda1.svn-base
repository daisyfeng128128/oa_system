package com.baofeng.oa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.entity.BaseEnums.Interviews;
import com.baofeng.oa.entity.Interview;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Repository("interviewDAO")
public class InterviewDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("unchecked")
	public List<Interview> readAllInterviewDate() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Interview.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Interview>) baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, Date date1, Date date2, Integer type, String fastArg, List<RoleDetailsAtts> platList, SearchFilter searchFilter, boolean flag) {
		try {
			DetachedCriteria  detachedCriteria = DetachedCriteria.forClass(Interview.class);
			if(type != null && 2 == type)
				detachedCriteria.add(Restrictions.eq("interview", Interviews.WAITENTRY));
			else if(type!= null && type ==0)
				detachedCriteria.add(Restrictions.eq("interview", Interviews.PENDING));
			else if(type != null && type == 4)
				detachedCriteria.add(Restrictions.eq("interview", Interviews.PASS));
			else if(type != null && type == 5 )
				detachedCriteria.add(Restrictions.eq("interview", Interviews.PRETEST));
			else if(type != null && (type ==1 || type ==3)){
				if(type == 1){
					detachedCriteria.add(Restrictions.eq("interview", Interviews.CHECK));
					if(platList != null && platList.size()>0){
						Disjunction disjunction = Restrictions.disjunction();
						for(RoleDetailsAtts atts  : platList){
							disjunction.add(Restrictions.eq("plat.id", Integer.valueOf(atts.getOpkey())));
						}
						detachedCriteria.add(disjunction);
					}else{
						detachedCriteria.add(Restrictions.eq("plat.id", -1));
					}
				}else if(type==3){
					detachedCriteria.add(Restrictions.eq("interview", Interviews.NOTPASS));
					if(!flag){
						if(platList != null && platList.size()>0){
							Disjunction disjunction = Restrictions.disjunction();
							for(RoleDetailsAtts atts  : platList){
								disjunction.add(Restrictions.eq("plat.id", Integer.valueOf(atts.getOpkey())));
							}
							detachedCriteria.add(disjunction);
						}else{
							detachedCriteria.add(Restrictions.eq("plat.id", -1));
						}
					}
				}
				
			}
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter filter, String queryFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Platforms.class);
			if (queryFilter != null && !queryFilter.trim().equals(""))
				detachedCriteria.add(Restrictions.like("platName", "%" + queryFilter + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addInterview(Interview post) {
		try {
			if(post != null && post.getId() !=null && post.getId().intValue()>0){
				this.baseDAO.update(post);
			}else{
				this.baseDAO.save(post);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Interview readInterview(Integer id) {
		try {
			if(id != null && id.intValue()>0){
				return (Interview) this.baseDAO.get(Interview.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Interview> readAllInterview(Date date1, Date date2, SearchFilter searchFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Interview.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return (List<Interview>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
