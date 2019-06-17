package com.restapi.airlines.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionResponseModel {

    private String firstName;
    private String lastName;
    private String phoneNum;
    private String emailUser;
    private String idAirline;
    private String destinAirline;
    private String airportOrigin;
    private String airportDestin;
    private String typeUser;
    private String discMessage;
    private double discUser;
    private double priceAirline;
    private double finalPrice;
}
