package com.zap_dashboard.Repository;

import com.zap_dashboard.entity.employeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<employeesEntity, String> {

}
