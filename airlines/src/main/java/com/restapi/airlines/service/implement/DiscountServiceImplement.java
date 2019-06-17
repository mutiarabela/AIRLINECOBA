package com.restapi.airlines.service.implement;

import com.restapi.airlines.model.request.DiscountRequestModel;
import com.restapi.airlines.model.response.DiscountResponseModel;

import java.util.Collection;
import java.util.HashMap;

public class DiscountServiceImplement {
    DiscountResponseModel returnValue;

    HashMap<String, DiscountResponseModel> discount;

    public DiscountServiceImplemet(){
        DiscountResponseModel discountResponseModel1 = new DiscountResponseModel();

        discountResponseModel1.setTypeUser("Silver");
        String typeUser1 = discountResponseModel1.getTypeUser();

        discountResponseModel1.setDiscUser(0);
        discountResponseModel1.setDiscMessage("You got 0% discount, update your membership to Bronze for 10% discount or Gold for 20% discount!");

        if(discount == null){
            discount = new HashMap<>();
        }

        discount.put(typeUser1, discountResponseModel1);

        DiscountResponseModel discountResponseModel2 = new DiscountResponseModel();

        discountResponseModel2.setTypeUser("Bronze");
        String typeUser2 = discountResponseModel2.getTypeUser();

        discountResponseModel2.setDiscUser(0);
        discountResponseModel2.setDiscMessage("You got 10% discount, update your membership to Gold for 20% discount, otherwise Enjoy!");

        discount.put(typeUser2, discountResponseModel2);

        DiscountResponseModel discountResponseModel3 = new DiscountResponseModel();

        discountResponseModel3.setTypeUser("Gold");
        String typeUser3 = discountResponseModel2.getTypeUser();

        discountResponseModel3.setDiscUser(0);
        discountResponseModel3.setDiscMessage("You got 20% discount, Enjoy!");

        discount.put(typeUser3, discountResponseModel3);

    }

    @Override
    public DiscountResponseModel createDiscount(DiscountRequestModel discountRequestModel) {
        returnValue = new DiscountResponseModel();

        returnValue.setTypeUser(discountRequestModel.getTypeUser());
        String typeUser = returnValue.getTypeUser();

        returnValue.setTypeUser(discountRequestModel.getTypeUser());
        returnValue.setDiscUser(discountRequestModel.getDiscUser());
        returnValue.setDiscMessage(discountRequestModel.getDiscMessage());

        if (discount == null) {
            discount = new HashMap<>();
        }

        discount.put(typeUser, returnValue);
        return returnValue;
    }

    public DiscountResponseModel getDiscount (String typeUser) {
        return discount.get(typeUser);
    }

    public Collection<DiscountResponseModel> getAllDiscount() {
        return discount.values();
    }

    public DiscountResponseModel deleteDiscount(String typeUser){ return discount.remove(typeUser); }

    public DiscountResponseModel updateDiscUser (String typeUser, DiscountRequestModel discountRequestModel) {
        if(discount.containsKey(typeUser)){
            DiscountResponseModel storedDisc = discount.get(typeUser);
            storedDisc.setDiscUser(discountRequestModel.getDiscUser());

            discount.put(typeUser, storedDisc);
        }
        return discount.get(typeUser);
    }
}
