package com.eazybytes.cards.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "users")
@Getter
@Setter
public class CardsContactInf0Dto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;

}
