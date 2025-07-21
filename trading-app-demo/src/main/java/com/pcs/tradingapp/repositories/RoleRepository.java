package com.pcs.tradingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcs.tradingapp.domain.Role;
import com.pcs.tradingapp.domain.RoleName;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByName(RoleName name);
}
