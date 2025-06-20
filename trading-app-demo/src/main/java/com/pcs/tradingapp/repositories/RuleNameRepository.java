package com.pcs.tradingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcs.tradingapp.domain.RuleName;


public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
