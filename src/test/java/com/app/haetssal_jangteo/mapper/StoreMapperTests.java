package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.enumeration.StoreState;
import com.app.haetssal_jangteo.domain.StoreVO;
import com.app.haetssal_jangteo.dto.StoreDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class StoreMapperTests {
    @Autowired
    private StoreMapper storeMapper;

    @Test
    public void testInsert() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreMarketId(4L);
        storeDTO.setStoreOwnerId(2L);
        storeDTO.setStoreCategoryId(100L);
        storeDTO.setStoreName("테스트 장터");
        storeDTO.setStoreIntro("테스트 장터 설명");
        storeDTO.setStoreAddress("서울시 성동구 ...");

        storeMapper.insert(storeDTO);
    }

    @Test
    public void testUpdate() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(5L);
        storeDTO.setStoreCategoryId(200L);
        storeDTO.setStoreName("테스트 수정 장터");
        storeDTO.setStoreIntro("테스트 수정 장터 설명");
        storeDTO.setStoreAddress("서울 성동구");

        storeMapper.update(storeDTO.toVO());
    }

    @Test
    public void testUpdateState() {
        Long id = 5L;
        String state = "denied";

        storeMapper.updateState(id, state);
    }

    @Test
    public void testUpdateIsConfirmed() {
        storeMapper.updateIsConfirmed(5L);
    }

    @Test
    public void testSelectAll() {
        List<StoreVO> allStores = storeMapper.selectAll();
        log.info("{}.....", allStores);
    }

    @Test
    public void testSelectById() {
        Optional<StoreVO> foundStore = storeMapper.selectById(3L);
        log.info("{}.......", foundStore);
    }

    @Test
    public void testSelectByStoreName() {
        Optional<StoreVO> foundStore = storeMapper.selectByStoreName("햇살 장터");
        log.info("{}.....", foundStore);
    }

    @Test
    public void testSelectByStoreOwnerId() {
        Optional<StoreVO> foundStore = storeMapper.selectByStoreOwnerId(1L);
        log.info("{}........", foundStore);
    }

    @Test
    public void testDelete() {
        storeMapper.delete(5L);
    }
}
