package com.magic.elasticjob.job;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractIndividualThroughputDataFlowElasticJob;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by magicdog on 2017/5/17.
 */
public class ThroughputDataFlowJobDemo extends AbstractIndividualThroughputDataFlowElasticJob<Foo> {


    public List<Foo> fetchData(JobExecutionMultipleShardingContext context) {
        Map<Integer, String> offset = context.getOffsets();
        List<Foo> result = Arrays.asList(new Foo()); // get data from database by sharding items and by offset
        return result;
    }

    public boolean processData(JobExecutionMultipleShardingContext context, Foo data) {
        // process data
        // ...

        // store offset
        for (int each : context.getShardingItems()) {
            updateOffset(each, "your offset, maybe id");
        }
        return true;
    }
}
