
package cloudcode.helloworld.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Defines a controller to handle HTTP requests.
 */
@Controller
public final class HelloWorldController {

    @Value("${PROJECT_ID}")
    private String project_id;

    @Value("${ENVIRONMENT}")
    private String environment;


    /**
     * Create an endpoint for the landing page
     * @return the index view template with a simple message
     */
    @GetMapping("/")
    public String helloWorld(Model model) {


        //String message = "It's Running!";
        String message = "Project ID: "+ project_id + "; Environment: " + environment;
        model.addAttribute("message", message);

        InetAddress ip;
        
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            model.addAttribute("hostname", hostname);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
