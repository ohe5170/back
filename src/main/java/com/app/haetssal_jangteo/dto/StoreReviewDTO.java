package com.app.haetssal_jangteo.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class StoreReviewDTO {
    private Long id;
    private Long reviewItemId;
    private Long reviewUserId;
    private int reviewScoreQuality;
    private int reviewScoreDelivery;
    private int reviewScoreKind;
    private String reviewContent;
    private String reviewState;
    private String createdDatetime;
    private String updatedDatetime;

    // 후기 이미지 목록
    private List<FileDTO> reviewFiles = new ArrayList<>();

    // 상품 정보 (목록에서 보여줄 때)
    private Long itemStoreId; // 상품의 가게 id
    private String itemName;
    private String itemPrice;

    // 상품 프로필 이미지
    private String itemFileName;
    private String itemFileSavedPath;

    // 유저 정보 (리뷰 표시할 때)
    private String userName;

    // 유저 프로필 이미지
    private String userFileName;
    private String userFileSavedPath;
}
