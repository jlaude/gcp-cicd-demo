
package com.argolis.jlaude.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import io.micrometer.core.annotation.Timed;


/**
 * Defines a controller to handle HTTP requests.
 */
@Controller
public final class WebController {

    private final WebService webService;

    
    @Autowired
    public WebController(WebService webService) {
        this.webService = webService;
    }


    /**
     * Create an endpoint for the landing page
     * @return the index view template with a simple message
     */
    @GetMapping("/")
    @Timed (value = "homepage.get.time", description = "Time taken to return homepage.get", percentiles = {0.5,0.9,0.95,0.99})
    public String homePage(Model model) {

        return "index";

    }

    @GetMapping("/ltrain")
    @Timed (value = "ltrain.get.time", description = "Time taken to return ltrain.get", percentiles = {0.5,0.9,0.95,0.99})
    public String lTrainPage(Model model) {

        return webService.getLTrainPage(model);

    }

}
