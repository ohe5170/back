package com.app.haetssal_jangteo.service;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.dto.FileDTO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.dto.ItemOptionDTO;
import com.app.haetssal_jangteo.repository.CategoryDAO;
import com.app.haetssal_jangteo.repository.FileDAO;
import com.app.haetssal_jangteo.repository.FileItemDAO;
import com.app.haetssal_jangteo.repository.ItemDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@SpringBootTest
@Slf4j
public class ItemServiceTest {
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private FileItemDAO fileItemDAO;

    @Test
    public void testSave() {
        // 상품 저장
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemStoreId(3L);
        itemDTO.setItemCategoryId(100L);
        itemDTO.setItemName("사과 박스");
        itemDTO.setItemPrice("15000");
        itemDTO.setItemStock("40");
        itemDTO.setItemContent("사과 1 BOX (10KG)");

        itemDAO.save(itemDTO);

        // 상품 옵션 저장
        ItemOptionDTO option = new ItemOptionDTO();
        option.setOptionItemId(itemDTO.getId());
        option.setOptionName("옵션2");
        option.setOptionDetail("option detail2");
        option.setOptionPrice("100000");
        option.setOptionStock("10");

        itemDAO.saveOption(option);

        // 파일 저장
        FileDTO fileDTO = new FileDTO();
        UUID uuid = UUID.randomUUID();
        fileDTO.setFileType(Filetype.IMAGE);
        fileDTO.setFileName(uuid.toString() + "_" + "file");
        fileDTO.setFileOriginName("itemImage");
        fileDTO.setFileSavedPath("../../path");
        fileDTO.setFileSize("100");

        fileDAO.save(fileDTO);

        // 상품 이미지 정보 저장
        FileItemDTO fileItemDTO = new FileItemDTO();
        fileItemDTO.setId(fileDTO.getId());
        fileItemDTO.setItemId(itemDTO.getId());
        fileItemDTO.setFileItemType(FileItemType.THUMBNAIL);

        fileItemDAO.save(fileItemDTO.toFileItemVO());

    }

    @Test
    public void selectItemById() {

    }
}
