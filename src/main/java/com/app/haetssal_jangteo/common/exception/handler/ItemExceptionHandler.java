package com.app.haetssal_jangteo.common.exception.handler;

import com.app.haetssal_jangteo.common.exception.ItemWriteFailException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice(basePackages = "com.app.haetssal_jangteo.controller.item")
public class ItemExceptionHandler {
    @ExceptionHandler(ItemWriteFailException.class)
    protected RedirectView writeFail(ItemWriteFailException writeFailException, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("write", "fail");
        return new RedirectView("/item/list");
    }
}
