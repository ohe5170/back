package com.app.haetssal_jangteo.service;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.dto.FileDTO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import com.app.haetssal_jangteo.dto.FileStoreDTO;
import com.app.haetssal_jangteo.dto.StoreDTO;
import com.app.haetssal_jangteo.repository.FileDAO;
import com.app.haetssal_jangteo.repository.FileItemDAO;
import com.app.haetssal_jangteo.repository.FileStoreDAO;
import com.app.haetssal_jangteo.repository.StoreDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Slf4j
public class StoreServiceTests {

    @Autowired
    private StoreDAO storeDAO;
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private FileStoreDAO fileStoreDAO;

    @Test
    public void testSave() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreMarketId(4L);
        storeDTO.setStoreOwnerId(2L);
        storeDTO.setStoreCategoryId(200L);
        storeDTO.setStoreName("테스트 장터2");
        storeDTO.setStoreIntro("테스트 장터 설명2");
        storeDTO.setStoreAddress("서울시 중구...");

        storeDAO.save(storeDTO);

        // 파일 저장
        FileDTO fileDTO = new FileDTO();
        UUID uuid = UUID.randomUUID();
        fileDTO.setFileType(Filetype.IMAGE);
        fileDTO.setFileName(uuid.toString() + "_" + "storeProfile");
        fileDTO.setFileOriginName("image02");
        fileDTO.setFileSavedPath("../../path");
        fileDTO.setFileSize("100");

        fileDAO.save(fileDTO);

        // 상품 이미지 정보 저장
        FileStoreDTO fileStoreDTO = new FileStoreDTO();
        fileStoreDTO.setId(fileDTO.getId());
        fileStoreDTO.setStoreId(storeDTO.getId());

        fileStoreDAO.save(fileStoreDTO.toFileStoreVO());
    }
}
