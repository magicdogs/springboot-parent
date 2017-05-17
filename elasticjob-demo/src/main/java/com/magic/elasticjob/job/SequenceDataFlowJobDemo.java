package com.magic.elasticjob.job;

import com.dangdang.ddframe.job.api.JobExecutionSingleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractIndividualSequenceDataFlowElasticJob;

import java.util.List;

/**
 * Created by magicdog on 2017/5/17.
 */
public class SequenceDataFlowJobDemo extends AbstractIndividualSequenceDataFlowElasticJob<Foo> {

    public List<Foo> fetchData(JobExecutionSingleShardingContext context) {
        int offset = Integer.parseInt(context.getOffset());
        List<Foo> result = null;// get data from database by sharding items and by offset
        return result;
    }

    public boolean processData(JobExecutionSingleShardingContext context, Foo data) {
        // process data
        // ...

        // store offset
        updateOffset(context.getShardingItem(), "your offset, maybe id");
        return true;
    }
}
