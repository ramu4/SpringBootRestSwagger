package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="customertab")
public class Customer {
	
	@Id
	@GeneratedValue
	@Column(name="cid")
	private Integer cId;
	@Column(name="cname")
	private String cName;
	@Column(name="serviceid")
	private String serviceId;
	@Column(name="mode")
	private String cmode;
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getCmode() {
		return cmode;
	}
	public void setCmode(String cmode) {
		this.cmode = cmode;
	}
	
	

}
