package com.example.afcs.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.afcs.dao.UserEntityDAO;
import com.example.afcs.model.UserEntity;
import com.example.afcs.util.ExceptionUtils;

@Transactional
@Repository
public class UserEntityDAOImpl implements UserEntityDAO {
	
	private static final Logger log = LoggerFactory.getLogger(UserEntityDAOImpl.class);
	
	@PersistenceContext
	protected transient EntityManager entityManager;
	  
	
	
	public UserEntity getUserProfile(String userName) {
		UserEntity returnValue = null;
		
		try {
			Query query =entityManager.createQuery("from UserEntity where emailId='" + userName + "'");
			returnValue = (UserEntity) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnValue;
		 
		   
	}


	@Transactional
	public UserEntity saveUser(UserEntity userEntity) {
		log.debug("******************** saveUser() starts executing *****************");
		try{
			entityManager.persist(userEntity);
		
		
		}
		catch(Exception e){
			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(), "com.example.comm");
			log.debug("Exception occured: "+this.getClass().getSimpleName()+"method name: "+Thread.currentThread().getStackTrace()[1].getMethodName()+"error message: "+e+"class name: "+exceptionData[0]+ "file name: "+exceptionData[1]+ "log method name: "+exceptionData[2]+"line number: "+exceptionData[3]);
			throw e;
		}
		return userEntity;
		
	}
	
	


	@Transactional
	public UserEntity updateUser(UserEntity userEntity) {
		log.debug("******************** updateUser() starts executing *****************");
		try {

			entityManager.merge(userEntity);
			
			

		} catch (Exception e) {
			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					"com.example.afcs");
			log.debug("Exception occured: " + this.getClass().getSimpleName() + "method name: "
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "error message: " + e + "class name: "
					+ exceptionData[0] + "file name: " + exceptionData[1] + "log method name: " + exceptionData[2]
					+ "line number: " + exceptionData[3]);
			throw e;
		}
		return userEntity;
		
	}


	@Override
	public UserEntity findByMobile(String mobile) {
		UserEntity userEntity = null;
		try {
			TypedQuery<UserEntity> query = entityManager.createNamedQuery("UserEntity.findByMobile",	UserEntity.class);
			query.setParameter("mobile", mobile);
			userEntity = query.getSingleResult();
		} catch (Exception ex) {
			//throw ex;
			return userEntity;
		}

		return userEntity;
		
	}


	@Override  
	public UserEntity getUserByToken(String token) {
		UserEntity userEntity = null;
		try {
			TypedQuery<UserEntity> query = entityManager.createNamedQuery("UserEntity.findByToken",	UserEntity.class);
			query.setParameter("token", token);
			userEntity = query.getSingleResult();
		} catch (RuntimeException ex) {
			throw ex;
		}

		return userEntity;
	}
	
	
	
	

}
