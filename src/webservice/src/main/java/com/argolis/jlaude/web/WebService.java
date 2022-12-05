package com.argolis.jlaude.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;

@Service
public class WebService {

    private static final Logger logger = LoggerFactory.getLogger(WebService.class);


    public String getHomepage(Model model) {

        String appUri = "http://java-app-tier-svc.default.svc.cluster.local:80/env";
        RestTemplate restTemplate = new RestTemplate();

        EnvDAO env = restTemplate.getForObject(appUri, EnvDAO.class);
        logger.info(env.toString());

        String message = "Project ID: "+ env.getProject_id() + "; Environment: " + env.getEnvironment_tier();
        model.addAttribute("message", message);
        model.addAttribute("hostname", env.getHostname());

        return "index";

    }


    /* 
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
    } */
}
