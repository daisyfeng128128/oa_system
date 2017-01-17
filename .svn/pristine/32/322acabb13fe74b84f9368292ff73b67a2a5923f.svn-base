package com.baofeng.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseDAOImpl implements IBaseDAO {

	@SuppressWarnings("unused")
	private boolean cacheQueries = false;

	@SuppressWarnings("unused")
	private String queryCacheRegion;

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<?> find(final String query) {
		return this.getCurrentSession().createQuery(query).list();
	}

	public Integer excuteSQL(final String sql, final Object[] parameter) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (parameter != null && parameter.length > 0) {
			for (int i = 0; i < parameter.length; i++) {
				q.setParameter(i, parameter[i]);
			}
		}
		return q.executeUpdate();
	}

	public List<?> findExcuteSQL(final String sql, final Object[] parameter) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (parameter != null && parameter.length > 0) {
			for (int i = 0; i < parameter.length; i++) {
				q.setParameter(i, parameter[i]);
			}
		}
		return q.list();
	}

	public List<?> find(final String query, final Object[] parameter) {
		Query q = this.getCurrentSession().createQuery(query);
		if (parameter != null && parameter.length > 0) {
			for (int i = 0; i < parameter.length; i++) {
				q.setParameter(i, parameter[i]);
			}
		}
		return q.list();
	}

	@SuppressWarnings("static-access")
	public List<?> findAllByCriteria(final DetachedCriteria detachedCriteria) {
		try {
			Session session = this.getCurrentSession();
			detachedCriteria.setResultTransformer(detachedCriteria.DISTINCT_ROOT_ENTITY);
			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			LinkedList<Object> filterSet = new LinkedList<Object>();
			for (Object obj : criteria.list()) {
				filterSet.add(obj);
			}
			return new ArrayList<Object>(filterSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Object>();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Object> findByDetachedCriteria(final DetachedCriteria detachedCriteria, final int beginIndex, final int endIndex) {
		try {
			Session session = this.getCurrentSession();
			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			criteria.setProjection(null);
			List list = criteria.setFirstResult(beginIndex).setMaxResults(endIndex).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Object>();
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NPageResult<?> NfindByPages(StringBuilder buildHQL, StringBuilder countHQL, int pageSize, int curPage) {
		try {
			Session session = this.getCurrentSession();
			final int totalCount = session.createQuery(countHQL.toString()).list().size();
			Query _query = session.createQuery(buildHQL.toString()).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).setFirstResult((curPage - 1) * pageSize)
					.setMaxResults(pageSize);
			List<?> items = _query.list();
			NPageResult<?> ps = new NPageResult(true, totalCount, Long.valueOf(curPage), items);
			return ps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@SuppressWarnings({ "unchecked", "static-access", "rawtypes" })
	public NPageResult<?> NfindByPages(final DetachedCriteria detachedCriteria, final int pageSize, final int currentPage) {
		try {
			Session session = this.getCurrentSession();
			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			criteria.setProjection(Projections.rowCount());
			List<?> list = criteria.list();
			long totalRows = Long.valueOf(0);
			if (list != null && list.size() > 0) {
				Long count = (Long) list.get(0);
				totalRows = count.longValue();
			}
			criteria.setProjection(null);
			detachedCriteria.setResultTransformer(detachedCriteria.DISTINCT_ROOT_ENTITY);
			List<?> items = criteria.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
			NPageResult<?> ps = new NPageResult(true, totalRows, Long.valueOf(currentPage), items);
			return ps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "static-access", "rawtypes" })
	public PageResult<?> findByPages(final DetachedCriteria detachedCriteria, final int pageSize, final int currentPage) {
		try {
			Session session = this.getCurrentSession();
			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			criteria.setProjection(Projections.rowCount());
			List<Object> list = criteria.list();
			int totalCount = Integer.valueOf(0);
			if (list != null && list.size() > 0) {
				Long count = (Long) list.get(0);
				totalCount = count.intValue();
			}
			criteria.setProjection(null);
			detachedCriteria.setResultTransformer(detachedCriteria.DISTINCT_ROOT_ENTITY);
			List items = criteria.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
			PageResult<?> ps = new PageResult(items, currentPage, totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1, totalCount);
			return ps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResult findByPages(final String inQueryHql, final String inCountHql, final int pageSize, final int currentPage) {
		try {
			Session session = this.getCurrentSession();
			final int totalCount = DataAccessUtils.intResult(session.createQuery(inCountHql).list());
			Query _query = session.createQuery(inQueryHql).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize);
			List items = _query.list();
			PageResult ps = new PageResult(items, currentPage, totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1, totalCount);
			return ps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Object get(final Class entity, final Serializable id) {
		return this.getCurrentSession().get(entity, id);
	}

	public Object get(DetachedCriteria detachedCriteria) {
		List<?> list = this.findAllByCriteria(detachedCriteria);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		try {
			Session session = this.getCurrentSession();
			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			Integer count = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
			return count.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.valueOf(0);
	}

	@SuppressWarnings("rawtypes")
	public Object getEntityByProperty(String propertyName, Object propertyValue, Class entityClass) throws DataAccessException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		detachedCriteria.add(Restrictions.eq(propertyName, propertyValue));
		List<?> list = this.findAllByCriteria(detachedCriteria);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Object load(final Class entity, final Serializable id) {
		return this.getCurrentSession().load(entity, id);
	}

	public void persist(final Object entity) {
		this.getCurrentSession().save(entity);
	}

	public Object save(final Object entity) {
		return this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(Object entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void setCacheQueries(boolean cacheQueries) {
		this.cacheQueries = cacheQueries;
	}

	public void setQueryCacheRegion(String queryCacheRegion) {
		this.queryCacheRegion = queryCacheRegion;
	}

	@Override
	public List<?> findAllByCriteria(final DetachedCriteria detachedCriteria, final int lenght) {
		try {
			Session session = this.getCurrentSession();
			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			criteria.setFirstResult(0);
			criteria.setMaxResults(lenght);
			LinkedList<Object> filterSet = new LinkedList<Object>();
			for (Object obj : criteria.list()) {
				filterSet.add(obj);
			}
			return new ArrayList<Object>(filterSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Object>();
	}

	@Override
	public void delete(Object entity) {
		this.getCurrentSession().delete(entity);
	}

	@Override
	public void mrege(Object entity) {
		this.getCurrentSession().merge(entity);
	}

	@Override
	public void update(Object entity) {
		this.getCurrentSession().update(entity);
	}

	@Override
	public void destroy(Object entity) {
		this.getCurrentSession().delete(entity);
	}

	@Override
	public Integer execute(final String hql) {
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public Integer execute(final String hql, final Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	@Override
	public void evict(Object obj) {
		this.getCurrentSession().evict(obj);
	}

}
