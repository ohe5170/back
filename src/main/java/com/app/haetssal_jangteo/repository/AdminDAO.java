package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminDAO {
    private final AdminMapper adminMapper;

    //  전체 개수
    public int findTotal(Search search) {
        return adminMapper.selectTotal(search);
    }

    //    상품 전체 조회
    public List<ItemDTO> findAll(Criteria criteria, Search search) {
        return adminMapper.selectAll(criteria, search);
    }
}


