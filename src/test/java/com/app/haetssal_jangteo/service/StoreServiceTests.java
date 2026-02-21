package com.app.haetssal_jangteo.service;

import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.StoreSearch;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.repository.*;
import com.app.haetssal_jangteo.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class StoreServiceTests {

    @Autowired
    private StoreDAO storeDAO;
    @Autowired
    private StoreDetailDAO storeDetailDAO;
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private FileItemDAO fileItemDAO;
    @Autowired
    private FileStoreDAO fileStoreDAO;
    @Autowired
    private ItemDAO itemDAO;

    @Test
    public void testSave() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreMarketId(4L);
        storeDTO.setStoreOwnerId(2L);
        storeDTO.setStoreCategoryId(200L);
        storeDTO.setStoreName("테스트 장터2");
        storeDTO.setStoreIntro("테스트 장터 설명2");
        storeDTO.setStoreAddress("서울시 중구...");

        storeDAO.save(storeDTO);

        // 파일 저장
        FileDTO fileDTO = new FileDTO();
        UUID uuid = UUID.randomUUID();
        fileDTO.setFileType(Filetype.IMAGE);
        fileDTO.setFileName(uuid.toString() + "_" + "storeProfile");
        fileDTO.setFileOriginName("image02");
        fileDTO.setFileSavedPath("../../path");
        fileDTO.setFileSize("100");

        fileDAO.save(fileDTO);

        // 상품 이미지 정보 저장
        FileStoreDTO fileStoreDTO = new FileStoreDTO();
        fileStoreDTO.setId(fileDTO.getId());
        fileStoreDTO.setStoreId(storeDTO.getId());

        fileStoreDAO.save(fileStoreDTO.toFileStoreVO());
    }

    @Test
    public void testFindBySearch() {
        StoreSearch storeSearch = new StoreSearch();
        storeSearch.setRegion("서울");
        storeSearch.setMarketId(4L);
        storeSearch.setCategoryId(200L);
        storeSearch.setOrderValue("desc");

        StoreWithPagingDTO storeWithPagingDTO = new StoreWithPagingDTO();
        Criteria criteria = new Criteria(1, storeDAO.findTotal(storeSearch));

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

        log.info("{}....", storeWithPagingDTO);
    }

    @Test
    public void detail() {
        Optional<StoreDetailDTO> storeDetailDTO = storeDetailDAO.findById(2L);

        if(storeDetailDTO.isPresent()) {
            StoreDetailDTO dto = storeDetailDTO.get();

            // item 가져오기 + 썸내일 같이 가져오기
            List<ItemDTO> items = itemDAO.findByStoreId(dto.getId()).stream()
                    .map(itemDTO -> {
                        List<FileItemDTO> thumbnails = fileItemDAO.findImagesByIdAndFileItemType(itemDTO.getId(), "thumbnail").stream().collect(Collectors.toList());
                        itemDTO.setItemFiles(thumbnails);
                        return itemDTO;
                    }).collect(Collectors.toList());

            // 마지막 로그인 nn전
            String latestLogin = DateUtils.toRelativeTime(dto.getOwnerLatestLogin());
            dto.setOwnerLatestLogin(latestLogin);

            // 상품 중 10개 만 가져오기
            dto.setStoreItems(items.subList(0, Math.min(items.size(), 10)));

            log.info("{}.......", dto);
        } else {
            log.info("상품 없음....");
        }
    }
}
