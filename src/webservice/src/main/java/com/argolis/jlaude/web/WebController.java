
package com.argolis.jlaude.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    @Timed (value = "homepage.get.time", description = "Time taken to return homepage.get")
    public String homePage(Model model) {

        return webService.getHomepage(model);

    }

}
