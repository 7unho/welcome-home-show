package com.april2nd.homeshow.model.service;

import com.april2nd.homeshow.model.HouseInfoDto;
import com.april2nd.homeshow.model.SearchResultDto;
import com.april2nd.homeshow.model.SidoGugunCodeDto;

import java.util.List;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;

	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;

	List<HouseInfoDto> getAptInDong(String dong, String minPriceRange, String maxPriceRange, String minDateRange,
									String maxDateRange) throws Exception;

	List<HouseInfoDto> getOneApt(String aptCode, String minPriceRange, String maxPriceRange, String minDateRange,
								 String maxDateRange) throws Exception;

//	List<DealInfoDto> getAptDeals(String apt, String minPriceRange, String maxPriceRange) throws Exception;

	SearchResultDto getSearchByKeyword(String keyword) throws Exception;
}
