package com.cbec.b2b.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.entity.response.LoginResponseEntity;
import com.cbec.b2b.entity.response.UserResponseEntity;
import com.cbec.b2b.response.Response;
import com.cbec.b2b.service.IUserService;

@RestController
@RequestMapping(value = "/api")
public class UserApi {
    @Autowired
    IUserService service;
    

    @RequestMapping(value = "/validate")
    public LoginResponseEntity validate(@RequestParam String userName,@RequestParam String password) {
        return service.validate(userName, password);
    }
    
    @RequestMapping(value = "/currentUser")
    public Response<UserResponseEntity> getUser(@RequestParam String userName) {
        return service.getUser(userName);
    }
}



