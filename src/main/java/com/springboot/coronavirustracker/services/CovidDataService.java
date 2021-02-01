package com.springboot.coronavirustracker.services;

import com.springboot.coronavirustracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidDataService {

    private static  String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    //For avoiding people getting error response when we are updating the new stats we gonna maintain a class level variable to update from the newStats

    private List<LocationStats> allStats = new ArrayList<>();

    private int totalReportedCases = 0;

    public int getTotalReportedCases() {
        return totalReportedCases;
    }

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    //Execute this method after the construct of this service.
    // This annotation automatically executes this method when this service is initialised which happens during the start of the application.
    @Scheduled(cron = "* * 1 * * *") //sec-min-hrs-day
    //This runs the first hour of every day
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        StringReader stringReader = new StringReader(restTemplate.getForEntity(VIRUS_DATA_URL, String.class)
                                                         .getBody());
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(VIRUS_DATA_URL))
//                .build();
//        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
//        StringReader stringReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1)); //getting the day by index in the header
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));//getting the previous day index in the header
            totalReportedCases += Integer.parseInt(record.get(record.size() - 1));
            locationStat.setLatestTotalCases(latestCases);
            locationStat.setPrevDayTotalCases(prevDayCases);
            newStats.add(locationStat);
        }
        System.out.println(newStats);
        this.allStats = newStats;
    }

}
