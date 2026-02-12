package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.SellerState;
import com.app.haetssal_jangteo.domain.SellerVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class SellerDTO {
    private Long id;
    private String bankName;
    private String depositor;
    private String accountNumber;
    private SellerState sellerState;

    public SellerVO toSellerVO() {
        return SellerVO.builder()
                .id(id)
                .bankName(bankName)
                .depositor(depositor)
                .accountNumber(accountNumber)
                .sellerState(sellerState)
                .build();
    }
}
