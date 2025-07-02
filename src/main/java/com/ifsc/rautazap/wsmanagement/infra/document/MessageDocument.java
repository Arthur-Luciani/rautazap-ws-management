package com.ifsc.rautazap.wsmanagement.infra.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "messages")
public class MessageDocument {

    @Id
    private String id;
    private String fromUserId;
    private String toUserId;
    private String content;
    private LocalDateTime timestamp;
    private boolean delivered;

    public MessageDocument(String fromUserId, String toUserId, String content, boolean isToUserOnline) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.delivered = isToUserOnline;
    }

}
