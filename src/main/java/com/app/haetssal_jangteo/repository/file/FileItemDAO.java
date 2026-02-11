package com.app.haetssal_jangteo.repository.file;

import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.mapper.FileItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileItemDAO {
    private final FileItemMapper fileItemMapper;

    //    아이템 이미지 등록
    public void save(FileItemVO fileItemVO) {
        fileItemMapper.insert(fileItemVO);
    }

}
