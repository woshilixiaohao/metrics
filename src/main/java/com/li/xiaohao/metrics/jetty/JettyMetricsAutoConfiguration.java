package com.li.xiaohao.metrics.jetty;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lixiaohao
 * @since 2018/12/25 23:17
 */
@Configuration
@AutoConfigureBefore(EndpointAutoConfiguration.class)
@EnableConfigurationProperties(JettyProperties.class)
@ConditionalOnClass(JettyEmbeddedServletContainerFactory.class)
public class JettyMetricsAutoConfiguration {

    @Bean
    public QueuedThreadPool jettyQueuedThreadPool(JettyProperties jettyProperties) {
        return new QueuedThreadPool(jettyProperties.getMaxThreads(),
                jettyProperties.getMinThreads(), jettyProperties.getIdleTimeout());
    }

    @Bean
    public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory(QueuedThreadPool queuedThreadPool) {
        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
        factory.setThreadPool(queuedThreadPool);
        return factory;
    }

    @Configuration
    static class JettyMetricsConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public JettyMetrics jettyMetrics() {
            return new JettyMetrics();
        }
    }
}
