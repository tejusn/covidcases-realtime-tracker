package com.springboot.coronavirustracker.controllers;

import com.springboot.coronavirustracker.models.LocationStats;
import com.springboot.coronavirustracker.services.CovidDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
//@RestController - if given this, it would make this a restcontroller and all the responses would be a json.
//Instead we are making it a @Controller to return the template name(HTML file name)
//@RestController
public class HomeController {

    private final CovidDataService covidDataService;

    public HomeController(CovidDataService covidDataService) {
        this.covidDataService = covidDataService;
    }

    @GetMapping("/")
    //Thymeleaf dependancy enables to return the html file name specified in the GetMapping method
    public String home(Model model) {
        //model object helps to bind the value to the HTML
        List<LocationStats> allStats = covidDataService.getAllStats();
        model.addAttribute("locationStats", allStats);

        int totalCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        //we can use the above totalCases as well which is simpler Sum the stream of integer values
        model.addAttribute("totalReportedCases", covidDataService.getTotalReportedCases());

        int prevDayTotalCases = allStats.stream().mapToInt(LocationStats::getPrevDayTotalCases).sum();
        model.addAttribute("totalReportedCasesToday", totalCases - prevDayTotalCases );
        return "home";
    }

    @GetMapping("/react")
    //Endpoint renders the same page using the react
    public String homeJson(Model model) {
        //model object helps to bind the value to the HTML
        List<LocationStats> allStats = covidDataService.getAllStats();
        model.addAttribute("locationStats", allStats); //Not accessible in the ReactJS component - try this
        return "index";
    }


}
