package com.app.haetssal_jangteo.repository.file;

import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.dto.FileDTO;
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
    public void save(FileDTO fileDTO) {
        fileMapper.insert(fileDTO);
    }

}
