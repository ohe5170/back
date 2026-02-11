package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.FileItemVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileItemMapper {
    // 상품 이미지 등록
    public void insert(FileItemVO fileItemVO);

    // 상품 이미지 삭제
    public void delete(Long id);

}
