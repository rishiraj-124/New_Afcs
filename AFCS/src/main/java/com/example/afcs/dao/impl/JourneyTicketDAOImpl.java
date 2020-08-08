package com.example.afcs.dao.impl;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.afcs.dao.JourneyTicketDAO;
import com.example.afcs.model.AfcsGateDeviceMaster;
import com.example.afcs.model.PassengerEntity;
import com.example.afcs.model.QRPassEntity;
import com.example.afcs.model.SingleJourneyEntitiy;
import com.example.afcs.model.ValueQRTicketEntity;
import com.example.afcs.util.ExceptionUtils;

@Transactional
@Repository
public class JourneyTicketDAOImpl implements JourneyTicketDAO{

private static final Logger log = LoggerFactory.getLogger(UserEntityDAOImpl.class);
	
	@PersistenceContext
	protected transient EntityManager entityManager;
	
	
	public SingleJourneyEntitiy saveJourney(SingleJourneyEntitiy singleJourneyEntitiy) {
		
		log.debug("******************** saveJourney() starts executing *****************");
		try{
			entityManager.persist(singleJourneyEntitiy);
			entityManager.flush(); 
			
		
		}
		catch(Exception e){
			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(), "com.example.afcs");
			log.debug("Exception occured: "+this.getClass().getSimpleName()+"method name: "+Thread.currentThread().getStackTrace()[1].getMethodName()+"error message: "+e+"class name: "+exceptionData[0]+ "file name: "+exceptionData[1]+ "log method name: "+exceptionData[2]+"line number: "+exceptionData[3]);
			throw e;
		}
		return singleJourneyEntitiy;
		
		
	}


	@Override
	public SingleJourneyEntitiy updateJourney(SingleJourneyEntitiy singleJourneyEntitiy) {

		log.debug("******************** updateJourney() starts executing *****************");
		try{
			entityManager.merge(singleJourneyEntitiy);
			entityManager.flush(); 
			
		
		}
		catch(Exception e){
			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(), "com.example.afcs");
			log.debug("Exception occured: "+this.getClass().getSimpleName()+"method name: "+Thread.currentThread().getStackTrace()[1].getMethodName()+"error message: "+e+"class name: "+exceptionData[0]+ "file name: "+exceptionData[1]+ "log method name: "+exceptionData[2]+"line number: "+exceptionData[3]);
			throw e;
		}
		return singleJourneyEntitiy;
	}


	@Override
	public SingleJourneyEntitiy getSingleJourney(String qrCode) {
		SingleJourneyEntitiy singleJourneyEntity=null;
		try {
			TypedQuery<SingleJourneyEntitiy> query = entityManager.createNamedQuery("SingleJourneyEntitiy.findByQRCode",	SingleJourneyEntitiy.class);
			query.setParameter("qrTicketHash", qrCode);
			singleJourneyEntity = query.getSingleResult();
		} catch (RuntimeException ex) {
			return singleJourneyEntity;
		}

		return singleJourneyEntity;
	}


	@Override
	public AfcsGateDeviceMaster getGateDeviceId(String deviceId) {
		AfcsGateDeviceMaster afcsGateDeviceMaster=null;
		try {
			TypedQuery<AfcsGateDeviceMaster> query = entityManager.createNamedQuery("AfcsGateDeviceMaster.findByDeviceId",	AfcsGateDeviceMaster.class);
			query.setParameter("deviceId", deviceId);
			afcsGateDeviceMaster = query.getSingleResult();
		} catch (RuntimeException ex) {
			return afcsGateDeviceMaster;
		}

		return afcsGateDeviceMaster;
	}


	@Override
	public ValueQRTicketEntity saveValueQR(ValueQRTicketEntity valueQRTicketEntity) {
		log.debug("******************** saveValueQR() starts executing *****************");
		try{
			entityManager.persist(valueQRTicketEntity);
			entityManager.flush(); 
			
		
		}
		catch(Exception e){
			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(), "com.example.afcs");
			log.debug("Exception occured: "+this.getClass().getSimpleName()+"method name: "+Thread.currentThread().getStackTrace()[1].getMethodName()+"error message: "+e+"class name: "+exceptionData[0]+ "file name: "+exceptionData[1]+ "log method name: "+exceptionData[2]+"line number: "+exceptionData[3]);
			throw e;
		}
		return valueQRTicketEntity;
	}


	@Override
	public QRPassEntity saveQRPass(QRPassEntity qrPassEntity) {
		log.debug("******************** saveQRPass() starts executing *****************");
		try{
			entityManager.persist(qrPassEntity);
			entityManager.flush(); 
			
		
		}
		catch(Exception e){
			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(), "com.example.afcs");
			log.debug("Exception occured: "+this.getClass().getSimpleName()+"method name: "+Thread.currentThread().getStackTrace()[1].getMethodName()+"error message: "+e+"class name: "+exceptionData[0]+ "file name: "+exceptionData[1]+ "log method name: "+exceptionData[2]+"line number: "+exceptionData[3]);
			throw e;
		}
		return qrPassEntity;
	}
	
	

}
