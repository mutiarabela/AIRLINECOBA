package com.restapi.airlines.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AirlineResponseModel {

    private String idAirline;
    private String typeAirline;
    private String airportOrigin;
    private String airportDestin;
    private String destinAirline;
    private double priceAirline = 0.00;

}
