package com.springboot.coronavirustracker.controllers;

import com.springboot.coronavirustracker.models.LocationStats;
import com.springboot.coronavirustracker.services.CovidDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restapi")
public class RestHomeController {

    CovidDataService covidDataService;

    public RestHomeController(CovidDataService covidDataService) {
        this.covidDataService = covidDataService;
    }

    @GetMapping(value = "/react")
    public Map<String, Object>  reactJson() {
        Map<String, Object> responseMap = new HashMap<>();
        List<LocationStats> allStats = covidDataService.getAllStats();
        responseMap.put("allLocationStats", covidDataService.getAllStats());
        responseMap.put("totalReportedCases", covidDataService.getTotalReportedCases());
        int prevDayTotalCases = allStats.stream().mapToInt(LocationStats::getPrevDayTotalCases).sum();
        responseMap.put("totalReportedCasesToday", covidDataService.getTotalReportedCases() - prevDayTotalCases );

        return responseMap;
    }
}
