package com.scm.helper;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Message {
    private String content;
    @Builder.Default
    private MessageType type=MessageType.green;
}
