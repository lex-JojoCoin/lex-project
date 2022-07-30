package com.jojocoin.cryptomarket.repository;

import com.jojocoin.cryptomarket.enums.RoleName;
import com.jojocoin.cryptomarket.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

    RoleModel findByRoleName(RoleName roleName);
}
