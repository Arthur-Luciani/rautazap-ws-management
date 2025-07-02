package com.ifsc.rautazap.wsmanagement.domain.message;

public record SendMessageCommand(String fromUserId,
                                 String toUserId,
                                 String content) {
}
