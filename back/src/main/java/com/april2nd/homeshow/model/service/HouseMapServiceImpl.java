package com.april2nd.homeshow.model.service;

import com.april2nd.homeshow.model.DealInfoDto;
import com.april2nd.homeshow.model.HouseInfoDto;
import com.april2nd.homeshow.model.SearchResultDto;
import com.april2nd.homeshow.model.SidoGugunCodeDto;
import com.april2nd.homeshow.model.mapper.HouseMapMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong, String minPriceRange, String maxPriceRange, String minDateRange,
										   String maxDateRange) throws Exception {
		List<HouseInfoDto> list = sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
		for (int i = 0; i < list.size(); i++) {
			List<DealInfoDto> filteredDealList = sqlSession.getMapper(HouseMapMapper.class).getAptFilteredDeals(
					list.get(i).getAptCode(), minPriceRange, maxPriceRange, minDateRange, maxDateRange);
			List<DealInfoDto> dealList = sqlSession.getMapper(HouseMapMapper.class)
					.getAptDeals(list.get(i).getAptCode());
			list.get(i).setDeals(dealList);
			list.get(i).setFilteredDeals(filteredDealList);
		}
		return list;
	}

	@Override
	public List<HouseInfoDto> getOneApt(String aptCode, String minPriceRange, String maxPriceRange, String minDateRange,
										String maxDateRange) throws Exception {
		List<HouseInfoDto> list =  new ArrayList<>();
		HouseInfoDto houseDto = sqlSession.getMapper(HouseMapMapper.class).getOneApt(aptCode);
		houseDto.setDeals(sqlSession.getMapper(HouseMapMapper.class).getAptDeals(aptCode));
		houseDto.setFilteredDeals(sqlSession.getMapper(HouseMapMapper.class).getAptFilteredDeals(aptCode, minPriceRange,
				maxPriceRange, minDateRange, maxDateRange));

		list.add(houseDto);

		return list;
	}

	@Override
	public SearchResultDto getSearchByKeyword(String keyword) throws Exception {

		SearchResultDto result = new SearchResultDto();
		result.setAptResult(sqlSession.getMapper(HouseMapMapper.class).searchByAptName(keyword));
		result.setDongResult(sqlSession.getMapper(HouseMapMapper.class).searchByDongName(keyword));

		return result;
	}

//	@Override
//	public List<DealInfoDto> getAptDeals(String apt, String minPriceRange, String maxPriceRange) throws Exception {
//		return sqlSession.getMapper(HouseMapMapper.class).getAptDeals(apt, minPriceRange, maxPriceRange);
//	}

}
