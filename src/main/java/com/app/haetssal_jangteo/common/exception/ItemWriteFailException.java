package com.app.haetssal_jangteo.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItemWriteFailException extends RuntimeException {
    public ItemWriteFailException(String message) {
        super(message);
    }
}
