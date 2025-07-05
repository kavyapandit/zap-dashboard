package com.zap_dashboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "employees")
public class employeesEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
	@Column(name = "employee_id")
	private String eid;

	@Column(name = "employee_name")
	private String ename;
	
	@Column(name = "email_id")
	private String email;
	
	@Column(name = "l1_manager_name")
	private String manager;

	@Column(name = "l1_manager_email")
	private String memail;
	
	public employeesEntity() {
		super();
	}

	public employeesEntity(String eid, String ename, String email, String manager,String memail) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.email = email;
		this.manager = manager;
		this.memail=memail;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}
}
