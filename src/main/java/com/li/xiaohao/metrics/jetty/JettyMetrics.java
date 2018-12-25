package com.li.xiaohao.metrics.jetty;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lixiaohao
 * @since 2018/12/25 23:35
 */
public class JettyMetrics implements PublicMetrics {
    @Autowired
    private QueuedThreadPool threadPool;

    @Override
    public Collection<Metric<?>> metrics() {
        List<Metric<?>> metrics = new ArrayList<>();
        metrics.add(new Metric<>("jetty.threadpool.busy", threadPool.getBusyThreads()));
        metrics.add(new Metric<>("jetty.threadpool.idle", threadPool.getIdleThreads()));
        metrics.add(new Metric<>("jetty.threadpool.max", threadPool.getMaxThreads()));
        metrics.add(new Metric<>("jetty.threadpool.min", threadPool.getMinThreads()));
        return metrics;
    }
}
