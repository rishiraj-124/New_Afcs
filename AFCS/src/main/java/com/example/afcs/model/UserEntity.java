package com.example.afcs.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "User")
@NamedQueries
({
	@NamedQuery(name="UserEntity.findByMobile", query="SELECT ue FROM UserEntity ue WHERE ue.mobile = :mobile"),
	@NamedQuery(name="UserEntity.findByToken", query="SELECT ue FROM UserEntity ue WHERE ue.token = :token"),
})
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 7290798953394355234L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "firstName")
    private String firstName;
    
    @Column(name = "lastName")
    private String lastName;
    
    @Column(name = "fullName")
    private String fullName;
    
    @Column(name = "address1")
    private String address1;
	
    @Column(name = "address2")
	private String address2;
	
    @Column(name = "city")
	private String city;
	
    @Column(name = "state")
	private String state;
	
    @Column(name = "pincode")
	private String pincode;
	
    @Column(name = "country")
	private String country;
    
    @Column(name = "emailId" , unique = true)
    @NotNull @NotEmpty
    private String emailId;
    
    
    @Column(name = "salt")
    private String salt;
    
    @Column(name = "token")
    private String token;
    
    @Column(name = "userPassword")
    private String userPassword;
    
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "userRole")
    private String userRole;
    
    @Column(name = "iMEI")
    private String imei;
    
    @Column(name = "iPAddress")
    private String ipAddress;
    
    @Column(name = "mobileOtp")
    private String mobileOtp;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getMobileOtp() {
		return mobileOtp;
	}
	public void setMobileOtp(String mobileOtp) {
		this.mobileOtp = mobileOtp;
	}
    
    
    
    
    
}
