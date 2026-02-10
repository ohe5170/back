package com.app.haetssal_jangteo.service.item;

import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.dto.ItemOptionDTO;
import com.app.haetssal_jangteo.repository.item.ItemDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ItemService {
    private ItemDAO itemDAO;

//    상품 등록
    public void save(ItemDTO itemDTO) {

    }
}
