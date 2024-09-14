/*package com.myshop.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ConstSampler;

@Configuration
public class JaegerConfig {    
@Bean
public JaegerTracer jaegerTracer(){
    return new io.jaegertracing.Configuration("myshop")
            .withSampler(new io.jaegertracing.Configuration.SamplerConfiguration()
                    .withType(ConstSampler.TYPE)
                    .withParam(1))
            .withReporter(new io.jaegertracing.Configuration.ReporterConfiguration()
                    .withLogSpans(true)
                    .withSender(new io.jaegertracing.Configuration.SenderConfiguration()
                            .withEndpoint("http://localhost:16686/api/traces").withAgentHost("localhost").withAgentPort(6831)))  // Use HTTP endpoint
            .getTracer();
}
}*/
