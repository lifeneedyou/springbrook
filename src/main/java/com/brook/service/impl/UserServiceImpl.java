 package com.brook.service.impl;

import com.brook.mapper.VcUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brook.bean.OperateResult;
import com.brook.bean.User;
import com.brook.bean.UserRegister;
import com.brook.bean.VicrabResult;
import com.brook.service.UserService;


@Service
public class UserServiceImpl implements UserService{


	@Autowired
	private VcUserMapper vcUserMapper;

	@Override
	public User getUser(String userKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult getUserByEmail(String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VicrabResult login(String userEmail, String userPwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VicrabResult<User> loginAuthorized(String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VicrabResult<User> register(UserRegister userRegister) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User inviteUser(String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VicrabResult<Boolean> modifyUserEmail(String userKey, String userEmail, String currentUserPwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VicrabResult<Boolean> modifyUserPassword(String userKey, String userPwd, String confirmUserPwd,
			String currentUserPwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VicrabResult<Boolean> userActivate(String userKey, String verifyEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VicrabResult<Boolean> userInviteConfirm(String userKey, String verifyEmail, String organizeKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VicrabResult<User> setUserInfo(UserRegister userRegister) {
		// TODO Auto-generated method stub
		return null;
	}

}
