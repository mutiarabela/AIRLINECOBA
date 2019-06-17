package com.restapi.airlines.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class DiscountRequestModel {

    @NotBlank(message = "Type user must be filled, with Silver, Bronze or Gold")
    private String typeUser;

    private double discUser = 0;

    @NotBlank(message = "Discount Message must be filled, with Silver, Bronze or Gold")
    private String discMessage;

}
