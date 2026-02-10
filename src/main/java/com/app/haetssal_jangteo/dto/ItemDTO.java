package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.enumeration.State;
import com.app.haetssal_jangteo.domain.ItemVO;
import lombok.*;

import java.util.List;

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
    }
}
