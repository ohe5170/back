package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.FileItemType;
import com.app.haetssal_jangteo.common.enumeration.Filetype;
import com.app.haetssal_jangteo.common.enumeration.State;
import com.app.haetssal_jangteo.domain.FileItemVO;
import com.app.haetssal_jangteo.domain.ItemOptionVO;
import com.app.haetssal_jangteo.domain.ItemVO;
import lombok.*;
import org.apache.tomcat.util.http.fileupload.FileItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private Long itemStoreId;
    private Long itemCategoryId;
    private String itemName;
    private String itemType;
    private int itemStock;
    private int itemPrice;
    private int itemDeliveryFee;
    private String itemContent;
    private State itemState;
    private int itemViewCount;
    private String createdDatetime;
    private String updatedDatetime;

//    상품 옵션들
    private List<ItemOptionDTO> itemOptions = new ArrayList<>();

//    상품 이미지들
    private List<FileItemDTO> itemImages = new ArrayList<>();

    public ItemVO toVO() {
        return ItemVO.builder()
                .id(id)
                .itemStoreId(itemStoreId)
                .itemCategoryId(itemCategoryId)
                .itemName(itemName)
                .itemType(itemType)
                .itemStock(itemStock)
                .itemPrice(itemPrice)
                .itemDeliveryFee(itemDeliveryFee)
                .itemContent(itemContent)
                .itemState(itemState)
                .itemViewCount(itemViewCount)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    };

//  옵션 추가
    public void addOption(ItemOptionDTO option) {
        this.itemOptions.add(option);
    }

//   상품 이미지 추가
    public void addImage(FileItemDTO image) {
        this.itemImages.add(image);
    }

//    상품 옵션 일괄 추가
    public void setImages(List<ItemOptionDTO> options) {
        this.itemOptions = options != null ? options : new ArrayList<>();
    }

//    상품 이미지 일괄 추가
    public void setOptions(List<FileItemDTO> images) {
        this.itemImages = images != null ? images : new ArrayList<>();
    }

//    상품 대표 이미지(1번째 사진을 적용)
    public String getMainThumbnail() {
        return itemImages.stream()
                .filter(img -> img.getFileItemType() == FileItemType.THUMBNAIL)
                .findFirst()
                .map(FileItemDTO::getFileSavedPath)
                .orElseGet(() -> itemImages.isEmpty() ? null : itemImages.get(0).getFileSavedPath());
    }

//    상품 설명 이미지 가져오기
    public List<FileItemDTO> getItemDetailImages() {
        return itemImages.stream()
                .filter(img -> img.getFileItemType() == FileItemType.DESC)
                .collect(Collectors.toList());
    }

//    상품 판매자 이미지 가져오기
    public List<FileItemDTO> getItemSellerImages() {
        return itemImages.stream()
                .filter(img -> img.getFileItemType() == FileItemType.SELLER_INFO)
                .collect(Collectors.toList());
    }

//    상품 교환/환불 이미지 가져오기
    public List<FileItemDTO> getRefundImages() {
        return itemImages.stream()
                .filter(img -> img.getFileItemType() == FileItemType.REFUND)
                .collect(Collectors.toList());
    }





}
