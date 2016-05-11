package com.example.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class CommonAppProperties {

    private final Logging logging = new Logging();

    public Logging getLogging() {
        return logging;
    }

    public static class Logging {

        private final Logstash logstash = new Logstash();

        public Logstash getLogstash() {
            return logstash;
        }

        public static class Logstash {

            private String host = "localhost";

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }
        }
    }
}
