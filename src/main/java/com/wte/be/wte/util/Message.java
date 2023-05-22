package com.wte.be.wte.util;

import lombok.*;

@Data
@AllArgsConstructor
public class Message {
    private StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
