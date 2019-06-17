package com.restapi.airlines.controller;

import com.restapi.airlines.model.request.DiscountRequestModel;
import com.restapi.airlines.model.response.DiscountResponseModel;
import com.restapi.airlines.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/Discounts")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @GetMapping(path = "/{typeUser}",
                produces = { MediaType.APPLICATION_JSON_VALUE,
                             MediaType.APPLICATION_XML_VALUE  })
    public ResponseEntity getDisccount (@PathVariable String typeUser){
        DiscountResponseModel returnValue = discountService.getDiscount(typeUser);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getAlLDiscount(){
        Collection returnValue = discountService.getAllDiscount();
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE,
                              MediaType.APPLICATION_JSON_VALUE },
                 produces = { MediaType.APPLICATION_XML_VALUE,
                              MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity createDiscount(@Valid @RequestBody DiscountRequestModel discountRequestModel){
        DiscountResponseModel returnValue = discountService.createDiscount(discountRequestModel);
        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{typeUser}",
                consumes = { MediaType.APPLICATION_XML_VALUE,
                             MediaType.APPLICATION_JSON_VALUE },
                produces = { MediaType.APPLICATION_XML_VALUE,
                             MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<DiscountResponseModel> updateDiscUser(@PathVariable String typeUser, @Valid @RequestBody DiscountRequestModel discountRequestModel){
        DiscountResponseModel returnValue = discountService.updateDiscUser(typeUser, discountRequestModel);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{typeUser}")
    public ResponseEntity deleteDiscount(@PathVariable String typeUser){
        DiscountResponseModel returnValue = discountService.deleteDiscount(typeUser);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }
}
