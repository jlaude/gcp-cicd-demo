package com.argolis.jlaude.app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    private static final Logger logger = LoggerFactory.getLogger(AppService.class);

    @Value("${GCP_PROJECT_ID}")
    private String project_id;

    @Value("${ENVIRONMENT}")
    private String environment;

    public Map<String, String> getEnvDetails() {

        InetAddress ip;
        
        String hostname = "";
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        HashMap<String, String> map = new HashMap<>();

        map.put("project_id", project_id);
        logger.info("project_id: " + project_id);
        map.put("environment_tier", environment);
        logger.info("environment_id: " + environment);
        map.put("hostname", hostname);
        logger.info("hostname: " + hostname);

        logger.info("map: " + map.toString());

        return map;
    }
}
