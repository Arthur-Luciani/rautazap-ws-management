package com.ifsc.rautazap.wsmanagement.application.ports.input;

import com.ifsc.rautazap.wsmanagement.application.ports.input.command.SendMessageCommand;

public interface SendMessageUseCase {

    void sendMessage(SendMessageCommand command);
}
