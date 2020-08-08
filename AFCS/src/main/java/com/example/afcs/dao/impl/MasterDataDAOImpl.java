package com.example.afcs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.afcs.bean.MasterDataRequest;
import com.example.afcs.dao.MasterDataDAO;
import com.example.afcs.model.MasterDataEntity;
import com.example.afcs.model.PassMasterEntity;
import com.example.afcs.model.PassengerMasterEntity;
import com.example.afcs.model.StationMasterEntity;
import com.example.afcs.model.TicketMasterEntity;
import com.example.afcs.util.ExceptionUtils;

@Transactional
@Repository
public class MasterDataDAOImpl implements MasterDataDAO {

	private static final Logger log = LoggerFactory.getLogger(MasterDataDAOImpl.class);

	@PersistenceContext
	protected transient EntityManager entityManager;

	@Override
	public MasterDataEntity getMasterData() {

		MasterDataEntity masterDataEntity;
		try {
			log.debug("******************** getMasterData() starts executing *****************");
			masterDataEntity = new MasterDataEntity();
			List<TicketMasterEntity> ticketMasterList;
			List<PassengerMasterEntity> passengerMasterList;
			List<StationMasterEntity> stationMasterList;
			List<PassMasterEntity> passMasterList;
			ticketMasterList = findAllTicketMaster();
			passengerMasterList = findAllPassengerMaster();
			stationMasterList = findAllStationMaster();
			passMasterList = findAllPassMaster();

			masterDataEntity.setTicketList(ticketMasterList);
			masterDataEntity.setPssngrList(passengerMasterList);
			masterDataEntity.setStnList(stationMasterList);
			masterDataEntity.setPassList(passMasterList);
			log.debug("******************** getMasterData() ends executing *****************");
		} catch (Exception e) {
			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					"com.example.afcs");
			log.debug("Exception occured: " + this.getClass().getSimpleName() + "method name: "
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "error message: " + e + "class name: "
					+ exceptionData[0] + "file name: " + exceptionData[1] + "log method name: " + exceptionData[2]
					+ "line number: " + exceptionData[3]);
			throw e;
		}
		return masterDataEntity;
	}

	public List<PassMasterEntity> findAllPassMaster() {
		return entityManager.createQuery("SELECT p FROM PassMasterEntity p", PassMasterEntity.class).getResultList();
	}

	public List<PassengerMasterEntity> findAllPassengerMaster() {

		return entityManager.createQuery("SELECT p FROM PassengerMasterEntity p", PassengerMasterEntity.class)
				.getResultList();

	}

	public List<StationMasterEntity> findAllStationMaster() {

		return entityManager.createQuery("SELECT s FROM StationMasterEntity s", StationMasterEntity.class)
				.getResultList();

	}

	public List<TicketMasterEntity> findAllTicketMaster() {

		return entityManager.createQuery("SELECT t FROM TicketMasterEntity t", TicketMasterEntity.class)
				.getResultList();

	}

}
