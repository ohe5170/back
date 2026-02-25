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

    // 가게 id
    private Long storeId;

    // 후기 이미지 목록
    private List<FileDTO> reviewFiles = new ArrayList<>();

    // 상품 정보 (목록에서 보여줄 때)
    private String itemName;
    private String itemPrice;

    // 유저 정보 (리뷰 표시할 때)
    private String userName;
}
