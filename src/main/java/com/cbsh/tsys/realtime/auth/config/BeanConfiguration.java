package com.cbsh.tsys.realtime.auth.config;

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

@Configuration
public class BeanConfiguration {
	
	@Value("${aws.kinesis.url:}")
	private String kinesisUrl;
	@Value("${aws.kinesis.port:}")
	private Integer kinesisPort;
	@Value("${aws.kinesis.region:}")
	private String kinesisRegion;
	@Value("${aws.kinesis.accessKey:}")
	private String kinesisAccessKey;
	@Value("${aws.kinesis.secretKey:}")
	private String kinesisSecretKey;
	
	@Bean
	@Profile(value="kinesis")
	public AmazonKinesisClient kinesisClient() {
		AWSCredentials creds = new BasicAWSCredentials(kinesisAccessKey, kinesisSecretKey);
		AWSCredentialsProvider credProvider = new StaticCredentialsProvider(creds);
		ClientConfiguration clientConfiguration = new ClientConfiguration();
		clientConfiguration.setProxyHost(kinesisUrl);
		clientConfiguration.setProxyPort(kinesisPort);
		Region region = Region.getRegion(Regions.fromName(kinesisRegion));
		return region.createClient(AmazonKinesisClient.class, credProvider, clientConfiguration);
	}

}
