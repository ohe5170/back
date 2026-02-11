package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.StoreState;
import com.app.haetssal_jangteo.domain.StoreVO;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class StoreDTO {
    private Long id;
    private Long storeMarketId;
    private Long storeOwnerId;
    private String storeName;
    private String storeIntro;
    private String storeAddress;
    private String storeScore;
    private StoreState storeState;
    private boolean storeIsConfirmed;
    private String createdDatetime;
    private String updatedDatetime;

    public StoreVO toVO() {
        return StoreVO.builder()
                .id(id)
                .storeMarketId(storeMarketId)
                .storeOwnerId(storeOwnerId)
                .storeName(storeName)
                .storeIntro(storeIntro)
                .storeAddress(storeAddress)
                .storeScore(storeScore)
                .storeState(storeState)
                .storeIsConfirmed(storeIsConfirmed)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
