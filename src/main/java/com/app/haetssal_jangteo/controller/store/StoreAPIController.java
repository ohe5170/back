package com.app.haetssal_jangteo.controller.store;

import com.app.haetssal_jangteo.dto.MarketDTO;
import com.app.haetssal_jangteo.service.market.MarketService;
import com.app.haetssal_jangteo.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store/**")
@RequiredArgsConstructor
@Slf4j
public class StoreAPIController {
    private final StoreService storeService;
    private final MarketService marketService;

    @GetMapping("region/{region}")
    public List<MarketDTO> getMarkets(@PathVariable String region) {
        log.info("받아온 지역 정보: {}", region);
        return marketService.findByRegion(region);
    }
}
