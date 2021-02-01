package com.springboot.coronavirustracker.models;

import lombok.Data;
import lombok.Generated;

@Data
public class LocationStats {

    private String state;
    private String country;
    private int latestTotalCases;
    private int prevDayTotalCases;
}
