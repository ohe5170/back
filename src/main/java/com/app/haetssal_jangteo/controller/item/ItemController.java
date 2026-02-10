package com.app.haetssal_jangteo.controller.item;

import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.service.item.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/item/**")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final HttpSession session;

    @GetMapping("/write")
    public String goToWriteForm() {
        return "item/item-write";
    }

    @PostMapping("/write")
    public RedirectView write(ItemDTO itemDTO, RedirectAttributes redirectAttributes) {
        itemService.save(itemDTO);
        redirectAttributes.addAttribute("id", itemDTO.getId());
        return new RedirectView("/detail");
    }

    @GetMapping("/detail")
    public void goToDetail(Long id) {
        return;
    }


}
