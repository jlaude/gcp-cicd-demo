package com.argolis.jlaude.web;

public class EnvDAO {
    private String project_id;
    private String environment_tier;
    public EnvDAO() {
    }
    public EnvDAO(String project_id, String environment_tier) {
        this.project_id = project_id;
        this.environment_tier = environment_tier;
    }
    @Override
    public String toString() {
        return "EnvDAO [project_id=" + project_id + ", environment=" + environment_tier + "]";
    }
    public String getProject_id() {
        return project_id;
    }
    public String getEnvironment_tier() {
        return environment_tier;
    }
    
    
}
