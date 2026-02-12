package com.app.haetssal_jangteo.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItemfoundFailException extends RuntimeException {
    public ItemfoundFailException(String message) {
        super(message);
    }
}
