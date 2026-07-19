package com.yesbank.notification.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailRequestDTO {

    private String to;

    private String cc;

    private String bcc;

    private String subject;

    private String body;
}