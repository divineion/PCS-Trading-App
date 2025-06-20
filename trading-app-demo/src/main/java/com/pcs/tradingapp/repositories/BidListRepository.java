package com.pcs.tradingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcs.tradingapp.domain.BidList;


public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
