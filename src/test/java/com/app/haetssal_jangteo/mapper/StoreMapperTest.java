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
public class StoreMapperTest {
    @Autowired
    private StoreMapper storeMapper;

    @Test
    public void testInsert() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreMarketId(2L);
        storeDTO.setStoreOwnerId(1L);
        storeDTO.setStoreName("햇살 장터");
        storeDTO.setStoreIntro("가게 설명1");
        storeDTO.setStoreAddress("서울시 성동구 ...");

        StoreDTO storeDTO2 = new StoreDTO();
        storeDTO2.setStoreMarketId(1L);
        storeDTO2.setStoreOwnerId(1L);
        storeDTO2.setStoreName("구름 장터");
        storeDTO2.setStoreIntro("가게 설명2");
        storeDTO2.setStoreAddress("서울시 종로 ...");

        StoreDTO storeDTO3 = new StoreDTO();
        storeDTO3.setStoreMarketId(3L);
        storeDTO3.setStoreOwnerId(1L);
        storeDTO3.setStoreName("바람 장터");
        storeDTO3.setStoreIntro("가게 설명3");
        storeDTO3.setStoreAddress("서울시 강남구 ...");

        storeMapper.insert(storeDTO);
        storeMapper.insert(storeDTO2);
        storeMapper.insert(storeDTO3);
    }

    @Test
    public void testUpdate() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(2L);
        storeDTO.setStoreName("햇빛 장터");
        storeDTO.setStoreIntro("가게 설명 수정1");

        storeMapper.update(storeDTO);
    }

    @Test
    public void testUpdateState() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(2L);
        storeDTO.setStoreState(StoreState.CLOSE);

        storeMapper.updateState(storeDTO);
    }

    @Test
    public void testUpdateIsConfirmed() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(3L);
        storeDTO.setStoreIsConfirmed(true);

        storeMapper.updateIsConfirmed(storeDTO);
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
}
