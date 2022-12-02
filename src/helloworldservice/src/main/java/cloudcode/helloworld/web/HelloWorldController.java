
package cloudcode.helloworld.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Defines a controller to handle HTTP requests.
 */
@Controller
public final class HelloWorldController {

    /**
     * Create an endpoint for the landing page
     * @return the index view template with a simple message
     */
    @GetMapping("/")
    public String helloWorld(Model model) {
        String message = "It's Running!";
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
