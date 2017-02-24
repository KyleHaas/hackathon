package com.cbsh.aws.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Container class for the s3-to-kinesis endpoint configs. Necessary 
 * since we can have one to many to endpoints.
 * 
 * @author Klhaas
 */
@Component
@ConfigurationProperties("camel.routes.s3-to-kinesis")
public class S3ToKinesisEndpointConfiguration extends AbstractEnpointConfiguration{
	
	
}
