package com.cbec.b2b.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cbec.b2b.entity.menu.Menu;
import com.cbec.b2b.entity.menu.MenuChildren;
import com.cbec.b2b.entity.message.MessageCountEntity;
import com.cbec.b2b.entity.message.MessageEntity;
import com.cbec.b2b.entity.register.RegisterStepOne;
import com.cbec.b2b.entity.register.RegisterStepTwo;
import com.cbec.b2b.entity.register.UserStatus;
import com.cbec.b2b.entity.response.CurrentUser;
import com.cbec.b2b.entity.response.LoginResponseEntity;
import com.cbec.b2b.entity.user.User;

public interface UserMapper {
	LoginResponseEntity validate(@Param("account") String account,@Param("password") String password);
	Integer isAuth(@Param("account") String account,@Param("url") String url);
	CurrentUser getUser(@Param("account") String account);
	List<Menu> getMenuTop(@Param("account") String account);
	List<MenuChildren> getMenuChildren(@Param("account") String account,@Param("menuid") String menuid);
	List<MessageEntity> getMessageByUserCode(@Param("account") String account);
	MessageCountEntity getMessageCountByUserCode(@Param("account") String account);
	int updateMessageByUserCodeType(@Param("account") String account,@Param("type") String type);
	int insertUser(RegisterStepOne registerStepOne);
	Integer isUser(@Param("account") String account);
	Integer insertUserRole(@Param("userid") Integer userid,@Param("roleid") Integer roleid);
	int updateUserRegister(RegisterStepTwo tegisterStepTwo);
	UserStatus getUserStatus(@Param("account") String account);
	Map<String,Object> getUserType(@Param("account") String account);
	Integer updatetUserStatusById(@Param("verifycode") String verifycode,@Param("id") String id,@Param("failmark") String failmark);
	int updatetUserRoleRegister(@Param("userid") Integer userid,@Param("roleid") Integer roleid);
	List<User> getPageUserForCheck(User user);
	List<User> getUserList(User user);
	User getUserByUserId(@Param("id") String id);
	int updateUserStatusByUserId(@Param("id") String id,@Param("flag") String flag);
	
	int updateLoginLastTime(@Param("account") String account);
}
