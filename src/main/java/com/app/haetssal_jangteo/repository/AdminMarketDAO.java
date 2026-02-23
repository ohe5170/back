package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.domain.MarketVO;
import com.app.haetssal_jangteo.mapper.AdminStoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminMarketDAO {
    private final AdminStoreMapper adminStoreMapper;

    //    전체 개수
    public int findTotal(Search search) {
        return adminStoreMapper.selectTotal(search);
    }

    //    가게 목록 조회
    public List<MarketVO> findAll(Criteria criteria, Search search) {
        return adminStoreMapper.selectAll(criteria, search);
    }

    //    지역 목록 조회
    public List<String> findRegions() {
        return adminStoreMapper.selectDistinctRegions();
    }

    //    수정
    public void setMarket(MarketVO marketVO) {
        adminStoreMapper.update(marketVO);
    }
}
