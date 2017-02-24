package com.cbsh.aws.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws.kinesis.KinesisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cbsh.aws.config.KinesisConsumerConfig;

/**
 *
 * @author Jhouston
 */
//@Component
public class KinesisConsumer extends RouteBuilder{

    @Autowired
    private KinesisConsumerConfig config;

    @Override
    public void configure() throws Exception {
        from(config.getFrom())
                .convertBodyTo(String.class)
                .split()
                .tokenize("\\r\\n|\\n")
                .setHeader(KinesisConstants.PARTITION_KEY, simple("partition"))
                //		.unmarshal("")
                //		.log("hello")
                .multicast()
                .parallelProcessing(config.getParallel())
                .to(config.getTo().toArray(new String[0]));
    }

}
