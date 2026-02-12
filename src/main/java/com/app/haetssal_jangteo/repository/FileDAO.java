package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.dto.FileDTO;
import com.app.haetssal_jangteo.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

//    파일 등록
    public void save(FileDTO fileDTO) {
        fileMapper.insert(fileDTO);
    }

}
