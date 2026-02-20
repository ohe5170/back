package com.app.haetssal_jangteo.controller.store;

import com.app.haetssal_jangteo.dto.StoreDTO;
import com.app.haetssal_jangteo.service.item.ItemService;
import com.app.haetssal_jangteo.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/store/**")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final ItemService itemService;

    @GetMapping("write")
    public String goToWriteForm() {
        return "store/store-write";
    }

    @PostMapping("write")
    public RedirectView write(StoreDTO storeDTO,
                              @RequestParam("fileStoreImage") MultipartFile multipartFile,
                              RedirectAttributes redirectAttributes) {
        storeService.save(storeDTO, multipartFile);
        redirectAttributes.addAttribute("id", storeDTO.getId());
        return new RedirectView("/store/detail");
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("stores", storeService.findAll());
        return "store/store-list";
    }

    @GetMapping("/detail")
    public String goToDetail(Long id, Model model) {
        // TODO
        // 가게 상세 관련 DTO 새로 만들고 불러와서 경로 연결하기
        return "/store/store-detail";
    }

}
