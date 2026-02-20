package com.app.haetssal_jangteo.service.store;

import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.exception.FileNotFoundException;
import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.StoreSearch;
import com.app.haetssal_jangteo.domain.FileVO;
import com.app.haetssal_jangteo.dto.FileDTO;
import com.app.haetssal_jangteo.dto.FileStoreDTO;
import com.app.haetssal_jangteo.dto.StoreDTO;
import com.app.haetssal_jangteo.dto.StoreWithPagingDTO;
import com.app.haetssal_jangteo.repository.FileDAO;
import com.app.haetssal_jangteo.repository.FileStoreDAO;
import com.app.haetssal_jangteo.repository.StoreDAO;
import com.app.haetssal_jangteo.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class StoreService {
    private final StoreDAO storeDAO;
    private final FileDAO fileDAO;
    private final FileStoreDAO fileStoreDAO;

    // 가게 등록
    public void save(StoreDTO storeDTO, MultipartFile multipartFile) {
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        FileDTO fileDTO = new FileDTO();
        FileStoreDTO fileStoreDTO = new FileStoreDTO();

        storeDAO.save(storeDTO);
        fileStoreDTO.setStoreId(storeDTO.getId());
        if(multipartFile.getOriginalFilename().isEmpty()) {
            return;
        }
        UUID uuid = UUID.randomUUID();
        fileDTO.setFileSavedPath(todayPath);
        fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
        fileDTO.setFileOriginName(multipartFile.getOriginalFilename());
        fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
        fileDTO.setFileType(multipartFile.getContentType().contains("image") ? Filetype.IMAGE : Filetype.DOCUMENT);
        fileDAO.save(fileDTO);

        fileStoreDTO.setId(fileDTO.getId());
        fileStoreDAO.save(fileStoreDTO.toFileStoreVO());

        File directory = new File(path);
        if(!directory.exists()) {
            directory.mkdirs();
        }

        try {
            multipartFile.transferTo(new File(path, fileDTO.getFileName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 가게 정보 수정
    public void setStore(StoreDTO storeDTO, MultipartFile multipartFile) {
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        storeDAO.setStore(storeDTO.toVO());

        FileDTO fileDTO = new FileDTO();
        FileStoreDTO fileStoreDTO = new FileStoreDTO();

        // 새로 받아온 상점 프로필 저장
        fileStoreDTO.setStoreId(storeDTO.getId());
        if(multipartFile.getOriginalFilename().isEmpty()) {
            return;
        }
        UUID uuid = UUID.randomUUID();
        fileDTO.setFileSavedPath(todayPath);
        fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
        fileDTO.setFileOriginName(multipartFile.getOriginalFilename());
        fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
        fileDTO.setFileType(multipartFile.getContentType().contains("image") ? Filetype.IMAGE : Filetype.DOCUMENT);
        fileDAO.save(fileDTO);

        fileStoreDTO.setId(fileDTO.getId());
        fileStoreDAO.save(fileStoreDTO.toFileStoreVO());

        File directory = new File(path);
        if(!directory.exists()) {
            directory.mkdirs();
        }

        try {
            multipartFile.transferTo(new File(path, fileDTO.getFileName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ----------- 삭제 -----------
        String fileId = storeDTO.getToDeleteFileId();
        if(fileId != null) {
            FileVO fileVO = fileDAO.findById(Long.valueOf(fileId)).orElseThrow(FileNotFoundException::new);
            File file = new File(rootPath + fileVO.getFileSavedPath(), fileVO.getFileName());
            if(file.exists()) {
                file.delete();
            }
            fileStoreDAO.delete(Long.valueOf(fileId));
            fileDAO.delete(Long.valueOf(fileId));
        };
    }

    // 가게 상태 변경
    public void setState(Long id, String state) {
        storeDAO.setState(id, state);
    }

    // 가게 등록 승인
    public void changeIsConfirmed(Long id) {
        storeDAO.changeIsConfirmed(id);
    }

    // 가게 전체 조회
    public List<StoreDTO> findAll() {
        return storeDAO.findAll();
    }

    // 검색으로 가게 조회
    public StoreWithPagingDTO findBySearch(int page, StoreSearch storeSearch) {
        StoreWithPagingDTO storeWithPagingDTO = new StoreWithPagingDTO();
        Criteria criteria = new Criteria(page, storeDAO.findTotal(storeSearch));

        List<StoreDTO> stores = storeDAO.findBySearch(criteria, storeSearch);

        criteria.setHasMore(stores.size() > criteria.getRowCount());
        storeWithPagingDTO.setCriteria(criteria);

        if(criteria.isHasMore()) {
            stores.remove(stores.size() - 1);
        }

        stores.forEach(storeDTO -> {
            storeDTO.setCreatedDatetime(DateUtils.toRelativeTime(storeDTO.getCreatedDatetime()));
        });
        storeWithPagingDTO.setStores(stores);

        return storeWithPagingDTO;
    }

    // 장터 id로 소속 가게들 조회

    // id로 가게 조회

    // 가게 이름으로 조회

    // 소유주 id로 조회

    // 가게 비활성화


    // 오늘자 경로 생성
    public String getTodayPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
