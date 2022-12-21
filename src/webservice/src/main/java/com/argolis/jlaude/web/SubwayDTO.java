package com.argolis.jlaude.web;

import java.util.List;

public class SubwayDTO {

    private List<String> delayed_minutes;
    private List<String> arrival_times;
    
    public SubwayDTO(List<String> delayed_minutes, List<String> arrival_times) {
        this.arrival_times = arrival_times;
        this.delayed_minutes = delayed_minutes;
    }
    
    public SubwayDTO() {
    }

    public List<String> getArrival_times() {
        return arrival_times;
    }
    public void setArrival_times(List<String> arrival_times) {
        this.arrival_times = arrival_times;
    }
    public List<String> getDelayed_minutes() {
        return delayed_minutes;
    }
    public void setDelayed_minutes(List<String> delayed_minutes) {
        this.delayed_minutes = delayed_minutes;
    }

    @Override
    public String toString() {
        return "SubwayDTO [arrival_times=" + arrival_times + ", delayed_minutes=" + delayed_minutes + "]";
    }
    
}
