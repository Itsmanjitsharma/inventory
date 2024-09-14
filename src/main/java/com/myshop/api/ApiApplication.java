package com.myshop.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import lombok.Value;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	/*@Bean
	OtlpHttpSpanExporter otlpHttpSpanExporter() {
		return OtlpHttpSpanExporter.builder()
				.setEndpoint("http://localhost:4318/v1/traces").build();
	}*/

//9886102102 
}
