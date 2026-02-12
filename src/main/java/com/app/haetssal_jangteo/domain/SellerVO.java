package com.app.haetssal_jangteo.domain;

import com.app.haetssal_jangteo.common.enumeration.SellerState;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class SellerVO {
    private Long id;
    private String bankName;
    private String depositor;
    private String accountNumber;
    private SellerState sellerState;
}
