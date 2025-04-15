package com.fouadev.usermanagementservice.repositories;

import com.fouadev.usermanagementservice.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, String> {
}
