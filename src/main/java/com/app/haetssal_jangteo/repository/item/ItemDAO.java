package com.app.haetssal_jangteo.repository.item;

import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.dto.ItemOptionDTO;
import com.app.haetssal_jangteo.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemDAO {
    private final ItemMapper itemMapper;

//    상품 등록
    public void save(ItemDTO itemDTO) {
        itemMapper.insert(itemDTO);
    }

//    상품 옵션 등록
    public void saveOption(ItemOptionDTO option) {
        itemMapper.insertOption(option);
    }

//    상품 전체 조회
    public List<ItemDTO> findAll() {
        return itemMapper.selectAll();
    }
}
