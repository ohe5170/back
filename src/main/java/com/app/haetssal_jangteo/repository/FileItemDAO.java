package com.app.haetssal_jangteo.repository;

import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.domain.FileVO;
import com.app.haetssal_jangteo.dto.FileDTO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import com.app.haetssal_jangteo.mapper.FileItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileItemDAO {
    private final FileItemMapper fileItemMapper;

    // 아이템 이미지 등록
    public void save(FileDTO fileItemVO) {
        fileItemMapper.insert(fileItemVO);
    }

    // 상품 id로 상품 이미지 조회
    public List<FileItemDTO> findImagesById(Long itemId) {
        return fileItemMapper.selectImagesByItemId(itemId);
    }

    //    추가
    // 추가
    public static void save(FileItemVO fileItemVO) {
        FileItemMapper.insert(fileItemVO);
    }

    //    목록
    public List<FileItemDTO>  findAllByPostId(Long id) {
        return FileItemMapper.selectAllByItemId(id);
    }

    //    삭제
    public void delete(Long id){
        FileItemMapper.delete(id);
    }
}