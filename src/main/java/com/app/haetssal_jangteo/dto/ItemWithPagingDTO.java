package com.app.haetssal_jangteo.dto;

import com.app.haetssal_jangteo.common.pagination.Criteria;
import com.app.haetssal_jangteo.repository.AdminDAO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemWithPagingDTO {
    private List<ItemDTO> items;
    private Criteria criteria;
}
