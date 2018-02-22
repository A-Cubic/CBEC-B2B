package com.cbec.b2b.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cbec.b2b.entity.menu.Menu;
import com.cbec.b2b.entity.message.MessageCountEntity;
import com.cbec.b2b.entity.message.MessageEntity;
import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;
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
    public CurrentUser getUser(@RequestParam String userName) {
        return service.getUser(userName);
    }
    
    @RequestMapping(value = "/menu")
    public List<Menu> getMenu(@RequestParam String userid) {
    	return service.getMenuTop(userid);
    }
    
    @RequestMapping(value = "/message/list")
    public List<MessageEntity> getMessage(@RequestParam String userName) {
        return service.getMessage(userName);
    }
    
    @RequestMapping(value = "/message/count")
    public MessageCountEntity getMessageCount(@RequestParam String userName) {
        return service.getMessageCount(userName);
    }
    
    @RequestMapping(value = "/message/empty")
    public String updateMessage(@RequestParam String userName,@RequestParam String type) {
    	return service.updateMessage(userName,type);
    }
    
    @RequestMapping(value = "/register/submit")
    public String registerSubmit(@RequestParam String mail,@RequestParam String password,@RequestParam String captcha) {
        return "";
    }
    
    @RequestMapping(value = "/register/code")
    public String registerCode(@RequestParam String mail) {
        return service.registerCode(mail);
    }
}



