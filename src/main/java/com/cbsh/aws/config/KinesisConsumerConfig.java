package com.cbsh.aws.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Container class for the tsys.realtime.auth endpoint configs. Necessary
 * since we can have one to many to endpoints.
 *
 * @author Jhouston
 */
@Component
@ConfigurationProperties("camel.routes.kinesis-consumer")
public class KinesisConsumerConfig extends AbstractEnpointConfiguration{

}
