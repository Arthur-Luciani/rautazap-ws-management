package com.ifsc.rautazap.wsmanagement.ports.input;

public interface SendMessageUseCase {

    void sendMessage(String toUser, String message);
}
