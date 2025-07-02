package com.ifsc.rautazap.wsmanagement.infra.document;

import com.ifsc.rautazap.wsmanagement.domain.message.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "messages")
public class MessageDocument {

    @Id
    private String id;
    private String fromUserId;
    private String toUserId;
    private String content;
    private LocalDateTime timestamp;
    private boolean delivered;

    public static MessageDocument fromMessage(Message.MessageData message) {
        return MessageDocument.builder()
                .id(message.id())
                .fromUserId(message.fromUserId())
                .toUserId(message.toUserId())
                .content(message.content())
                .timestamp(message.timestamp())
                .delivered(message.isDestinationUserOnline())
                .build();
    }


}
