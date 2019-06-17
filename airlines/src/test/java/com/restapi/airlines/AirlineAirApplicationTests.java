package com.restapi.airlines;

import com.restapi.airlines.controller.AirlineController;
import com.restapi.airlines.exception.AirlineNotFoundException;
import com.restapi.airlines.model.request.AirlineDetailsRequestModel;
import com.restapi.airlines.model.response.AirlineResponseModel;
import com.restapi.airlines.service.AirlineService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AirlineAirApplicationTests {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Autowired
    AirlineService airlineService;

    @Autowired
    AirlineController airlineController;

    @Test
    public void createAirline(){
        AirlineDetailsRequestModel airline = new AirlineDetailsRequestModel();
        airline.setIdAirline("Garuda");
        airline.setTypeAirline("Boeing G1");
        airline.setAirportOrigin("Soekarno Hatta");
        airline.setAirportDestin("Adi Soetjipto");
        airline.setDestinAirline("Yogyakarta");
        airline.setPriceAirline(1000000.00);

        AirlineResponseModel create = airlineService.createAirline(airline);

        assertThat(create.getIdAirline()).isEqualTo(airline.getIdAirline());
        assertThat(create.getTypeAirline()).isEqualTo(airline.getTypeAirline());
        assertThat(create.getAirportOrigin()).isEqualTo(airline.getAirportOrigin());
        assertThat(create.getAirportDestin()).isEqualTo(airline.getAirportDestin());
        assertThat(create.getDestinAirline()).isEqualTo(airline.getDestinAirline());
        assertThat(create.getPriceAirline()).isEqualTo(airline.getPriceAirline());

        ResponseEntity createAirline = airlineController.createAirline(airline);
        assertThat(createAirline.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getAirline() {

        createAirline();

        String idAirline  = "Garuda";

        AirlineResponseModel result = airlineService.getAirline(idAirline);
        assertThat(result.getIdAirline()).isEqualTo("Garuda");
        assertThat(result.getTypeAirline()).isEqualTo("Boeing G1");
        assertThat(result.getAirportOrigin()).isEqualTo("Soekarno Hatta");
        assertThat(result.getAirportDestin()).isEqualTo("Adi Soetjipto");
        assertThat(result.getDestinAirline()).isEqualTo("Yogyakarta");
        assertThat(result.getPriceAirline()).isEqualTo(1000000.00);

        ResponseEntity responseEntity = airlineController.getAirline(idAirline);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getAllAirline(){
        createAirline();

        Collection getCreatedAirlines = airlineService.getAllAirline();
        assertThat(getCreatedAirlines.size()).isEqualTo(3);

        ResponseEntity getAllAirline = airlineController.getAllAirline();
        assertThat(getAllAirline.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void updatePriceAirline(){
        createAirline();

        log.info("Update priceAirline=1150000.00 for idAirline=Garuda");

        String idAirline = "Garuda";
        AirlineResponseModel airlineResponseModel = airlineService.getAirline(idAirline);
        String updatedIdAirline = airlineResponseModel.getIdAirline();
        double updatedPriceAirline = airlineResponseModel.getPriceAirline();

        AirlineDetailsRequestModel airlineDetailsRequestModel = new AirlineDetailsRequestModel();
        AirlineResponseModel updatedAirline = airlineService.updatePriceAirline(idAirline, airlineDetailsRequestModel);

        updatedAirline.setPriceAirline(950000.00);

        assertThat(updatedAirline.getIdAirline()).isEqualTo(updatedIdAirline);
        assertThat(updatedAirline.getPriceAirline()).isNotEqualTo(updatedPriceAirline);

        ResponseEntity updatePriceAirline = airlineController.updatePriceAirline(idAirline, airlineDetailsRequestModel);
        assertThat(updatePriceAirline.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void deleteAirline(){
        createAirline();

        AirlineResponseModel getCreatedAirline = airlineService.getAirline("Garuda");
        String idAirline = getCreatedAirline.getIdAirline();

        airlineService.deleteAirline(idAirline);
        assertThat(airlineService.getAirline(idAirline)).isNull();

    }

    @Test(expected = AirlineNotFoundException.class)
    public  void AirlineNotFoundExceptionGet(){
        createAirline();
        String idAirline = "BatikAir";
        ResponseEntity create = airlineController.getAirline(idAirline);
        log.info(create.toString());
    }

    @Test
    public void deleteNotFound(){
        exceptionRule.expect(AirlineNotFoundException.class);
        exceptionRule.expectMessage("Delete Airline Failed, Airline Not Found");

        String idAirline = "BatikAir";

        ResponseEntity deleteUser = airlineController.deleteAirline(idAirline);

    }

}
