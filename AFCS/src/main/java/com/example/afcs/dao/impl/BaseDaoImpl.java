package com.example.afcs.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.example.afcs.model.*;
import com.example.afcs.dao.*;
/**
 * @author rishiraj
 *
 */

public class BaseDaoImpl implements IBaseDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	private SessionFactory getSessionFactory()
	{
		return this.sessionFactory;
	}
	
	private HibernateTemplate getHibernateTemplate()
	{
		return this.hibernateTemplate;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.dao.IBaseDAO#save(com.techmahindra.common.pojo.IBasePojo)
	 */
	public void save(final IBasePojo entity) {
		getHibernateTemplate().save(entity);		
	}

	public void saveObject(final Object entity) {
		getHibernateTemplate().save(entity);		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.dao.IBaseDAO#update(com.techmahindra.common.pojo.IBasePojo)
	 */
	public void update(final Object entity) {
		getHibernateTemplate().update(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.dao.IBaseDAO#saveOrUpdate(com.techmahindra.common.pojo.IBasePojo)
	 */
	public void saveOrUpdate(final Object entity) 
	{
		if(entity != null && entity instanceof List)
		{
			List entities = (List) entity;
			
			for(Object obj : entities)
			{
				getHibernateTemplate().saveOrUpdate(obj);
			}
			
		}else
		{
			getHibernateTemplate().saveOrUpdate(entity);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.ng.anipay.dao.IBaseDao#saveOrUpdate(java.util.List)
	 */
	public List<Object> saveOrUpdate(final List<Object> entities)
	{
		for(Object entity : entities)
		{
			getHibernateTemplate().saveOrUpdate(entity);
		}
		
		return entities;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.dao.IBaseDAO#delete(com.techmahindra.common.pojo.IBasePojo)
	 */
	//@Transactional(rollbackFor=Exception.class)
	public void delete(final IBasePojo entity) {
		
		getHibernateTemplate().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.dao.IBaseDAO#findById(java.lang.Long,
	 *      java.lang.Class)
	 */
	public <T extends Object> T findById(final Integer id,
			final Class<T> modelClass) {
		return getHibernateTemplate().get(modelClass, id);
	}
	
	public <T extends IBasePojo> T findById(final String id,
			final Class<T> modelClass) {
		return getHibernateTemplate().get(modelClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.dao.IBaseDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T extends IBasePojo> List<T> findAll(Class<T> persistableClass) {
		return (List<T>) getHibernateTemplate().find("FROM " + persistableClass.getSimpleName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.dao.IBaseDAO#findByNamedQuery(java.lang.String,
	 *      java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public  List findByNamedQuery(
			final String queryName, final Map<String, Object> queryParams) {
		String[] params = new String[queryParams.size()];
		Object[] values = new Object[queryParams.size()];

		int index = 0;
		Iterator<String> i = queryParams.keySet().iterator();
		while (i.hasNext()) {
			String key = i.next();
			params[index] = key;
			values[index++] = queryParams.get(key);
		}

		return (List) getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
				params, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.dao.IBaseDAO#findByNamedQuery(java.lang.String,
	 *      java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List findByNamedQuery(
			final String queryName) {
		
		return getHibernateTemplate().findByNamedQuery(queryName);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.web.dao.IBaseDAO#findByNamedQuery(java.lang.String,
	 *      java.util.Map, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List findByNamedQuery(
			final String queryName, final Map<String, Object> queryParams,
			final int fetchSize, final int startIndex) {
		return (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						final Query query = session.getNamedQuery(queryName);
						query.setMaxResults(fetchSize);
						query.setFirstResult(startIndex);

						for (Entry<String, Object> param : queryParams.entrySet()) {
							query.setParameter(param.getKey(), param.getValue());
						}
						return query.list();
					}
				});
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.web.dao.IBaseDAO#findByNamedQuery(java.lang.String,
	 *      java.lang.String[], java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public List findByNamedQuery(final String queryName,
			final String[] paramNames, final Object[] values) {
		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
				paramNames, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.web.dao.IBaseDAO#findByNamedQuery(java.lang.String,
	 *      java.lang.String[], java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findByNamedQueryArray(final String queryName,
			final String[] paramNames, final Object[] values) {
		return (List<Object[]>)getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
				paramNames, values);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.web.dao.IBaseDAO#findByNamedQuery(java.lang.String,
	 *      java.lang.String[], java.lang.Object[], int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findByNamedQuery(final String queryName,
			final String[] paramNames, final Object[] values,
			final int fetchSize, final int startIndex) {
		final List result = (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						final Query query = session.getNamedQuery(queryName);
						query.setMaxResults(fetchSize);
						query.setFirstResult(startIndex);

						int i = 0;
						for (String param : paramNames) {
							query.setParameter(param, values[i++]);
						}
						return query.list();
					}
				});
		return (List<Object>)result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.web.dao.IBaseDAO#findByHQL(java.lang.String,
	 *      java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findByHQL(final String hql, final Object[] paramValues) {
		return (List<Object>)getHibernateTemplate().find(hql, paramValues);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findByHQLArray(final String hql, final Object[] paramValues) {
		return (List<Object[]>)getHibernateTemplate().find(hql, paramValues);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.web.dao.IBaseDAO#findByHQL(java.lang.String,
	 *      java.lang.Object[], int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findByHQL(final String hql, final Object[] paramValues,
			final int fetchSize, final int startIndex) {
		return (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						final Query query = session.createQuery(hql);
						query.setMaxResults(fetchSize);
						query.setFirstResult(startIndex);

						for (int index = 0; index < paramValues.length; index++) {
							query.setParameter(index, paramValues[index]);
						}
						return query.list();
					}
				});
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.dao.IBaseDAO#flush()
	 */
	public void flush() {
		getHibernateTemplate().flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmahindra.common.dao.IBaseDAO#evict(com.techmahindra.common.pojo.IBasePojo)
	 */
	public void evict(final IBasePojo entity) {
		getHibernateTemplate().evict(entity);
	}

	protected Map<String, Object> createMapForQueryParams(final String[] keys,
			final Object[] values) {
		final Map<String, Object> queryParams = new HashMap<>();
		int counter = 0;
		for (String str : keys) {
			queryParams.put(str, values[counter++]);
		}
		return queryParams;
	}

	/**
	 * @param hql
	 * @param paramValues
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Integer countRecordsByHQL(final String hql,
			final Object... paramValues) {
		final Object[] params = new Object[paramValues.length];
		int i = 0;

		for (Object arg : paramValues) {
			params[i++] = arg;
		}

		final List<Object> result = (List<Object>)getHibernateTemplate().find(hql, params);
		return ((Long) (result.get(0))).intValue();
	}

	/**
	 * @param namedQuery
	 * @param paramsMap
	 */
	@SuppressWarnings("unchecked")
	protected void updateByNamedQuery(final String namedQuery,
			final Map<String, Object> paramsMap) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				final Query query = session.getNamedQuery(namedQuery);

				for (Entry<String, Object> param : paramsMap.entrySet()) {
					query.setParameter(param.getKey(), param.getValue());
				}
				return query.executeUpdate();
			}
		});
	}

	/**
	 * @param namedQuery
	 * @param paramsMap
	 */
	@SuppressWarnings("unchecked")
	protected Integer deleteByNamedQuery(final String namedQuery,
			final Map<String, Object> paramsMap) {			
				final Query query = getSessionFactory().getCurrentSession().getNamedQuery(namedQuery);

				for (Entry<String, Object> param : paramsMap.entrySet()) {
					query.setParameter(param.getKey(), param.getValue());
				}
				return query.executeUpdate();				
	}

	/**
	 * @param hql
	 * @param paramValues
	 */
	@SuppressWarnings("unchecked")
	protected void updateByHQL(final String hql, final Object... paramValues) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				final Query query = session.createQuery(hql);

				int counter = 0;
				for (Object arg : paramValues) {
					query.setParameter(counter++, arg);
				}

				return query.executeUpdate();
			}
		});
	}

	
	public <T extends IBasePojo> T findObjectByNamedQuery(String queryName,Map<String, Object> queryParams) 
	{
		Object result = findSingleObjectByNamedQuery(queryName, queryParams);
		
		if(result != null)
			return (T)result;
		else
		return null;
	}
	
	
	public <T extends Object> T findObjectByNamedQuery(String queryName,Map<String, Object> queryParams, Class<T> modelClass) 
	{
		List resultData = getQueryResult(queryName, queryParams);
		
		if(resultData != null && !resultData.isEmpty())
			return (T)resultData.get(0);
		else
		return null;
	}
	
	private List getQueryResult(String queryName,Map<String, Object> queryParams) 
	{
		String[] params = new String[queryParams.size()];
		Object[] values = new Object[queryParams.size()];

		int index = 0;
		Iterator<String> i = queryParams.keySet().iterator();
		while (i.hasNext()) {
			String key = i.next();
			params[index] = key;
			values[index++] = queryParams.get(key);
		}

		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,params, values);
		
	}

	public Object findSingleObjectByNamedQuery(String queryName,Map<String, Object> queryParams) 
	{
		List resultData = getQueryResult(queryName, queryParams);
		
		if(resultData != null && !resultData.isEmpty())
			return resultData.get(0);
		else
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	protected List<String> createByNamedQuery(final String sqlQuery,
			final Map<String, Object> queryParams) {			
				final SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
				
				for (Entry<String, Object> param : queryParams.entrySet()) {
					query.setParameter(param.getKey(), param.getValue());
				}
				return query.list();				
	}
	
	@SuppressWarnings("unchecked")
	protected int createQuery(final String sqlQuery,
			final Map<String, Object> queryParams) {			
				final SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
				
				for (Entry<String, Object> param : queryParams.entrySet()) {
					query.setParameter(param.getKey(), param.getValue());
				}
				return query.executeUpdate();				
	}
	
	
	@SuppressWarnings("unchecked")
	protected List<Object[]> createQuery(final String sqlQuery) {			
				final SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
				return query.list();				
	}
	
	
	@SuppressWarnings("unchecked")
	protected int createUpdateQuery(final String sqlQuery) {			
				final SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
				return query.executeUpdate();				
	}
	
	@SuppressWarnings("unchecked")
	protected List createSelectQuery(final String sqlQuery,
			final Map<String, Object> queryParams) {			
				final SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
				for (Entry<String, Object> param : queryParams.entrySet()) {
					query.setParameter(param.getKey(), param.getValue());
				}
				return query.list();				
	}
	
}