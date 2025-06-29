package com.ifsc.rautazap.wsmanagement.ports.output;

public interface SendMessageTopicPort {

    void publishSendMessage(String toUser, String message);
}
