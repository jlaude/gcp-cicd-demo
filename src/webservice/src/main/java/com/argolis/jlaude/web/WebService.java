package com.argolis.jlaude.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class WebService {

    @Value("${PROJECT_ID}")
    private String project_id;

    @Value("${ENVIRONMENT}")
    private String environment;

    public String getHomepage(Model model) {

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
