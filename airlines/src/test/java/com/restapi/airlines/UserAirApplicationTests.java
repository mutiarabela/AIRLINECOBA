package com.restapi.airlines;

import com.restapi.airlines.controller.UserController;
import com.restapi.airlines.exception.UserNotFoundException;
import com.restapi.airlines.model.request.UserDetailsRequestModel;
import com.restapi.airlines.model.response.UserResponseModel;
import com.restapi.airlines.service.UserService;
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
public class UserAirApplicationTests {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Autowired
    UserService userService;

    @Autowired
    UserController userController;

    @Test
    public void createUser(){
        UserDetailsRequestModel user = new UserDetailsRequestModel();
        user.setIdUser("mutiarabela");
        user.setFirstName("Mutiara");
        user.setLastName("Bela");
        user.setTypeUser("Silver");
        user.setPhoneNum("082111711170");
        user.setEmailUser("bela@gmail.com");
        user.setPasswordUser("12345678");
        user.setSaldoUser(1000000.00);

        log.info("-------- Parameter that we POST [User] --------");
        log.info("idUser="+user.getIdUser());
        log.info("firstName="+user.getFirstName());
        log.info("lastName="+user.getLastName());
        log.info("typeUser="+user.getTypeUser());
        log.info("phoneNum="+user.getPhoneNum());
        log.info("emailUser="+user.getEmailUser());
        log.info("passwordUser="+user.getPasswordUser());
        log.info("saldoUser="+user.getSaldoUser());

        UserResponseModel create = userService.createUser(user);

        assertThat(create.getIdUser()).isEqualTo(user.getIdUser());
        assertThat(create.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(create.getLastName()).isEqualTo(user.getLastName());
        assertThat(create.getTypeUser()).isEqualTo(user.getTypeUser());
        assertThat(create.getPhoneNum()).isEqualTo(user.getPhoneNum());
        assertThat(create.getEmailUser()).isEqualTo(user.getEmailUser());
        assertThat(create.getPasswordUser()).isEqualTo(user.getPasswordUser());
        assertThat(create.getSaldoUser()).isEqualTo(user.getSaldoUser());

        log.info("----------- Posted Parameter [User] -----------");
        log.info(create.toString());

        ResponseEntity createUser = userController.createUser(user);
        assertThat(createUser.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getUser() {

        createUser();
        log.info("Create idUser=mutiarabela");

        String idUser  = "mutiarabela";
        log.info("GET idUser=mutiarabela");

        UserResponseModel result = userService.getUser(idUser);
        assertThat(result.getFirstName()).isEqualTo("Mutiara");
        assertThat(result.getLastName()).isEqualTo("Bela");
        assertThat(result.getTypeUser()).isEqualTo("Silver");
        assertThat(result.getPhoneNum()).isEqualTo("082111711170");
        assertThat(result.getEmailUser()).isEqualTo("bela@gmail.com");
        assertThat(result.getPasswordUser()).isEqualTo("12345678");
        assertThat(result.getSaldoUser()).isEqualTo(1000000.00);

        log.info("-------- Parameter that we GET --------");
        log.info(result.toString());

        ResponseEntity getUser = userController.getUser(idUser);
        assertThat(getUser.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getAllUser(){
        createUser();

        Collection getCreatedUsers = userService.getAllUser();
        assertThat(getCreatedUsers.size()).isEqualTo(3);

        log.info("------ Get All Users Success ------");

        ResponseEntity getAllUser = userController.getAllUser();
        assertThat(getAllUser.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void updatePhoneNumUser(){
        createUser();

        log.info("Update phoneNumUser=082111711170 for idUser=mutiarabela");

        String idUser = "mutiarabela";
        UserResponseModel userResponseModel = userService.getUser(idUser);
        String updatedIdUser = userResponseModel.getIdUser();
        String updatedPhoneNum  = userResponseModel.getPhoneNum();
        String updatedFirstName  = userResponseModel.getFirstName();
        String updatedLastName  = userResponseModel.getLastName();
        String updatedTypeUser  = userResponseModel.getTypeUser();
        String updatedEmailUser  = userResponseModel.getEmailUser();
        String updatedPasswordUser  = userResponseModel.getPasswordUser();
        double updatedSaldoUser  = userResponseModel.getSaldoUser();

        UserDetailsRequestModel userDetailsRequestModel = new UserDetailsRequestModel();
        UserResponseModel updatedUser = userService.updatePhoneNumUser(idUser, userDetailsRequestModel);

        updatedUser.setPhoneNum("081222722270");

        log.info("------------- Posted Parameter------------- ");
        log.info(updatedUser.toString());

        log.info("------------- Compared Parameter----------- ");
        log.info(" idUser="       + updatedIdUser       + " firstNameUser=" + updatedFirstName +
                 " lastNameUser=" + updatedLastName + " phoneNumUser="  + updatedPhoneNum +
                 " userNameUser=" + updatedTypeUser + " emailUser="     + updatedEmailUser +
                 " passwordUser=" + updatedPasswordUser + " saldoUser="     + updatedSaldoUser);

        assertThat(updatedUser.getIdUser()).isEqualTo(updatedIdUser);
        assertThat(updatedUser.getPhoneNum()).isNotEqualTo(updatedPhoneNum);

        ResponseEntity updatePhoneNumUser = userController.updatePhoneNumUser(idUser, userDetailsRequestModel);
        assertThat(updatePhoneNumUser.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void deleteUser(){
        createUser();

        log.info("---------- Delete User for idUser=mutiarabela ----------");

        userService.deleteUser("mutiarabela");
        assertThat(userService.getUser("mutiarabela")).isNull();

        log.info("------------ Delete User Success -------------");
    }

    @Test(expected = UserNotFoundException.class)
    public  void UserNotFoundException(){
        createUser();
        String idUser = "belamutiara";
        ResponseEntity create = userController.getUser(idUser);
        log.info(create.toString());
    }

    @Test
    public void deleteNotFound(){
        exceptionRule.expect(UserNotFoundException.class);
        exceptionRule.expectMessage("Delete Failed, User Not Found");

        String idUser = "belamutiara";

        ResponseEntity deleteUser = userController.deleteUser(idUser);

    }

}
