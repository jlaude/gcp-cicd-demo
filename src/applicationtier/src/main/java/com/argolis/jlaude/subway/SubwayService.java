package com.argolis.jlaude.subway;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodySubscribers;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.argolis.jlaude.subway.NycTransit.TripUpdate;

@Service
public class SubwayService {

    private static final Logger logger = LoggerFactory.getLogger(SubwayService.class);

    @Value("${GCP_PROJECT_ID}")
    private String project_id;


    public Map<String, List<String>> getSubwayDetails() throws IOException, InterruptedException {

    //public ArrayList<String> getSubwayDetails() throws IOException, InterruptedException {

        final String apiKey = AccessSecretVersion.accessSecretVersion(project_id, "mta-subway-api-key", "1");

        List<String> arrivalTimesList = new ArrayList<>();
        List<String> delayedMinutesList = new ArrayList<>();
        Map<String, List<String>> trainTimes = new HashMap<>();
        HttpClient httpClient = HttpClient.newHttpClient();

        // Create an HTTP request with the Protocol Buffers message as the body
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api-endpoint.mta.info/Dataservice/mtagtfsfeeds/nyct%2Fgtfs-l"))
            .header("x-api-key", apiKey)
            .GET()
            .build();

        HttpResponse<InputStream> response = httpClient.send(request, responseInfo -> BodySubscribers.ofInputStream());
        NycTransit.FeedMessage feed = NycTransit.FeedMessage.parseFrom(response.body());

        for (NycTransit.FeedEntity ent: feed.getEntityList()) {

            //logger.info(ent.getAllFields().toString());

            if (ent.hasTripUpdate()) {

                TripUpdate tripUpdate = ent.getTripUpdate();

                for (TripUpdate.StopTimeUpdate stopTimeUpdate : tripUpdate.getStopTimeUpdateList()){

                    if (stopTimeUpdate.hasStopId()) {

                        //Get details about specific stop
                        if (stopTimeUpdate.getStopId().equals("L08N")) {

                            logger.info("stop_id: " + stopTimeUpdate.getStopId());

                            //get arrival details about specific stop
                            if (stopTimeUpdate.hasArrival()) {

                                TripUpdate.StopTimeEvent arrivalEvent = stopTimeUpdate.getArrival();
        
                                //get train arrival time
                                if (arrivalEvent.hasTime()) {
        
                                    long timeStamp = arrivalEvent.getTime();
        
                                    java.util.Date time=new java.util.Date(timeStamp*1000);
                                    
                                    //Format time to Eastern Time
                                    DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                                    format.setTimeZone(TimeZone.getTimeZone("America/New_York"));
                                    String formatted = format.format(time);
        
                                    logger.info("Scheduled Arrival Time: " + formatted);

                                    arrivalTimesList.add(formatted);
        
                                }
        
                                if (arrivalEvent.hasDelay()){
        
                                    Integer delayMinutes = arrivalEvent.getDelay() / 60;
                                    logger.info("Delayed : " + delayMinutes.toString() + " minutes");

                                    delayedMinutesList.add(delayMinutes.toString());
                                }
                            }
                        }
                    }
                }
            }
        }

        trainTimes.put("arrival_times", arrivalTimesList);
        trainTimes.put("delayed_minutes", delayedMinutesList);

        logger.info(arrivalTimesList.toString());
        logger.info(delayedMinutesList.toString());

        logger.info(trainTimes.toString());

        return trainTimes;

        //return arrivalTimesList;
    }
    
}
