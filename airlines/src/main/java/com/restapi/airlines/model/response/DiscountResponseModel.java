package com.restapi.airlines.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DiscountResponseModel {

    private String typeUser;
    private double discUser;
    private String discMessage;
}
