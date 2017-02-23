package com.cbsh.tsys.realtime.auth.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws.kinesis.KinesisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cbsh.tsys.realtime.auth.config.EndpointConfiguration;

/**
 * Routebuilder class that pulls messages from ${camel.routes.realtime-auth.from}
 * and multicasts the transformed messages to all endpoints defined in 
 * ${camel.routes.realtime-auth.to}.
 * 
 * @author Klhaas
 */
@Component
public class TsysRealtimeAuthRoute extends RouteBuilder{
	
	@Autowired
	private EndpointConfiguration config;

	@Override
	public void configure() throws Exception {
		from(config.getFrom())
            .convertBodyTo(String.class)
            .split()
            .tokenize("\\r\\n|\\n")
			.setHeader(KinesisConstants.PARTITION_KEY, simple("partition"))
	//		.unmarshal("")
	//		.log("hello")
			.multicast()
			.parallelProcessing(config.getParallel())
			.to(config.getTo().toArray(new String[0]));
	}

}
