package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileItemMapperTest {

    @Autowired
    private FileItemMapper fileItemMapper;

    @Test
    public void testInsert() {
        FileItemDTO fileItemDTO = new FileItemDTO();
        fileItemDTO.setId(1L);
        fileItemDTO.setItemId(5L);
        fileItemDTO.setFileItemType(FileItemType.THUMBNAIL);

        fileItemMapper.insert(fileItemDTO.toFileItemVO());
    }
}
