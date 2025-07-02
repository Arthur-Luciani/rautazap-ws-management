package com.ifsc.rautazap.wsmanagement.domain.message;

public record MessageDTO(String fromUserId, String toUserId, String content, Boolean isToUserOnline) {}
