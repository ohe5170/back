package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.domain.MarketVO;
import com.app.haetssal_jangteo.mapper.AdminMarketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminMarketDAO {
    private final AdminMarketMapper adminMarketMapper;

    //    전체 개수
    public int findTotal(Search search) {
        return adminMarketMapper.selectTotal(search);
    }

    //    가게 목록 조회
    public List<MarketVO> findAll(Criteria criteria, Search search) {
        return adminMarketMapper.selectAll(criteria, search);
    }

    //    지역 목록 조회
    public List<String> findRegions() {
        return adminMarketMapper.selectDistinctRegions();
    }

    //    수정
    public void setMarket(MarketVO marketVO) {
        adminMarketMapper.update(marketVO);
    }
}
