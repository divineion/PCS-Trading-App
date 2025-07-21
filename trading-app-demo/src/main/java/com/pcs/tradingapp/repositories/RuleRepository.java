package com.pcs.tradingapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcs.tradingapp.domain.Rule;

public interface RuleRepository extends JpaRepository<Rule, Integer> {

	Optional<Rule> findByName(String name);
}
