package com.ifsc.rautazap.wsmanagement.ports.input;

public interface SendMessageUseCase {

    void sendMessage(String fromUser, String toUser, String content);
}
