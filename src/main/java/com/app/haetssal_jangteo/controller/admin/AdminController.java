//package com.app.haetssal_jangteo.controller.admin;
//
//import com.app.haetssal_jangteo.common.search.Search;
//import com.app.haetssal_jangteo.domain.CategoryVO;
//import com.app.haetssal_jangteo.dto.ItemDTO;
//import com.app.haetssal_jangteo.dto.ItemWithPagingDTO;
//import com.app.haetssal_jangteo.dto.MarketDTO;
//import com.app.haetssal_jangteo.dto.MarketWithPagingDTO;
//import com.app.haetssal_jangteo.mapper.CategoryMapper;
//import com.app.haetssal_jangteo.service.admin.AdminService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/admin")
//@RequiredArgsConstructor
//public class AdminController {
//
//    private final AdminService adminService;
//    private final CategoryMapper categoryMapper;
//
//    @GetMapping
//    public String admin() {
//        return "admin/admin";
//    }
//
//    @GetMapping("/items")
//    public String items(@RequestParam(defaultValue = "1") int page, Search search, Model model) {
//        ItemWithPagingDTO itemWithPagingDTO = adminService.list(page, search);
//        model.addAttribute("itemWithPagingDTO", itemWithPagingDTO);
//        model.addAttribute("search", search);
//        model.addAttribute("categories", categoryMapper.selectCateAll());
//        return "admin/items";
//    }
//
//    @GetMapping("/markets")
//    public String markets(@RequestParam(defaultValue = "1") int page, Search search, Model model) {
//        MarketWithPagingDTO marketWithPagingDTO = adminService.marketList(page, search);
//        model.addAttribute("marketWithPagingDTO", marketWithPagingDTO);
//        model.addAttribute("search", search);
//        model.addAttribute("regions", adminService.findMarketRegions());
//        return "admin/markets";
//    }
//
//    // ===== REST API =====
//
//    @GetMapping("/api/items")
//    @ResponseBody
//    public ResponseEntity<ItemWithPagingDTO> getItems(@RequestParam(defaultValue = "1") int page, Search search) {
//        return ResponseEntity.ok(adminService.list(page, search));
//    }
//
//    @PutMapping("/api/items/{id}")
//    @ResponseBody
//    public ResponseEntity<Void> updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
//        itemDTO.setId(id);
//        adminService.update(itemDTO, null);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/api/markets")
//    @ResponseBody
//    public ResponseEntity<MarketWithPagingDTO> getMarkets(@RequestParam(defaultValue = "1") int page, Search search) {
//        return ResponseEntity.ok(adminService.marketList(page, search));
//    }
//
//    @PutMapping("/api/markets/{id}")
//    @ResponseBody
//    public ResponseEntity<Void> updateMarket(@PathVariable Long id, @RequestBody MarketDTO marketDTO) {
//        marketDTO.setId(id);
//        adminService.updateMarket(marketDTO);
//        return ResponseEntity.ok().build();
//    }
//}
