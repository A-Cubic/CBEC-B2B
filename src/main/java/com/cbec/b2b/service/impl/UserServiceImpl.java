package com.cbec.b2b.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cbec.b2b.common.ContentErrorMsg;
import com.cbec.b2b.common.EmailUtils;
import com.cbec.b2b.common.OSSUtils;
import com.cbec.b2b.common.ServiceException;
import com.cbec.b2b.common.Util;
import com.cbec.b2b.entity.MsgResponse;
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
import com.cbec.b2b.mapper.UserMapper;
import com.cbec.b2b.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserMapper mapper;
	
	@Autowired
	EmailUtils emailUtils;
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	
	@Override
	public LoginResponseEntity validate(String account,String password) {
		LoginResponseEntity response = mapper.validate(account, password);
		if(response != null) {
			return response;
		}else {
			ServiceException ex = new ServiceException(ContentErrorMsg.ERROR_3);
			throw ex;
		}
	}
	
	@Override
	public Boolean isAuth(String account,String url) {
		Integer num = mapper.isAuth(account,url);
		if(num != null && num>0) {
			return true;
		}
		return false;
	}

	@Override
	public CurrentUser getUser(String account) {
		return mapper.getUser(account);
	}
	
	@Override
	public List<Menu> getMenuTop(String account) {
		List<Menu> menuList = mapper.getMenuTop(account);
		
		for(Menu bean : menuList) {
			String auth = bean.getAuthoritys();
			if(auth!=null && !"".equals(auth)) {
				List<String> menuAuth = new ArrayList<String>();
				String[] strs = auth.split(",");
				for(String s : strs) {
					menuAuth.add(s);
				}
				bean.setAuthority(menuAuth);
			}
			
			List<MenuChildren> clildList = bean.getChildren();
			if(clildList!=null && clildList.size()>0) {
				for(MenuChildren clild : clildList) {
					String auth_clild = clild.getAuthoritys();
					if(auth_clild!=null && !"".equals(auth_clild)) {
						List<String> menuChildAuth = new ArrayList<String>();
						String[] strs_child = auth_clild.split(",");
						for(String s : strs_child) {
							menuChildAuth.add(s);
						}
						clild.setAuthority(menuChildAuth);
					}
				}
			}
		}
		return menuList;
	}
	
	@Override
	public List<MenuChildren> getMenuChildren(String account,String menuid) {
		return mapper.getMenuChildren(account,menuid);
	}

	@Override
	public List<MessageEntity> getMessage(String account) {
		return mapper.getMessageByUserCode(account);
	}


	@Override
	public String updateMessage(String account, String type) {
		int num = mapper.updateMessageByUserCodeType(account,type);
		String response = String.format("清空%s成功.",type);
		if(num<1) {
			response = "没有新的消息.";
		}
		return response;
	}

	@Override
	public MessageCountEntity getMessageCount(String account) {
		return mapper.getMessageCountByUserCode(account);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public MsgResponse registerSubmit(String mail,String password,String type) {
		RegisterStepOne registerStepOne = new RegisterStepOne();
		registerStepOne.setMail(mail);
		registerStepOne.setPassword(Util.getMD5(password));
		registerStepOne.setType(type);
		if("1".equals(type)) {
			registerStepOne.setAvatar("http://ecc-product.oss-cn-beijing.aliyuncs.com/upload/head_s.png");
		}else {
			registerStepOne.setAvatar("http://ecc-product.oss-cn-beijing.aliyuncs.com/upload/head_p.png");
		}
		MsgResponse response = new MsgResponse();
		
		int userNum = mapper.isUser(mail);
		
		if(userNum>0) {
			response.setMsg("账号已存在");
			return response;
		}
		
		int insertUserNum = mapper.insertUser(registerStepOne);
		
		if(insertUserNum<1) {
			response.setMsg("注册失败，请检查格式");
			return response;
		}
		
		Integer role_id = 5;
		
		if("2".equals(type) || "3".equals(type) || "4".equals(type)) {
			role_id = 6;
		}
		
		int insertUserRoleNum = mapper.insertUserRole(registerStepOne.getId(),role_id);
		
		if(insertUserRoleNum>0) {
			response.setMsg("注册成功，请重新登录，完善资料.");
			response.setType("1");
			return response;
		}
		
		response.setMsg("注册失败");
		return response;
	}

	@Override
	public String registerCode(String mail) {
		String code = Util.randomCode();
		emailUtils.sendRegisterCode(mail,code);
		return code;
	}

	@Override
	public String registerInfoUpload(RegisterStepTwo bean) {
		
		String img_1 = bean.getImg1();
		String img_2 = bean.getImg2();
		String img_3 = bean.getImg3();
		String img_4 = bean.getImg4();
		
		InputStream in1 = null;
		InputStream in2 = null;
		InputStream in3 = null;
		InputStream in4 = null;
        try {
			if(!"".equals(img_1)) {
				String img1_name =  Util.createUuid()+OSSUtils.img_suffix;
				in1 = new ByteArrayInputStream(Util.decryptBASE64(img_1));
				OSSUtils.uploadOSSToInputStream(in1, img1_name);
				bean.setImg1(OSSUtils.head+img1_name);
			}
			if(!"".equals(img_2)) {
				String img2_name =  Util.createUuid()+OSSUtils.img_suffix;
				in2 = new ByteArrayInputStream(Util.decryptBASE64(img_2));
				OSSUtils.uploadOSSToInputStream(in2, img2_name);
				bean.setImg2(OSSUtils.head+img2_name);
			}
			if(!"".equals(img_3)) {
				String img3_name =  Util.createUuid()+OSSUtils.img_suffix;
				in3 = new ByteArrayInputStream(Util.decryptBASE64(img_3));
				OSSUtils.uploadOSSToInputStream(in3, img3_name);
				bean.setImg3(OSSUtils.head+img3_name);
			}
			if(!"".equals(img_4)) {
				String img4_name =  Util.createUuid()+OSSUtils.img_suffix;
				in4 = new ByteArrayInputStream(Util.decryptBASE64(img_4));
				OSSUtils.uploadOSSToInputStream(in4, img4_name);
				bean.setImg4(OSSUtils.head+img4_name);
			}
			mapper.updateUserRegister(bean);
        } catch (Exception e) {
			logger.error("base64转化失败，原因："+e.getMessage());
		}finally{
			try {
				if(in1!=null){
					in1.close();
				}
				if(in2!=null){
					in2.close();
				}
				if(in3!=null){
					in3.close();
				}
				if(in4!=null){
					in4.close();
				}
			} catch (IOException e) {
				logger.error("输入流失败，原因："+e.getMessage());
			}
		}
		
		return "上传成功";
	}

	@Override
	public UserStatus registerStatus(String account) {
		return mapper.getUserStatus(account);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String registerCheck(String account, String check) {
		Map<String,Object> map = mapper.getUserType(account);
		Integer id = (Integer)map.get("id");
		String type = (String)map.get("usertype");
		Integer role_id = 2;
		String verifycode = "4";
		
		if("1".equals(check)) {
			if("2".equals(type) || "3".equals(type) || "4".equals(type)) {
				role_id = 3;
			}
			mapper.updatetUserRoleRegister(id, role_id);
		}else {
			verifycode = "-1";
		}
		mapper.updatetUserStatusById(verifycode, id+"");
		
		return "审核成功";
	}

	@Override
	public List<User> getPageUser(User user) {
		return mapper.getPageUserForCheck(user);
	}


}
