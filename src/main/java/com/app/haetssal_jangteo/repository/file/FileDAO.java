package com.app.haetssal_jangteo.repository.file;

import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import com.app.haetssal_jangteo.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

//    파일 등록
    public void save(FileItemDTO fileItemDTO) {
        fileMapper.insert(fileItemDTO);
    }

//    아이템 이미지 등록
    public void saveItemImage(FileItemVO fileItemVO) {
        fileMapper.insertFileItem(fileItemVO);
    }

}
