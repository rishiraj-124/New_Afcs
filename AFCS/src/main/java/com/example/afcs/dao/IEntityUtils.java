package com.example.afcs.dao;

public interface IEntityUtils<T> {

	T saveEntity(T entity);
	T updateEntity(T entity);
	T getEntityById(Long id,String query,Class<T> klass);
	
	
	
}
