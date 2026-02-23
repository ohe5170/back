//package com.app.haetssal_jangteo.service.admin;
//
//import com.app.haetssal_jangteo.common.enumeration.FileItemType;
//import com.app.haetssal_jangteo.common.pagination.Criteria;
//import com.app.haetssal_jangteo.common.search.Search;
//import com.app.haetssal_jangteo.dto.FileDTO;
//import com.app.haetssal_jangteo.dto.FileItemDTO;
//import com.app.haetssal_jangteo.dto.ItemDTO;
//import com.app.haetssal_jangteo.domain.MarketVO;
//import com.app.haetssal_jangteo.dto.ItemWithPagingDTO;
//import com.app.haetssal_jangteo.dto.MarketDTO;
//import com.app.haetssal_jangteo.dto.MarketWithPagingDTO;
//import com.app.haetssal_jangteo.repository.AdminItemDAO;
//import com.app.haetssal_jangteo.repository.AdminMarketDAO;
//import com.app.haetssal_jangteo.repository.FileItemDAO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(rollbackFor = Exception.class)
//@Slf4j
//public class AdminService {
//    private final AdminItemDAO adminDAO;
//    private final AdminMarketDAO adminMarketDAO;
//    private FileItemDAO tagDAO;
//
//
//    //    목록
//    public ItemWithPagingDTO list(int page, Search search){
//        ItemWithPagingDTO ItemWithPagingDTO = new ItemWithPagingDTO();
//        int total = adminDAO.findTotal(search);
//        Criteria criteria = new Criteria(page, total);
//
//        List<ItemDTO> items = adminDAO.findAll(criteria, search);
//
//        criteria.setHasMore(items.size() > criteria.getRowCount());
//        ItemWithPagingDTO.setTotal(total);
//        ItemWithPagingDTO.setCriteria(criteria);
//
//        if(criteria.isHasMore()){
//            items.remove(items.size() - 1);
//        }
//        ItemWithPagingDTO.setItems(items);
//        return  ItemWithPagingDTO;
//    }
//
//    public String getTodayPath(){
//        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//    }
//    //    수정
//
//    public void update(ItemDTO itemDTO, List<MultipartFile> multipartFiles) {
//        String rootPath = "C:/file/";
//        String todayPath = getTodayPath();
//        String path = rootPath + todayPath;
//
//        // 1. 상품 기본 정보 수정
//        adminDAO.setItem(itemDTO.toVO());
//
////        // 2. 태그 수정 로직 (태그 반복문 분리)
////        if (tagDAO != null && itemDTO.getTags() != null) {
////            itemDTO.getTags().forEach(tagDTO -> {
////                tagDTO.setItemId(itemDTO.getId()); // ID 설정 수정
////                tagDAO.save(tagDTO.toVO());
////            });
////        }
//
//        // 3. 파일 업로드 로직
//        if (multipartFiles != null) {
//            FileDTO fileDTO = new FileDTO();
//            FileItemDTO fileItemDTO = new FileItemDTO(); // 변수명 소문자 권장
//
//            multipartFiles.forEach(multipartFile -> {
//                if (multipartFile.getOriginalFilename() == null || multipartFile.getOriginalFilename().isEmpty()) {
//                    return;
//                }
//
//                UUID uuid = UUID.randomUUID();
//                fileDTO.setFilePath(todayPath);
//                fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
//                fileDTO.setFileOriginalName(multipartFile.getOriginalFilename());
//                fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
//                fileDTO.setFileItemType(
//                        multipartFile.getContentType() != null && multipartFile.getContentType().contains("image")
//                                ? FileItemType.THUMBNAIL
//                                : FileItemType.DESC
//                );
//
//                // DAO 호출 (필드에 선언된 fileItemDAO 또는 관련 DAO 사용)
//                // fileDAO.save(fileDTO); // 현재 코드상 fileDAO 정의가 필요합니다.
//
//                fileItemDTO.setItemId(itemDTO.getId());
//                fileItemDTO.setId(fileDTO.getId());
//                // fileItemDAO.save(fileItemDTO.toFileItemVO());
//
//                File directory = new File(rootPath + todayPath);
//                if (!directory.exists()) {
//                    directory.mkdirs();
//                }
//                try {
//                    multipartFile.transferTo(new File(path, fileDTO.getFileName()));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        }
//    }
//
//    //    가게 목록
//    public MarketWithPagingDTO marketList(int page, Search search) {
//        MarketWithPagingDTO marketWithPagingDTO = new MarketWithPagingDTO();
//        int total = adminMarketDAO.findTotal(search);
//        Criteria criteria = new Criteria(page, total);
//
//        List<MarketVO> markets = adminMarketDAO.findAll(criteria, search);
//
//        criteria.setHasMore(markets.size() > criteria.getRowCount());
//        marketWithPagingDTO.setTotal(total);
//        marketWithPagingDTO.setCriteria(criteria);
//
//        if (criteria.isHasMore()) {
//            markets.remove(markets.size() - 1);
//        }
//        marketWithPagingDTO.setMarkets(markets);
//        return marketWithPagingDTO;
//    }
//
//    //    가게 지역 목록 조회
//    public List<String> findMarketRegions() {
//        return adminMarketDAO.findRegions();
//    }
//
//    //    가게 수정
//    public void updateMarket(MarketDTO marketDTO) {
//        adminMarketDAO.setMarket(marketDTO.toVO());
//    }
//}