package com.restapi.airlines.model.response;

import lombok.*;

@Getter
@Setter
@ToString
public class UserResponseModel {

    private String idUser;
    private String firstName;
    private String lastName;
    private String typeUser;
    private String phoneNum;
    private String emailUser;
    private String passwordUser;
    private double saldoUser;

}
