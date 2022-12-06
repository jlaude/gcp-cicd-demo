package com.argolis.jlaude.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;

@Service
public class WebService {

    private static final Logger logger = LoggerFactory.getLogger(WebService.class);


    public String getHomepage(Model model) {

        String appUri = "http://java-app-tier-svc.default.svc.cluster.local:80/env";

        WebClient client = WebClient.builder().baseUrl(appUri).build();
        Mono<EnvDTO> env = client.get().retrieve().bodyToMono(EnvDTO.class);
        EnvDTO envResult = env.block();

        logger.info(envResult.toString());
        
        String message = "Project ID: "+ envResult.getProject_id() + "; Environment: " + envResult.getEnvironment_tier();
        model.addAttribute("message", message);
        model.addAttribute("hostname", envResult.getHostname());

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

}
