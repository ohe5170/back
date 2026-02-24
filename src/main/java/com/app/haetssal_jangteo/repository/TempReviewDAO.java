package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.dto.ReviewDTO;
import com.app.haetssal_jangteo.dto.StoreReviewDTO;
import com.app.haetssal_jangteo.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TempReviewDAO {
    private final ReviewMapper reviewMapper;

    // 가게 id로 해당 가게 상품의 후기 조회
    public List<StoreReviewDTO> findByStoreId(Long storeId) {
        return reviewMapper.selectByStoreId(storeId);
    }
}
