package com.li.xiaohao.metrics.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lixiaohao
 * @since 2018/12/26 0:05
 */
@Configuration
public class CustomizeAutoConfiguration {

    @Bean
    public CustomizeHealthEndpoint customizeHealthEndpoint() {
        return new CustomizeHealthEndpoint();
    }
}
