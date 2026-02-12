package com.app.haetssal_jangteo.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ItemDetailDTO {
    private Long id;

    // 상품 기본 정보
    private String itemCategoryName;
    private String itemSubCategoryName;
    private String itemName;
    private String itemPrice;
    private String itemStock;
    private String itemDeliveryFee;
    private String itemContent;
    private String itemViewCount;

    // 상품 썸네일 & 옵션들
    private List<FileItemDTO> itemThumbnails = new ArrayList<>();
    private List<ItemOptionDTO> itemOptions = new ArrayList<>();

    // 상품 가게 정보
    private String storeName;
    private String storeScore;
    private String storeTotalPrice;
    private String storeTotalSales;

    // 가게 소유주 마지막 로그인 시간(추후 받아와야 함)
    private String ownerLatestLogin;

    // 같은 카테고리 상품들
    private List<ItemDTO> sameCategoryItems = new ArrayList<>();
}
