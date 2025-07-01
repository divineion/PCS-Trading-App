package com.pcs.tradingapp.services.curvepoint;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pcs.tradingapp.domain.CurvePoint;
import com.pcs.tradingapp.dto.response.CurvePointInfoDto;
import com.pcs.tradingapp.repositories.CurvePointRepository;

@Service
public class CurvePointService {
	private final CurvePointRepository repository;
	
	private final CurvePointMapper mapper;
	
	public CurvePointService(CurvePointRepository repository, CurvePointMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<CurvePointInfoDto> findAllCurvePoints() {
		List<CurvePoint> curvePoints =  repository.findAll();
		
		return mapper.curvePointsToCurvePointInfoDtos(curvePoints);
	}

}
