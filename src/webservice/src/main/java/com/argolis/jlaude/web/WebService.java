package com.argolis.jlaude.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;

@Service
public class WebService {

    private static final Logger logger = LoggerFactory.getLogger(WebService.class);


    public String getHomepage(Model model) {

        String appUri = "http://java-app-tier-svc.default.svc.cluster.local:80/env";
        RestTemplate restTemplate = new RestTemplate();

        //WebClient client = WebClient.create(appUri);
        //UriSpec<EnvDTO> uriSpec = client.get();

        EnvDTO env = restTemplate.getForObject(appUri, EnvDTO.class);
        logger.info(env.toString());

        String message = "Project ID: "+ env.getProject_id() + "; Environment: " + env.getEnvironment_tier();
        model.addAttribute("message", message);
        model.addAttribute("hostname", env.getHostname());

        InetAddress ip;
        
        String webTierHostname;
        try {
            ip = InetAddress.getLocalHost();
            webTierHostname = ip.getHostName();
            model.addAttribute("webTierHostname", webTierHostname);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "index";

    }


    /* 



        return "index";
    } */
}
