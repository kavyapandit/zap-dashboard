package com.zap_dashboard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zap_dashboard.entity.loginEntity;

@Repository
public interface loginRepository extends JpaRepository<loginEntity, Long>
{
	loginEntity findTopByEmpIdOrderByLoginTimeDesc(String empId);
	
	 List<loginEntity> findByEmpIdOrderByLoginTimeDesc(String empId);

}
