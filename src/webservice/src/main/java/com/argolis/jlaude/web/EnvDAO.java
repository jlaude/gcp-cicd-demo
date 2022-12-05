package com.argolis.jlaude.web;

public class EnvDAO {
    private String project_id;
    private String environment_tier;
    private String hostname;
    public EnvDAO() {
    }
    public EnvDAO(String project_id, String environment_tier, String hostname) {
        this.project_id = project_id;
        this.environment_tier = environment_tier;
        this.hostname = hostname;
    }
    @Override
    public String toString() {
        return "EnvDAO [project_id=" + project_id + ", environment=" + environment_tier + ", hostname=" + hostname + "]";
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
