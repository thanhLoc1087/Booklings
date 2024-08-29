package com.loc.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loc.identity.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
