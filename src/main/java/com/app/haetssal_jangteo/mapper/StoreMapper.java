package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.StoreVO;
import com.app.haetssal_jangteo.dto.StoreDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StoreMapper {
//    가게 등록
    public void insert(StoreDTO storeDTO);

//    가게 정보 수정
    public void update(StoreDTO storeDTO);

//    가게 상태 변경 (CLOSE, DENIED)
    public void updateState(StoreDTO storeDTO);

//    가게 등록 승인
    public void updateIsConfirmed(StoreDTO storeDTO);

//    전체 가게 검색
    public List<StoreVO> selectAll();

//    장터 id로 가게들 검색
    public List<StoreVO> selectByMarketId(Long storeMarketId);

//    id로 가게 검색
    public Optional<StoreVO> selectById(Long id);

//    가게 이름으로 가게 검색
    public Optional<StoreVO> selectByStoreName(String storeName);

//    소유주 id로 가게 검색
    public Optional<StoreVO> selectByStoreOwnerId(Long storeOwnerId);

}
