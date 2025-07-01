package com.pcs.tradingapp.services.curvepoint;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pcs.tradingapp.constants.ApiMessages;
import com.pcs.tradingapp.domain.CurvePoint;
import com.pcs.tradingapp.dto.request.curvepoint.CreateCurvePointDto;
import com.pcs.tradingapp.dto.response.CurvePointInfoDto;
import com.pcs.tradingapp.exceptions.CurvePointNotFoundException;
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

	public void createCurvePoint(CreateCurvePointDto dto) {
		CurvePoint curvePoint = mapper.createCurvePointDtoToCurvePoint(dto);
		curvePoint.setCreationDate(LocalDateTime.now());
		
		repository.save(curvePoint);
	}

	public CurvePointInfoDto getCurvePoint(Integer id) throws CurvePointNotFoundException {
		CurvePoint curvePoint = repository.findById(id).orElseThrow(() -> new CurvePointNotFoundException(ApiMessages.CURVEPOINT_NOT_FOUND));
		
		return mapper.curvePointToCurvePointInfoDto(curvePoint);
	}
}
