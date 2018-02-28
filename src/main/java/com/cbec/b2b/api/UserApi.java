package com.cbec.b2b.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbec.b2b.common.PageInfo;
import com.cbec.b2b.entity.MsgResponse;
import com.cbec.b2b.entity.menu.Menu;
import com.cbec.b2b.entity.message.MessageCountEntity;
import com.cbec.b2b.entity.message.MessageEntity;
import com.cbec.b2b.entity.register.RegisterStepTwo;
import com.cbec.b2b.entity.register.UserStatus;
import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;
import com.cbec.b2b.entity.user.User;
import com.cbec.b2b.service.IUserService;
import com.github.pagehelper.PageHelper;

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
    public MsgResponse registerSubmit(@RequestParam String mail,@RequestParam String password,@RequestParam String type) {
        return service.registerSubmit(mail,password,type);
    }
    
    @RequestMapping(value = "/register/code")
    public String registerCode(@RequestParam String mail) {
        return service.registerCode(mail);
    }
    
    @RequestMapping(value = "/register/upload")
    public String registerInfoUpload(@RequestParam RegisterStepTwo request) {
    	return service.registerInfoUpload(request);
    }
    
    @RequestMapping(value = "/register/status")
    public UserStatus registerStatus(@RequestParam String userName) {
    	return service.registerStatus(userName);
    }
    
    @RequestMapping(value = "/register/check")
    public String registerCheck(@RequestParam String userId,@RequestParam String usercode,@RequestParam String check,@RequestParam String usertype,@RequestParam String failmark) {
    	return service.registerCheck(userId,usercode,check,usertype,failmark);
    }
    
    @RequestMapping(value = "user/pagelist")
    public PageInfo<User> getPageUser(@RequestParam User user) {
    	PageHelper.startPage(user.getCurrent(),user.getPageSize());
    	List<User> userList = service.getPageUser(user);
    	PageInfo<User> pageData = new PageInfo<User>(userList);
    	return pageData;
    }
    
}



