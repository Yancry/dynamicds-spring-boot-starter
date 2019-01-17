package com.yancary.dynamicds;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "dynamicds")
@Data
public class DynamicDatasourceConfigProperties {
	private String orgCodeHeader;
	private Map<String, String> general;
	private Map<String, Map<String, String>> tenants;
}
