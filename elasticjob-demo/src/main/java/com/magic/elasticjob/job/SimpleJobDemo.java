package com.magic.elasticjob.job;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;

/**
 * Created by magicdog on 2017/5/17.
 */
public class SimpleJobDemo extends AbstractSimpleElasticJob {

    public void process(JobExecutionMultipleShardingContext shardingContext) {
        System.out.println("SimpleJobDemo");
    }

}
