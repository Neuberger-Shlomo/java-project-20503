package com.example.demo.controllers.rest;

import com.example.demo.db.entities.Users;
import com.example.demo.db.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping(path = "/users")
public class UserController extends RestApiAbstract<Users,UserRepo> {
    Logger logger = LogManager.getLogger(UserController.class);
    final UserRepo repo;

    public UserController(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserRepo getRepo(){
        return repo;
    }



    private boolean validatePassword(String password){
        boolean retVal = true;
        retVal &= password.length() > Users.PASSWORD_MIN_LENGTH;
        retVal &= password.length() <  Users.PASSWORD_MAX_LENGTH;
        return retVal;
    }

    @Override
    public boolean isValid(Users user) {
        boolean isValidPassword =validatePassword(user.getPassword());
        return isValidPassword;
    }

}