package com.app.haetssal_jangteo.mapper;

import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.domain.FileVO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
//    공통 부분
    // 파일 등록
    public void insert(FileItemDTO fileItemDTO);

    // 파일 삭제
    public void delete(Long id);


//    상품 이미지 부분 --------------------

    // 상품 이미지 등록
    public void insertFileItem(FileItemVO fileItemVO);

    // 상품 이미지 삭제
    public void deleteFileItem(Long id);


//    유저 프로필 이미지 부분 --------------


//    리뷰 이미지 부분 ---------------------


//    신고 첨부파일 부분 ----------------------

}
