package com.cbsh.aws.route;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws.kinesis.KinesisConstants;
import org.apache.camel.component.aws.s3.S3Constants;
import org.springframework.stereotype.Component;

@Component
public class FileToS3Route extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:///Users/Cassie/Downloads?fileName=census-income.test")
//        .convertBodyTo(String.class)
//        .split().tokenize("\\r\\n|\\n")
			.convertBodyTo(byte[].class)
	    	.setHeader(S3Constants.CONTENT_LENGTH, simple("${in.header.CamelFileLength}"))
	    	.setHeader(S3Constants.KEY,simple("kyle2-upload.txt"))
        	.setHeader("s3Destination").method(this, "getS3Destination")
        	.multicast().parallelProcessing(true)
//        		.to("log:${header.s3Destination}");
        		.to("aws-s3://census-toy?amazonS3Client=#amazonS3Client&prefix=kinesis-test");
		
	}
	
	public String getS3Destination(Exchange exchange) {
		return "kinesis-test";
	}

}
