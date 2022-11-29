package com.april2nd.homeshow.model.mapper;

import com.april2nd.homeshow.model.DealInfoDto;
import com.april2nd.homeshow.model.HouseInfoDto;
import com.april2nd.homeshow.model.SidoGugunCodeDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;

	HouseInfoDto getOneApt(String aptCode) throws SQLException;

	List<DealInfoDto> getAptFilteredDeals(String apt , String minPriceRange, String maxPriceRange,String minDateRange, String maxDateRange) throws SQLException;
	List<DealInfoDto> getAptDeals(String apt) throws SQLException;
	List<Map<String,String>> searchByDongName(String keyword) throws SQLException;
	List<Map<String,String>> searchByAptName(String keyword) throws SQLException;
}
