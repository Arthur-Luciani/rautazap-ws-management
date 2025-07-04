package com.ifsc.rautazap.wsmanagement.application.ports.input.command;

public record SendMessageCommand(String fromUserId,
                                 String toUserId,
                                 String content) {
}
