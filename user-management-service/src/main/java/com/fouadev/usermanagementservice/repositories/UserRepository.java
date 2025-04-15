package com.fouadev.usermanagementservice.repositories;

import com.fouadev.usermanagementservice.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, String> {
}
