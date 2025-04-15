package com.fouadev.usermanagementservice.repositories;

import com.fouadev.usermanagementservice.entities.AppPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<AppPermission, String> {
}
