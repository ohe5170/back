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
        fileItemDTO.setFileSavedPath("../../path");
        fileItemDTO.setFileOriginPath("../../oripath");
        fileItemDTO.setFileSize("100L");

        fileItemDTO.setItemId(1L);
        fileItemDTO.setFileItemType(FileItemType.THUMBNAIL);

        fileMapper.insert(fileItemDTO);
        fileMapper.insertFileItem(fileItemDTO.toFileItemVO());
    }

    @Test
    public void testDelete() {
        fileMapper.deleteFileItem(16L);
        fileMapper.delete(16L);
    }

}
