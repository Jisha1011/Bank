package com.bank.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "cards")
public record CardsContactInfo(String message, Map<String,String> contactDetails, List<String> onCallSupport) {
}
