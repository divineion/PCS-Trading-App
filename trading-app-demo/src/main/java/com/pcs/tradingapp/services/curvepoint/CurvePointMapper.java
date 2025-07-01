package com.pcs.tradingapp.services.curvepoint;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pcs.tradingapp.domain.CurvePoint;
import com.pcs.tradingapp.dto.response.CurvePointInfoDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurvePointMapper {
	public CurvePointInfoDto curvePointToCurvePointInfoDto(CurvePoint curvePoint);
	
	public List<CurvePointInfoDto> curvePointsToCurvePointInfoDtos(List<CurvePoint> curvePoint);
}
