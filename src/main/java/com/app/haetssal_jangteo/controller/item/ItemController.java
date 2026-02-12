package com.app.haetssal_jangteo.controller.item;

import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.service.item.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@RequestMapping("/item/**")
@RequiredArgsConstructor
public class ItemController {
    private final HttpSession session;
    private final ItemService itemService;

    @GetMapping("/write")
    public String goToWriteForm() {
        return "item/item-write";
    }

    @PostMapping("/write")
    public RedirectView write(ItemDTO itemDTO,
                              @RequestParam("itemThumbnail") ArrayList<MultipartFile> itemThumbnails,
                              @RequestParam("itemDescImages") ArrayList<MultipartFile> itemDescImages,
                              @RequestParam("itemSellerImages") ArrayList<MultipartFile> itemSellerImages,
                              @RequestParam("itemRefundImages") ArrayList<MultipartFile> itemRefundImages,
                              RedirectAttributes redirectAttributes) {
        itemService.save(itemDTO, itemThumbnails, itemDescImages, itemSellerImages, itemRefundImages);
        redirectAttributes.addAttribute("id", itemDTO.getId());
        return new RedirectView("/detail");
    }

    @GetMapping("/detail")
    public void goToDetail(Long id) {
        return;
    }


}
