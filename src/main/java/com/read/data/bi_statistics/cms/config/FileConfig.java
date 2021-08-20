package com.read.data.bi_statistics.cms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;


@Component
@ConfigurationProperties(prefix = "relations")
@PropertySource(value = {"classpath:export_excel_relation.yml"},encoding="UTF-8")
public class FileConfig {


	private Map<String,String> contents = new HashMap<>();


	public Map<String, String> getContents() {
		return contents;
	}
}
