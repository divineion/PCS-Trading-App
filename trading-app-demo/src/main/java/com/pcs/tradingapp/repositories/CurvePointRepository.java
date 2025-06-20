package com.pcs.tradingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcs.tradingapp.domain.CurvePoint;


public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
