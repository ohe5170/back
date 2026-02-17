package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.StoreVO;
import com.app.haetssal_jangteo.dto.StoreDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StoreMapper {
//    가게 등록
    public void insert(StoreDTO storeDTO);

//    가게 정보 수정
    public void update(StoreVO storeVO);

//    가게 상태 변경 (CLOSE, DENIED)
    public void updateState(@Param("id") Long id, @Param("state") String state);

//    가게 등록 승인
    public void updateIsConfirmed(Long id);

//    전체 가게 조회
    public List<StoreVO> selectAll();

//    장터 id로 소속 가게들 조회
    public List<StoreVO> selectByMarketId(Long marketId);

//    id로 가게 조회
    public Optional<StoreVO> selectById(Long id);

//    가게 이름으로 가게 조회
    public Optional<StoreVO> selectByStoreName(String storeName);

//    가게 소유주 id로 가게 조회
    public Optional<StoreVO> selectByStoreOwnerId(Long storeOwnerId);

//    가게 비활성화
    public void delete(Long id);

}
