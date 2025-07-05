
package com.zap_dashboard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class usersEntity {

    @Id
    @Column(name = "emp_id", nullable = false, unique = true)
    private String empId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "phone", nullable = false)
    private String phone;
    
    @Column(name = "profilepic", nullable = false)
    private String profilepic;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone2) {
		this.phone = phone2;
	}

	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

	public usersEntity(String empId, String name, String role, String designation, String password, String email,
			String phone, String profilepic) {
		super();
		this.empId = empId;
		this.name = name;
		this.role = role;
		this.designation = designation;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.profilepic = profilepic;
	}
	
	public usersEntity() {}
    
}