package com.app.haetssal_jangteo.domain;

import com.app.haetssal_jangteo.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ReviewVO extends Period {
    private Long id;
    private Long reviewItemId;
    private Long reviewUserId;
    private int reviewScoreQuality;
    private int reviewScoreDelivery;
    private int reviewScoreKind;
    private String reviewContent;
    private String reviewState;
}
