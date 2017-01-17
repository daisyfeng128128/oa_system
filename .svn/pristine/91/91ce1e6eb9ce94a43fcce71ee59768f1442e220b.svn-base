package com.baofeng.utils;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

public interface IBaseDAO {

	public void delete(Object entity);

	public void destroy(final Object entity);

	public List<?> find(final String query);

	public List<?> find(final String query, final Object[] parameter);

	public Integer excuteSQL(final String sql, final Object[] parameter);

	public List<?> findExcuteSQL(final String sql, final Object[] parameter);

	public List<?> findAllByCriteria(final DetachedCriteria detachedCriteria);

	public List<?> findAllByCriteria(final DetachedCriteria detachedCriteria, int lenght);

	public List<?> findByDetachedCriteria(final DetachedCriteria detachedCriteria, final int beginIndex, final int endIndex);

	public NPageResult<?> NfindByPages(StringBuilder buildHQL, StringBuilder countHQL, int pageSize, int curPage);

	public NPageResult<?> NfindByPages(DetachedCriteria detachedCriteria, int pageSize, int curPage);

	public PageResult<?> findByPages(final DetachedCriteria detachedCriteria, final int pageSize, final int currentPage);

	public PageResult<?> findByPages(final String inQueryHql, final String inCountHql, final int pageSize, final int currentPage);

	public Object get(final Class<?> entity, final Serializable id);

	public Object get(final DetachedCriteria detachedCriteria);

	public int getCountByCriteria(final DetachedCriteria detachedCriteria);

	public Object getEntityByProperty(String propertyName, Object propertyValue, Class<?> entityClass);

	public Object load(final Class<?> entity, final Serializable id);

	public void mrege(Object entity);

	public void persist(final Object entity);

	public Object save(final Object entity);

	public void saveOrUpdate(final Object entity);

	public void setCacheQueries(boolean cacheQueries);

	public void setQueryCacheRegion(String queryCacheRegion);

	public void update(final Object entity);

	public Integer execute(String hql);

	public Integer execute(String hql, Object[] param);

	public void evict(Object obj);

	Session getCurrentSession();

}