/**
 * 
 */
package com.example.afcs.dao;

import java.util.List;
import java.util.Map;

import com.example.afcs.model.IBasePojo;



/**
 * @author rishiraj
 *
 */

public interface IBaseDao {

	/**
	 * @param entity
	 */
	public void save(final IBasePojo entity);

	/**
	 * 
	 * @param object
	 */
	public void saveObject(final Object object);
	
	/**
	 * @param entity
	 */
	public void update(final Object entity);

	/**
	 * @param entity
	 */
	public void saveOrUpdate(final Object entity);

	/**
	 * @param entity
	 */
	public List<Object> saveOrUpdate(final List<Object> entities);
	
	/**
	 * @param entity
	 */
	public void delete(final IBasePojo entity);

	/**
	 * @param <T>
	 * @param id
	 * @param modelClass
	 * @return
	 */
	public <T extends Object> T findById(final Integer id,
			final Class<T> modelClass);

	/**
	 * @param <T>
	 * @param persistableClass
	 * @return
	 */
	public <T extends IBasePojo> List findAll(Class<T> persistableClass);

	/**
	 * @param <T>
	 * @param queryName
	 * @param queryParams
	 * @return
	 */
	public List findByNamedQuery(
			final String queryName, final Map<String, Object> queryParams);
	
	/**
	 * @param <T>
	 * @param queryName
	 * @param queryParams
	 * @return
	 */
	public <T extends IBasePojo> T findObjectByNamedQuery(
			final String queryName, final Map<String, Object> queryParams);

	
	
	public <T extends Object> T findObjectByNamedQuery(
			final String queryName, final Map<String, Object> queryParams, final Class<T> modelClass);
	/**
	 * 
	 * @param queryName
	 * @param queryParams
	 * @return
	 */
	public Object findSingleObjectByNamedQuery(
			final String queryName, final Map<String, Object> queryParams);
	/**
	 * @param <T>
	 * @param queryName
	 * @param queryParams
	 * @param fetchSize
	 * @param startIndex
	 * @return
	 */
	public List findByNamedQuery(
			final String queryName, final Map<String, Object> queryParams,
			final int fetchSize, final int startIndex);

	/**
	 * @param queryName
	 * @param paramNames
	 * @param values
	 * @return
	 */
	public List findByNamedQuery(final String queryName,
			final String[] paramNames, final Object[] values);

	/**
	 * @param queryName
	 * @param paramNames
	 * @param values
	 * @param fetchSize
	 * @param startIndex
	 * @return
	 */
	public List<Object> findByNamedQuery(final String queryName,
			final String[] paramNames, final Object[] values,
			final int fetchSize, final int startIndex);

	/**
	 * @param <T>
	 * @param hql
	 * @param paramValues
	 * @return
	 */
	public List<Object> findByHQL(final String hql, final Object[] paramValues);

	/**
	 * @param hql
	 * @param paramValues
	 * @param fetchSize
	 * @param startIndex
	 * @return
	 */
	public List<Object> findByHQL(final String hql, final Object[] paramValues,
			final int fetchSize, final int startIndex);

	/**
	 * @param entity
	 */
	public void evict(final IBasePojo entity);

	/**
	 * 
	 */
	public void flush();
}
