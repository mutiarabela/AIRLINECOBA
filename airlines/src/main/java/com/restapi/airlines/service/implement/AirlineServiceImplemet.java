package com.restapi.airlines.service.implement;

import com.restapi.airlines.model.request.AirlineDetailsRequestModel;
import com.restapi.airlines.model.response.AirlineResponseModel;
import com.restapi.airlines.service.AirlineService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class AirlineServiceImplemet implements AirlineService {
    AirlineResponseModel returnValue;

    HashMap<String, AirlineResponseModel> airlines;

    public AirlineServiceImplemet(){
        AirlineResponseModel airlineResponseModel1 = new AirlineResponseModel();

        airlineResponseModel1.setIdAirline("Garuda");
        String idAirline1 = airlineResponseModel1.getIdAirline();

        airlineResponseModel1.setTypeAirline("Boeing G1");
        airlineResponseModel1.setAirportOrigin("Seokarno Hatta");
        airlineResponseModel1.setAirportDestin("Adi Soetjipto");
        airlineResponseModel1.setDestinAirline("Yogyakarta");
        airlineResponseModel1.setPriceAirline(1550000.00);

        if(airlines == null){
            airlines = new HashMap<>();
        }

        airlines.put(idAirline1, airlineResponseModel1);

        AirlineResponseModel airlineResponseModel2 = new AirlineResponseModel();

        airlineResponseModel2.setIdAirline("Citilink");
        String idAirline2 = airlineResponseModel2.getIdAirline();

        airlineResponseModel2.setTypeAirline("Boeing C1");
        airlineResponseModel2.setAirportOrigin("Halim Perdana Kusuma");
        airlineResponseModel2.setAirportDestin("Adi Soetjipto");
        airlineResponseModel2.setDestinAirline("Yogyakarta");
        airlineResponseModel2.setPriceAirline(750000.00);

        airlines.put(idAirline2, airlineResponseModel2);

        AirlineResponseModel airlineResponseModel3 = new AirlineResponseModel();

        airlineResponseModel3.setIdAirline("FlyAway");
        String idAirline3 = airlineResponseModel3.getIdAirline();

        airlineResponseModel3.setTypeAirline("Boeing F1");
        airlineResponseModel3.setAirportOrigin("Halim Perdana Kusuma");
        airlineResponseModel3.setAirportDestin("Juanda");
        airlineResponseModel3.setDestinAirline("Surabaya");
        airlineResponseModel3.setPriceAirline(1050000.00);

        airlines.put(idAirline3, airlineResponseModel3);

    }

    @Override
    public AirlineResponseModel createAirline(AirlineDetailsRequestModel airlineDetails) {
        returnValue = new AirlineResponseModel();

        returnValue.setIdAirline(airlineDetails.getIdAirline());
        String idAirline = returnValue.getIdAirline();

        returnValue.setIdAirline(airlineDetails.getIdAirline());
        returnValue.setTypeAirline(airlineDetails.getTypeAirline());
        returnValue.setAirportOrigin(airlineDetails.getAirportOrigin());
        returnValue.setAirportDestin(airlineDetails.getAirportDestin());
        returnValue.setDestinAirline(airlineDetails.getDestinAirline());
        returnValue.setPriceAirline(airlineDetails.getPriceAirline());

        if (airlines == null) {
            airlines = new HashMap<>();
        }

        airlines.put(idAirline, returnValue);
        return returnValue;
    }

    public AirlineResponseModel getAirline (String idAirline) {
        return airlines.get(idAirline);
    }

    public Collection<AirlineResponseModel> getAllAirline() {
        return airlines.values();
    }

    public AirlineResponseModel deleteAirline(String idAirline){ return airlines.remove(idAirline); }

    public AirlineResponseModel updatePriceAirline (String idAirline, AirlineDetailsRequestModel airlineDetails) {
        if(airlines.containsKey(idAirline)){
            AirlineResponseModel storedUser = airlines.get(idAirline);
            storedUser.setPriceAirline(airlineDetails.getPriceAirline());

            airlines.put(idAirline, storedUser);
        }
        return airlines.get(idAirline);
    }
}
