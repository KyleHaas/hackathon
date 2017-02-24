package com.cbsh.aws.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jhouston
 */
@Component
public class KinesisConsumer extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("aws-kinesis://census-stream?amazonKinesisClient=#kinesisClient")
        	.convertBodyTo(String.class)
            .convertBodyTo(byte[].class)
            .log("Consumed Message")
            .aggregate(constant(true), new ByteArrayAggregationStrategy())
            	.completionInterval(30000)
            	.to("direct-vm:sendToS3");
    }

}
