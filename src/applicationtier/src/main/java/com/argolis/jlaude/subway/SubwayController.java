
package com.argolis.jlaude.subway;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;

/**
 * Defines a controller to handle HTTP requests.
 */
@RestController
public final class SubwayController {

    private final SubwayService subwayService;

    @Autowired
    public SubwayController(SubwayService subwayService) {
        this.subwayService = subwayService;
    }


    @GetMapping("/subway")
    @Timed (value = "subway.get.api.time", description = "Time taken to return subway.get.api", percentiles = {0.5,0.9,0.95,0.99})
    public ArrayList<String> subwayRestApi(Model model) throws IOException, InterruptedException {

       return subwayService.getSubwayDetails();

    }

}
