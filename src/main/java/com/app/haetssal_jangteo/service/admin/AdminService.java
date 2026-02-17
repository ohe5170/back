package com.app.haetssal_jangteo.service.admin;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.dto.FileDTO;
import com.app.haetssal_jangteo.dto.FileItemDTO;
import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.dto.ItemWithPagingDTO;
import com.app.haetssal_jangteo.repository.AdminDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.DateUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AdminService {
    private final AdminDAO adminDAO;


    //    목록
    public ItemWithPagingDTO list(int page, Search search){
        ItemWithPagingDTO ItemWithPagingDTO = new ItemWithPagingDTO();
        Criteria criteria = new Criteria(page, adminDAO.findTotal(search));

        List<ItemDTO> items = adminDAO.findAll(criteria, search);

        criteria.setHasMore(items.size() > criteria.getRowCount());
        ItemWithPagingDTO.setCriteria(criteria);

        if(criteria.isHasMore()){
            items.remove(items.size() - 1);
        }
        return  ItemWithPagingDTO;
    }

    public String getTodayPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
    //    수정
    public void update(ItemDTO itemDTO, List<MultipartFile> multipartFiles){
        String rootPath = "C:/file/";
        String todayPath = getTodayPath();
        String path = rootPath + todayPath;

        adminDAO.setPost(itemDTO.toVO());

        FileDTO fileDTO = new FileDTO();
        FileItemDTO postFileDTO = new FileItemDTO();

        postDTO.getTags().forEach(tagDTO -> {
            tagDTO.setPostId(postDTO.getId());
            tagDAO.save(tagDTO.toVO());
        });

        postFileDTO.setPostId(postDTO.getId());
        multipartFiles.forEach((multipartFile -> {
            if(multipartFile.getOriginalFilename().isEmpty()){
                return;
            }
            UUID uuid = UUID.randomUUID();
            fileDTO.setFilePath(todayPath);
            fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
            fileDTO.setFileOriginalName(multipartFile.getOriginalFilename());
            fileDTO.setFileName(uuid.toString() + "_" + multipartFile.getOriginalFilename());
            fileDTO.setFileContentType(multipartFile.getContentType().contains("image") ? FileContentType.IMAGE : FileContentType.OTHER);
            fileDAO.save(fileDTO);

            postFileDTO.setId(fileDTO.getId());
            postFileDAO.save(postFileDTO.toPostFileVO());

            File directory = new File(rootPath + "/" + fileDTO.getFilePath());
            if(!directory.exists()){
                directory.mkdirs();
            }
            try {
                multipartFile.transferTo(new File(path, fileDTO.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
}
