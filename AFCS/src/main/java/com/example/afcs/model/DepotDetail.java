package com.example.afcs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DepotDetail")
public class DepotDetail implements Serializable {

	private static final long serialVersionUID = -2335314711833887808L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@ManyToOne
	@JoinColumn(name="MetroDetailID", nullable=false) 
	private MetroDetailsMaster metroDetailsMaster;
	
	
	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, orphanRemoval = true)
 	@JoinColumn(name = "DepotDetailID", referencedColumnName = "id")
	private List<EmergencyContact> emergencyContactList;
   	
   	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, orphanRemoval = true)
 	@JoinColumn(name = "DepotDetailID", referencedColumnName = "id")
	private List<SecurityContact> securityContactList;
}
