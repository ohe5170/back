package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    // 리뷰 등록
    public void insert(ReviewDTO reviewDTO);

    // 유저가 작성한 리뷰 목록
    public List<ReviewDTO> selectAllByUserId(Long userId);

    // 유저가 작성한 리뷰 개수
    public int selectCountByUserId(Long userId);

    // 특정 상품의 리뷰 목록
    public List<ReviewDTO> selectAllByItemId(Long itemId);
}
