package com.pcs.tradingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcs.tradingapp.domain.Rule;

public interface RuleRepository extends JpaRepository<Rule, Integer> {
}
