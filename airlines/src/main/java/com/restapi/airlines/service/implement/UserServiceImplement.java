package com.restapi.airlines.service.implement;

import com.restapi.airlines.model.request.UserDetailsRequestModel;
import com.restapi.airlines.model.response.UserResponseModel;
import com.restapi.airlines.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class UserServiceImplement implements UserService {
    UserResponseModel returnValue;

    HashMap<String, UserResponseModel> users;

    public UserServiceImplement(){
        UserResponseModel userResponseModel1 = new UserResponseModel();

        userResponseModel1.setIdUser("mutiarabela");
        String idUser1 = userResponseModel1.getIdUser();

        userResponseModel1.setFirstName("Mutiara");
        userResponseModel1.setLastName("Bela");
        userResponseModel1.setTypeUser("Silver");
        userResponseModel1.setPhoneNum("082111711170");
        userResponseModel1.setEmailUser("bela@gmail.com");
        userResponseModel1.setPasswordUser("123456789");
        userResponseModel1.setSaldoUser(1000000);

        if(users == null){
            users = new HashMap<>();
        }

        users.put(idUser1, userResponseModel1);

        UserResponseModel userResponseModel2 = new UserResponseModel();

        userResponseModel2.setIdUser("niallhoran");
        String idUser2 = userResponseModel2.getIdUser();

        userResponseModel2.setFirstName("Niall");
        userResponseModel2.setLastName("Horan");
        userResponseModel1.setTypeUser("Bronze");
        userResponseModel2.setPhoneNum("0811212999970");
        userResponseModel2.setEmailUser("niall@gmail.com");
        userResponseModel2.setPasswordUser("11111111");
        userResponseModel1.setSaldoUser(6000000);

        users.put(idUser2, userResponseModel2);

        UserResponseModel userResponseModel3 = new UserResponseModel();

        userResponseModel3.setIdUser("nurfalah");
        String idUser3 = userResponseModel3.getIdUser();

        userResponseModel3.setFirstName("Nur");
        userResponseModel3.setLastName("Falah");
        userResponseModel1.setTypeUser("Gold");
        userResponseModel3.setPhoneNum("081218077761");
        userResponseModel3.setEmailUser("falah@gmail.com");
        userResponseModel3.setPasswordUser("123123123");
        userResponseModel1.setSaldoUser(10000000);

        users.put(idUser3, userResponseModel3);
    }

    @Override
    public UserResponseModel createUser(UserDetailsRequestModel userDetails) {
        returnValue = new UserResponseModel();

        returnValue.setIdUser(userDetails.getIdUser());
        String idUser = returnValue.getIdUser();

        returnValue.setIdUser(userDetails.getIdUser());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setTypeUser(userDetails.getTypeUser());
        returnValue.setLastName(userDetails.getLastName());
        returnValue.setPhoneNum(userDetails.getPhoneNum());
        returnValue.setEmailUser(userDetails.getEmailUser());
        returnValue.setPasswordUser(userDetails.getPasswordUser());
        returnValue.setSaldoUser(userDetails.getSaldoUser());


        if (users == null) {
            users = new HashMap<>();
        }

        users.put(idUser, returnValue);
        return returnValue;
    }

    public UserResponseModel getUser (String idUser) {
        return users.get(idUser);
    }

    public Collection <UserResponseModel> getAllUser(){
        return users.values();
    }

    public UserResponseModel deleteUser(String idUser){
        return users.remove(idUser);
    }

    public UserResponseModel updatePhoneNumUser (String idUser, UserDetailsRequestModel userDetails) {
        if(users.containsKey(idUser)){
            UserResponseModel storedUser = users.get(idUser);
            storedUser.setPhoneNum(userDetails.getPhoneNum());

            users.put(idUser, storedUser);
        }
        return users.get(idUser);
    }
}
