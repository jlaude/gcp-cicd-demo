package com.argolis.jlaude.web;

public class EnvDTO {
    private String project_id;
    private String environment_tier;
    private String hostname;
    public EnvDTO() {
    }
    public EnvDTO(String project_id, String environment_tier, String hostname) {
        this.project_id = project_id;
        this.environment_tier = environment_tier;
        this.hostname = hostname;
    }
    @Override
    public String toString() {
        return "EnvDTO [project_id=" + project_id + ", environment=" + environment_tier + ", hostname=" + hostname + "]";
    }
    public String getProject_id() {
        return project_id;
    }
    public String getEnvironment_tier() {
        return environment_tier;
    }
    public String getHostname() {
        return hostname;
    }
    
    
}
