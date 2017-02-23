package com.cbsh.aws.poc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.amazonaws.services.s3.AmazonS3Client;

@Configuration
public class BeanConfiguration {
	
	@Value("${aws.region:}")
	private String kinesisRegion;
	@Value("${aws.accessKey:}")
	private String kinesisAccessKey;
	@Value("${aws.secretKey:}")
	private String kinesisSecretKey;
	
	
	@Bean
	public AWSCredentialsProvider credProvider() {
		AWSCredentials creds = new BasicAWSCredentials(kinesisAccessKey, kinesisSecretKey);
		AWSCredentialsProvider credProvider = new StaticCredentialsProvider(creds);
		return credProvider;
	}
	@Bean
	public Region region(){
		return Region.getRegion(Regions.fromName(kinesisRegion));
	}
	
	@Bean
	public AmazonKinesisClient kinesisClient() {
		return region().createClient(AmazonKinesisClient.class, credProvider(), new ClientConfiguration());
	}
	
	@Bean
	public AmazonS3Client amazonS3Client() {
		return region().createClient(AmazonS3Client.class, credProvider(), new ClientConfiguration());
	}
}
