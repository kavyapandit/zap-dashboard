package com.zap_dashboard.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "login")
public class loginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @Column(name = "emp_id")
    private String empId;

    @Column(name = "location")
    private String location;

    @Column(name = "login_time")
    private LocalDateTime loginTime;
    

    @Column(name = "logout_time")
    private LocalDateTime logoutTime;


	public loginEntity(Long id, String empId, String location, LocalDateTime loginTime, LocalDateTime logoutTime) {
		super();
		this.id = id;
		this.empId = empId;
		this.location = location;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}


	public loginEntity() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmpId() {
		return empId;
	}


	public void setEmpId(String empId) {
		this.empId = empId;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public LocalDateTime getLoginTime() {
		return loginTime;
	}


	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}


	public LocalDateTime getLogoutTime() {
		return logoutTime;
	}


	public void setLogoutTime(LocalDateTime logoutTime) {
		this.logoutTime = logoutTime;
	}

}