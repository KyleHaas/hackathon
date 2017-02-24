package com.cbsh.aws.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws.s3.S3Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cbsh.aws.config.KinesisConsumerConfig;

/**
 *
 * @author Jhouston
 */
@Component
public class KinesisConsumer extends RouteBuilder{

    @Autowired
    private KinesisConsumerConfig config;

    S3Producer p;
    @Override
    public void configure() throws Exception {
        from("aws-kinesis://census-stream?amazonKinesisClient=#kinesisClient")
        	.convertBodyTo(String.class)
            .convertBodyTo(byte[].class)
            .log("consumed")
            .aggregate(constant(true), new ByteArrayAggregationStrategy())
            	.completionInterval(30000)
            	.to("direct-vm:sendToS3");
    }

}
