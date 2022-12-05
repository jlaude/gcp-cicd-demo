package com.argolis.jlaude.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @Value("${PROJECT_ID}")
    private String project_id;

    @Value("${ENVIRONMENT}")
    private String environment;

    public Map<String, String> getEnvDetails() {

        HashMap<String, String> map = new HashMap<>();

        map.put("project_id", project_id);
        map.put("environment_tier", environment);

        return map;
    }
}
