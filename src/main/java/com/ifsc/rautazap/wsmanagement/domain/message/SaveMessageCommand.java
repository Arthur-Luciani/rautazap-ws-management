package com.ifsc.rautazap.wsmanagement.domain.message;

public record SaveMessageCommand(String messageId,
                                 String fromUserId,
                                 String toUserId,
                                 String content,
                                 Boolean messageDelivered) {
}
