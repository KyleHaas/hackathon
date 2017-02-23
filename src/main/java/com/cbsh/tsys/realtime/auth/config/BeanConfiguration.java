package com.cbsh.tsys.realtime.auth.config;

import org.apache.camel.component.aws.kinesis.KinesisProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.amazonaws.services.s3.AmazonS3;

@Configuration
public class BeanConfiguration {
	
	@Value("${aws.region:}")
	private String kinesisRegion;
	@Value("${aws.accessKey:}")
	private String kinesisAccessKey;
	@Value("${aws.secretKey:}")
	private String kinesisSecretKey;
	
	@Bean
	public AmazonKinesisClient kinesisClient() {
		AWSCredentials creds = new BasicAWSCredentials(kinesisAccessKey, kinesisSecretKey);
		AWSCredentialsProvider credProvider = new StaticCredentialsProvider(creds);
		ClientConfiguration clientConfiguration = new ClientConfiguration();
		Region region = Region.getRegion(Regions.fromName(kinesisRegion));
		return region.createClient(AmazonKinesisClient.class, credProvider, clientConfiguration);
	}
	
	public AmazonS3 amazonS3Client() {
//		AmazonS3 amazons3 = new AmazonS3();
		return null;
		
	}
}
