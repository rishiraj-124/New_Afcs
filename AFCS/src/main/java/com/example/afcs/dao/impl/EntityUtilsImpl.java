package com.example.afcs.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.example.afcs.dao.IEntityUtils;

public class EntityUtilsImpl<T> implements IEntityUtils<T> {

	@PersistenceContext
	EntityManager empMgr;
	
	@Override
	public T saveEntity(T entity) {
		empMgr.persist(entity);
		return entity;
	}

	@Override
	public T updateEntity(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getEntityById(Long id, String query, Class<T> klass) {
			

		
		return null;
	}

}
