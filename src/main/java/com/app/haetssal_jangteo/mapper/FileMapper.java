package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.domain.FileVO;
import com.app.haetssal_jangteo.dto.FileDTO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
//    공통 부분
    // 파일 등록
    public void insert(FileDTO fileDTO);

    // 파일 삭제
    public void delete(Long id);
}
