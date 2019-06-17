package com.restapi.airlines.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class AirlineDetailsRequestModel {

    @NotNull(message="Airline name must be filled")
    @Size(min=2, message="Airline name must be filled")
    private String idAirline;

    @NotBlank(message="Type of plane must be filled")
    private String typeAirline;

    @NotBlank(message="Airport Origin must be filled")
    private String airportOrigin;

    @NotBlank(message="Airport Destination must be filled")
    private String airportDestin;

    @NotBlank(message="Destination name must be filled")
    private String destinAirline;

    @NotNull(message="Price must be filled")
    private double priceAirline = 0.00;

}
