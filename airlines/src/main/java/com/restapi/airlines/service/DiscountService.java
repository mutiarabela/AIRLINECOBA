package com.restapi.airlines.service;

import com.restapi.airlines.model.request.DiscountRequestModel;
import com.restapi.airlines.model.response.DiscountResponseModel;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface DiscountService {
    DiscountResponseModel createDiscount(DiscountRequestModel discountRequestModel);
    DiscountResponseModel getDiscount(String typeUser);
    Collection<DiscountResponseModel> getAllDiscount();
    DiscountResponseModel updateDiscUser(String typeUser, DiscountRequestModel discountRequestModel);
    DiscountResponseModel deleteDiscount(String typeUser);
}
