package net.yassine.customerservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="customer.params")
public record CustomConfigParams(int x,int y) {
}
