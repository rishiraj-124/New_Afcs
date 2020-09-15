package com.example.afcs.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LineMaster")
public class LineMaster implements Serializable {

	
	private static final long serialVersionUID = 7863949386462645678L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "lineName")
	private String lineName;
	
	@Column(name = "lineColour")
	private String lineColour;
	
	@Column(name = "startStn")
	private String startStn;
	
	@Column(name = "endStn")
	private String endStn;
	
	@Column(name = "trainfrequencyTime")
	private String trainfrequencyTime;
	
	@Column(name = "tfFrom")
	private String tfFrom;
	
	@Column(name = "tfTo")
	private String tfTo;
	
	@Column(name = "maxFareOnLine")
	private Long maxFareOnLine;
	
	@ManyToOne
	@JoinColumn(name="SMID", nullable=false) 
	private StationMasterEntity stationMaster;

	@ManyToOne
	@JoinColumn(name="PFMID", nullable=false) 
	private PlatformMaster platformMaster;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getLineColour() {
		return lineColour;
	}

	public void setLineColour(String lineColour) {
		this.lineColour = lineColour;
	}

	public String getStartStn() {
		return startStn;
	}

	public void setStartStn(String startStn) {
		this.startStn = startStn;
	}

	public String getEndStn() {
		return endStn;
	}

	public void setEndStn(String endStn) {
		this.endStn = endStn;
	}

	public String getTrainfrequencyTime() {
		return trainfrequencyTime;
	}

	public void setTrainfrequencyTime(String trainfrequencyTime) {
		this.trainfrequencyTime = trainfrequencyTime;
	}

	public String getTfFrom() {
		return tfFrom;
	}

	public void setTfFrom(String tfFrom) {
		this.tfFrom = tfFrom;
	}

	public String getTfTo() {
		return tfTo;
	}

	public void setTfTo(String tfTo) {
		this.tfTo = tfTo;
	}

	public Long getMaxFareOnLine() {
		return maxFareOnLine;
	}

	public void setMaxFareOnLine(Long maxFareOnLine) {
		this.maxFareOnLine = maxFareOnLine;
	}

	public StationMasterEntity getStationMaster() {
		return stationMaster;
	}

	public void setStationMaster(StationMasterEntity stationMaster) {
		this.stationMaster = stationMaster;
	}

	public PlatformMaster getPlatformMaster() {
		return platformMaster;
	}

	public void setPlatformMaster(PlatformMaster platformMaster) {
		this.platformMaster = platformMaster;
	}
	
	
	
	
	
	

}
