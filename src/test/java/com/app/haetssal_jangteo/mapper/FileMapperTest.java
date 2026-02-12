package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.dto.FileDTO;
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
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileType(Filetype.IMAGE);
        fileDTO.setFileName("123123");
        fileDTO.setFileOriginName("originName");
        fileDTO.setFileSavedPath("../../path");
        fileDTO.setFileSize("100L");

        fileMapper.insert(fileDTO);
    }

    @Test
    public void testDelete() {
        fileMapper.delete(16L);
    }

}
