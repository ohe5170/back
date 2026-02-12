package com.app.haetssal_jangteo.service.item;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.exception.ItemfoundFailException;
import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.domain.ItemOptionVO;
import com.app.haetssal_jangteo.domain.ItemVO;
import com.app.haetssal_jangteo.dto.FileDTO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.dto.ItemOptionDTO;
import com.app.haetssal_jangteo.repository.CategoryDAO;
import com.app.haetssal_jangteo.repository.FileDAO;
import com.app.haetssal_jangteo.repository.FileItemDAO;
import com.app.haetssal_jangteo.repository.ItemDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ItemService {
    private final ItemDAO itemDAO;
    private final CategoryDAO categoryDAO;
    private final FileDAO fileDAO;
    private final FileItemDAO fileItemDAO;

    // 상품 등록
    public void save(ItemDTO itemDTO,
                     ArrayList<MultipartFile> itemThumbnails,
                     ArrayList<MultipartFile> itemDescImages,
                     ArrayList<MultipartFile> itemSellerImages,
                     ArrayList<MultipartFile> itemRefundImages) {
        // 이미지 저장용 경로 지정
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;


        // 임시로 상품 가게 id 등록
        itemDTO.setItemStoreId(2L);

        itemDAO.save(itemDTO);

        // 옵션이 있다면, 저장
        List<ItemOptionDTO> options = itemDTO.getItemOptions();
        if(!options.isEmpty()) {
            itemDTO.getItemOptions().forEach(option -> {
                // 저장된 상품 id 주입
                option.setOptionItemId(itemDTO.getId());
                itemDAO.saveOption(option);
            });
        }

        // 받아온 이미지들이 있으면 저장하기
        // 받아온 이미지들에 따라 FileItemType 설정
        Map<List<MultipartFile>, FileItemType> imageMap = Map.of(
                itemThumbnails, FileItemType.THUMBNAIL,
                itemDescImages, FileItemType.DESC,
                itemSellerImages, FileItemType.SELLER_INFO,
                itemRefundImages, FileItemType.REFUND
        );

        // 각 이미지를 forEach로 저장하는 메소드("saveImages")에 입력
        imageMap.forEach((images, fileItemType) -> {
            if(!images.isEmpty()) {
                saveImages(images, itemDTO.getId(), todayPath, path, fileItemType);
            }
        });

    }

    // 상품 불러오기
    public void detail(Long id) {

    }

    // 오늘자 경로 생성
    public String getTodayPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    // 이미지 저장 로직
    private void saveImages(List<MultipartFile> images, Long itemId, String todayPath, String path, FileItemType fileItemType) {
        images.forEach(image -> {
            if(image.getOriginalFilename().isEmpty()) {
                return;
            }

            UUID uuid = UUID.randomUUID();
            FileDTO fileDTO = new FileDTO();
            fileDTO.setFileType(image.getContentType().contains("image") ? Filetype.IMAGE : Filetype.DOCUMENT);
            fileDTO.setFileName(uuid.toString() + "_" + image.getOriginalFilename());
            fileDTO.setFileOriginName(image.getOriginalFilename());
            fileDTO.setFileSavedPath(todayPath);
            fileDTO.setFileSize(String.valueOf(image.getSize()));
            fileDAO.save(fileDTO);

            FileItemDTO fileItemDTO = new FileItemDTO();
            fileItemDTO.setId(fileDTO.getId());
            fileItemDTO.setItemId(itemId);
            fileItemDTO.setFileItemType(fileItemType);
            fileItemDAO.save(fileItemDTO.toFileItemVO());

            File directory = new File(path);
            if(!directory.exists()) {
                directory.mkdirs();
            }

            try {
                image.transferTo(new File(path, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
