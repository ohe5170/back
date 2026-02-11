package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.domain.FileVO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileMapperTest {

    @Autowired
    private FileMapper fileMapper;

    @Test
    public void testInsert() {
        FileItemDTO fileItemDTO = new FileItemDTO();
        fileItemDTO.setFileType(Filetype.IMAGE);
        fileItemDTO.setFileName("123123");
        fileItemDTO.setFileOriginName("originName");
        fileItemDTO.setFileSavedPath("../../path");
        fileItemDTO.setFileSize("100L");

        fileMapper.insert(fileItemDTO);
    }

    @Test
    public void testDelete() {
        fileMapper.delete(16L);
    }

}
