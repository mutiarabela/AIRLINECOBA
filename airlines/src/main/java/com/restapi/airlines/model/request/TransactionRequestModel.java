package com.restapi.airlines.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NotNull
public class TransactionRequestModel {

    @NotBlank(message = "User ID must be filled")
    @Size(min=2, message="User ID must be filled")
    private String idUser;

    @NotBlank(message = "Airline ID must be filled")
    @Size(min=2, message="Airline ID must be filled")
    private String idAirline;

    @NotBlank(message = "Destination must be filled")
    private String destinationAirline;

}
