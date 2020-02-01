package com.test.hci.repository;

import com.test.hci.model.Module;
import com.test.hci.model.UserModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserModuleRepo extends JpaRepository<UserModule, String> {
    @Query("FROM UserModule userModule WHERE userModule.user.id = :userId ORDER BY userModule.moduleOrder ASC, userModule.module.name ASC")
    List<UserModule> findAllByUserId(@Param("userId") String userId);

    UserModule findByUserIdAndModuleNameAndModuleOrder(String userId, String moduleName, int moduleOrder);
}
