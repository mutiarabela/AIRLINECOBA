package com.restapi.airlines.service.implement;

import com.restapi.airlines.model.request.TransactionRequestModel;
import com.restapi.airlines.model.response.*;
import com.restapi.airlines.service.AirlineService;
import com.restapi.airlines.service.DiscountService;
import com.restapi.airlines.service.TransactionService;
import com.restapi.airlines.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionServiceImplement implements TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private DiscountService discountService;

    TransactionResponseModel returnValue;

    HashMap<String, TransactionResponseModel> transactions;

    public TransactionResponseModel createTransaction(TransactionRequestModel transactionRequestModel){

        returnValue = new TransactionResponseModel();

        String idUser    = transactionRequestModel.getIdUser();
        String idAirline = transactionRequestModel.getIdAirline();

        UserResponseModel userTemp = userService.getUser(idUser);
        AirlineResponseModel airlineTemp = airlineService.getAirline(idAirline);

        returnValue.setFirstName(userTemp.getFirstName());
        returnValue.setLastName(userTemp.getLastName());
        returnValue.setPhoneNum(userTemp.getPhoneNum());
        returnValue.setEmailUser(userTemp.getEmailUser());
        returnValue.setDestinAirline(airlineTemp.getDestinAirline());
        returnValue.setAirportOrigin(airlineTemp.getAirportOrigin());
        returnValue.setAirportDestin(airlineTemp.getAirportDestin());

        returnValue.setTypeUser(userTemp.getTypeUser());
        String typeUser = returnValue.getTypeUser();
        DiscountResponseModel discTemp = discountService.getDiscount(typeUser);

        returnValue.setPriceAirline(airlineTemp.getPriceAirline());
        double priceAirline = returnValue.getPriceAirline();

        returnValue.setDiscountAirline(airlineTemp.getDiscountAirline());
        double discountPrice = returnValue.getDiscountAirline();

        double total = priceAirline - ((priceAirline * discountPrice)/100);
        returnValue.setTotPriceAirline(total);

        if (transactions == null) {
            transactions = new HashMap<>();
        }

        transactions.put(idUser,returnValue);

        return returnValue;
    }

    private double totalTransaction(){
        double total = 0.00;
        Map<String, TransactionResponseModel> map = transactions;
        for (Map.Entry<String, TransactionResponseModel> entry : map.entrySet()) {
            total += entry.getValue().getTotPriceAirline();
        }
        return total;
    }

    public AllTransactionResponseModel getAllTransaction(){
        AllTransactionResponseModel allTransactionResponse = new AllTransactionResponseModel();
        allTransactionResponse.setTransaction(transactions.values());
        allTransactionResponse.setTotalTransaction(totalTransaction());
        return allTransactionResponse;
    }
}
