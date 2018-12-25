package com.li.xiaohao.metrics.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.lang.management.ManagementFactory;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lixiaohao
 * @since 2018/12/25 23:38
 */
@EnableConfigurationProperties({JettyEmbeddedServletContainerFactory.class})
public class CustomizeHealthEndpoint extends AbstractEndpoint<Map<String, Object>> {

    @Autowired
    private InfoEndpoint infoEndpoint;
    @Autowired
    private HealthEndpoint healthEndpoint;
    @Autowired
    private JettyEmbeddedServletContainerFactory factory;

    public CustomizeHealthEndpoint() {
        super("health");
    }

    @Override
    public Map<String, Object> invoke() {
        Map<String, Object> info = infoEndpoint.invoke();
        Health health = healthEndpoint.invoke();

        Map<String, Object> compositeHealth = new LinkedHashMap<>();
        compositeHealth.put("status", health.getStatus().getCode());
        compositeHealth.put("timestamp", System.currentTimeMillis());
        compositeHealth.put("port", factory.getPort());
        compositeHealth.put("pid", ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);

        compositeHealth.putAll(info);
        compositeHealth.putAll(health.getDetails());

        return compositeHealth;
    }
}
