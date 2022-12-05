
package com.argolis.jlaude.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Map<String, String> helloWorld(Model model) {

        return appService.getEnvDetails();

    }

}
