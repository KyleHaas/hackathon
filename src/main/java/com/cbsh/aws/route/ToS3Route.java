package com.cbsh.aws.route;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws.s3.S3Constants;
import org.springframework.stereotype.Component;

@Component
public class ToS3Route extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("direct-vm:sendToS3")
			.convertBodyTo(byte[].class)
			.log("Sending to S3")
//	    	.setHeader(S3Constants.CONTENT_LENGTH, simple("${body.length}"))
	    	.setHeader(S3Constants.KEY, method(this, "getFileName"))
        	.multicast().parallelProcessing(true)
        		.to("aws-s3://census-toy?amazonS3Client=#amazonS3Client");
	}
	
	public String getFileName(Exchange exchange) {
		return "kinesis-test/" + new Date().getTime() + ".txt";
	}

}