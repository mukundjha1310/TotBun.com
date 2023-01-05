package com.totbun.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.totbun.modules.Role;
import com.totbun.modules.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer>{

	Optional<UserRole> findByUserRole(Role userRole);

}
