package com.baofeng.utils;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * @author RENLIANGRONG
 * 
 */
public class DetachedCriteriaUtil {
	public static DetachedCriteria addSearchFilter(DetachedCriteria detachedCriteria, SearchFilter filter) throws BaseException {
		if (filter != null && filter.getRules() != null && filter.getRules().size() > 0) {
			if ("or".equals(filter.getGroupOp())) {
				Disjunction disjunction = Restrictions.disjunction();
				for (SearchRule rule : filter.getRules()) {
					addSearchRules(disjunction, rule);
				}
				detachedCriteria.add(disjunction);
			} else if ("eq".equals(filter.getGroupOp())) {
				for (SearchRule rule : filter.getRules()) {
					addSearchRules(detachedCriteria, rule);
				}
			}
		}
		return detachedCriteria;
	}

	public static void addSearchRules(DetachedCriteria detachedCriteria, SearchRule rule) {
		if (rule.getOp() != null && "eq".equals(rule.getOp())) {
			detachedCriteria.add(Restrictions.eq(rule.getField(), rule.getData()));
		} else if ("like".equals(rule.getOp())) {
			detachedCriteria.add(Restrictions.like(rule.getField(), rule.getData().toString(), MatchMode.ANYWHERE));
		}
	}

	public static void addSearchRules(Disjunction disjunction, SearchRule rule) {
		if (rule.getOp() != null && "eq".equals(rule.getOp())) {
			disjunction.add(Restrictions.eq(rule.getField(), rule.getData()));
		} else if ("like".equals(rule.getOp())) {
			disjunction.add(Restrictions.like(rule.getField(), rule.getData().toString(), MatchMode.ANYWHERE));
		}
	}

}
