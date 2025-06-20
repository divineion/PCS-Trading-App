package com.pcs.tradingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcs.tradingapp.domain.Trade;


public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
