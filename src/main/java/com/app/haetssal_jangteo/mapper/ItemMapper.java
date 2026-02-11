package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.ItemOptionVO;
import com.app.haetssal_jangteo.domain.ItemVO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.dto.ItemOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
//    상품 등록
    public void insert(ItemDTO itemDTO);

//    상품 옵션 등록
    public void insertOption(ItemOptionDTO itemOptionDTO);

//    상품 수정
    public void update(ItemDTO itemDTO);

//    상품 상태 변경
    public void updateState(ItemDTO itemDTO);

//    상품 조회수 증가
    public void updateViewCount(Long id);

//    전체 상품 조회
    public List<ItemDTO> selectAll();

//    상품 옵션 전체 조회
    public List<ItemOptionVO> selectAllOptions(Long optionItemId);

//    상품 id로 상품 하나 조회

//    회원 id로 상품들 조회

//    가게 id로 상품들 조회

//    카테고리 id로 상품들 조회

}
