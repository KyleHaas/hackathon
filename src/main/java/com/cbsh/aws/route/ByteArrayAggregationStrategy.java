package com.cbsh.aws.route;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.component.aws.s3.S3Constants;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.amazonaws.services.s3.AmazonS3Client;

public class ByteArrayAggregationStrategy implements AggregationStrategy {

    public ByteArrayAggregationStrategy() {
            super();
    }

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
            Message newIn = newExchange.getIn();
            byte[] newBody = (byte[]) newIn.getBody();
            byte[] bytes = null;
            if (oldExchange == null) {
            	bytes = ByteBuffer.allocate(newBody.length+"\n".getBytes().length).put(newBody).put("\n".getBytes()).array();
                newIn.setBody(bytes);
                return newExchange;
            } else {
                Message in = oldExchange.getIn();
                bytes = in.getBody(byte[].class);
                bytes = ByteBuffer.allocate(bytes.length+newBody.length+"\n".getBytes().length).put(bytes).put(newBody).put("\n".getBytes()).array();
                oldExchange.getIn().setHeader(S3Constants.CONTENT_LENGTH, bytes.length);
                oldExchange.getIn().setBody(bytes);
                return oldExchange;
            }
    }
    
    

}
