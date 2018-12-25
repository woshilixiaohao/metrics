package com.li.xiaohao.metrics.jetty;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lixiaohao
 * @since 2018/12/25 23:19
 */
@ConfigurationProperties(prefix = "jetty.threadPool")
public class JettyProperties {
    private int minThreads = 10;
    private int maxThreads = 200;
    private int idleTimeout = 60000;

    public int getMinThreads() {
        return minThreads;
    }

    public void setMinThreads(int minThreads) {
        this.minThreads = minThreads;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }
}
