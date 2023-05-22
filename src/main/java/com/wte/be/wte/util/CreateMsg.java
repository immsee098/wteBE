package com.wte.be.wte.util;

import java.util.*;

public class CreateMsg {
    public static Message makeMsg(StatusEnum status, String msg, Object data) {
        Message message = new Message();
        message.setStatus(status);
        message.setMessage(msg);
        message.setData(data);

        return message;
    }
}
