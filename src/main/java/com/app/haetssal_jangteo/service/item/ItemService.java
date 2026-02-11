package com.app.haetssal_jangteo.service.item;

import com.app.haetssal_jangteo.domain.CategoryVO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.dto.ItemOptionDTO;
import com.app.haetssal_jangteo.repository.category.CategoryDAO;
import com.app.haetssal_jangteo.repository.file.FileDAO;
import com.app.haetssal_jangteo.repository.item.ItemDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ItemService {
    private final ItemDAO itemDAO;
    private final CategoryDAO categoryDAO;
    private final FileDAO fileDAO;

//    상품 등록
    public void save(ItemDTO itemDTO) {

//      임시로 상품 가게 id 등록
        itemDTO.setItemStoreId(2L);

        itemDAO.save(itemDTO);

        // 옵션이 있다면, 저장
        List<ItemOptionDTO> options = itemDTO.getItemOptions();
        if(!options.isEmpty()) {
            itemDTO.getItemOptions().forEach(option -> {
                // 저장된 상품 id 주입
                option.setOptionItemId(itemDTO.getId());
                itemDAO.saveOption(option);
            });
        }

//        나중에 받아온 이미지도 저장하기
//        FileItemDTO fileItemDTO = null;
//
//        multipartFiles.forEach(multipartFile -> {
//        });
    }
}
