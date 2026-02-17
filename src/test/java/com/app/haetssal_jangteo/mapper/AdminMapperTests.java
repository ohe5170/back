package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.ItemVO;
import com.app.haetssal_jangteo.dto.ItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class AdminMapperTests {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testInsert() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(8L);
        itemDTO.setItemStoreId(1L);
        itemDTO.setItemCategoryId(1L);
        itemDTO.setItemName("사과 박스");
        itemDTO.setItemPrice("15000");
        itemDTO.setItemStock("40");
        itemDTO.setItemContent("사과 1 BOX (10KG)");

        adminMapper.insert(itemDTO);
    }

    @Test
    public void testSelectById() {
        Optional<ItemVO> foundItem = adminMapper.selectById(1L);
        log.info("{}........", foundItem);
    }
}

