package com.zap_dashboard.Repository;

import com.zap_dashboard.entity.usersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<usersEntity, String> {
    
    @Query("SELECT u FROM usersEntity u WHERE u.empId = :empId AND u.password = :password")
    Optional<usersEntity> findByLoginCredentials(String empId, String password);

    @Query("SELECT u.name FROM usersEntity u WHERE u.empId = :empId")
    Optional<String> findNameByEmpId(String empId);
}
