package com.cbsh.aws.poc.config;

import java.util.List;

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
public class KinesisConsumerConfig {

    /** Source of data to process. **/
    private String from;
    /**
     * Whether or not the 'to' destinations should be processed in parallel or not.
     * if set to false, 'to' endpoints will be processed in the order provided in
     * the yml file.
     **/
    private Boolean parallel;
    /** List of endpoints to send the 'transformed' data to. **/
    private List<String> to;

    public Boolean getParallel() {
        return parallel;
    }
    public void setParallel(Boolean parallel) {
        this.parallel = parallel;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public List<String> getTo() {
        return to;
    }
    public void setTo(List<String> to) {
        this.to = to;
    }


}
