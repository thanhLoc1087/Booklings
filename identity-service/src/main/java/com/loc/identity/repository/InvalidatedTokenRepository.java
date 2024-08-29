package com.loc.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loc.identity.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
