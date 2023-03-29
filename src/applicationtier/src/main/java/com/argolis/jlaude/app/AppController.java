package com.argolis.jlaude.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;

/**
 * Defines a controller to handle HTTP requests.
 */
@RestController
public final class AppController {

    private final AppService appService;
    
    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    /**
     * Create an endpoint for the landing page
     * @return the index view template with a simple message
     */
    @GetMapping("/env")
    @Timed (value = "env.get.api.time", description = "Time taken to return env.get.api", percentiles = {0.5,0.9,0.95,0.99})

    public Map<String, String> envRestApi(Model model) {

        return appService.getEnvDetails();

    }

}
