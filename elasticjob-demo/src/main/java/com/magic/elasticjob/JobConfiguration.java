package com.magic.elasticjob;

import com.dangdang.ddframe.job.api.JobScheduler;
import com.dangdang.ddframe.job.api.config.JobConfigurationFactory;
import com.dangdang.ddframe.job.api.config.impl.DataFlowJobConfiguration;
import com.dangdang.ddframe.job.api.config.impl.ScriptJobConfiguration;
import com.dangdang.ddframe.job.api.config.impl.SimpleJobConfiguration;
import com.dangdang.ddframe.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperRegistryCenter;
import com.magic.elasticjob.job.SequenceDataFlowJobDemo;
import com.magic.elasticjob.job.SimpleJobDemo;
import com.magic.elasticjob.job.ThroughputDataFlowJobDemo;

/**
 * Created by magicdog on 2017/5/17.
 */
public class JobConfiguration {

    // 定义Zookeeper注册中心配置对象
    private ZookeeperConfiguration zkConfig = new ZookeeperConfiguration("localhost:2181", "elastic-job-example", 1000, 3000, 3);

    // 定义Zookeeper注册中心
    private CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zkConfig);

    // 定义简单作业配置对象
    private final SimpleJobConfiguration simpleJobConfig = JobConfigurationFactory.createSimpleJobConfigurationBuilder("simpleElasticDemoJob",
            SimpleJobDemo.class, 10, "0/30 * * * * ?").build();

    // 定义高吞吐流式处理的数据流作业配置对象
    private final DataFlowJobConfiguration throughputJobConfig = JobConfigurationFactory.createDataFlowJobConfigurationBuilder("throughputDataFlowElasticDemoJob",
            ThroughputDataFlowJobDemo.class, 10, "0/5 * * * * ?").streamingProcess(true).build();

    // 定义顺序的数据流作业配置对象
    private final DataFlowJobConfiguration sequenceJobConfig = JobConfigurationFactory.createDataFlowJobConfigurationBuilder("sequenceDataFlowElasticDemoJob",
            SequenceDataFlowJobDemo.class, 10, "0/5 * * * * ?").build();

    // 定义脚本作业配置对象
    private final ScriptJobConfiguration scriptJobConfig = JobConfigurationFactory.createScriptJobConfigurationBuilder("scriptElasticDemoJob",
            10, "0/5 * * * * ?", "test.sh").build();

    public void init() {
        // 连接注册中心
        regCenter.init();
        // 启动简单作业
        new JobScheduler(regCenter, simpleJobConfig).init();
        // 启动高吞吐流式处理的数据流作业
        new JobScheduler(regCenter, throughputJobConfig).init();
        // 启动顺序的数据流作业
        new JobScheduler(regCenter, sequenceJobConfig).init();
        // 启动脚本作业
        new JobScheduler(regCenter, scriptJobConfig).init();
    }
}
