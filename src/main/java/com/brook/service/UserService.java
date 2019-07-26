package com.brook.service;

import com.brook.bean.OperateResult;
import com.brook.bean.User;
import com.brook.bean.UserRegister;
import com.brook.bean.VicrabResult;

public interface UserService {

    /**
     * 获取用户信息
     *
     * @param userKey
     * @return
     */
    User getUser(String userKey);

    /**
     * 根据邮箱获取用户信息
     *
     * @param userEmail
     * @return
     */
    OperateResult getUserByEmail(String userEmail);

    /**
     * 用户登录
     *
     * @param userEmail
     * @param userPwd
     * @return
     */
    VicrabResult login(String userEmail, String userPwd);

    /**
     * 用户授权登录
     *
     * @param userEmail
     * @return
     */
    VicrabResult<User> loginAuthorized(String userEmail);

    /**
     * 用户注册
     *
     * @param userRegister
     * @return
     */
    VicrabResult<User> register(UserRegister userRegister);

    /**
     * 邀请用户
     *
     * @param userEmail
     * @return
     */
    User inviteUser(String userEmail);


    /**
     * 更新用户邮箱
     *
     * @param userKey
     * @param userEmail
     * @param currentUserPwd
     * @return
     */
    VicrabResult<Boolean> modifyUserEmail(String userKey, String userEmail, String currentUserPwd);

    /**
     * 修改用户密码
     *
     * @param userKey
     * @param userPwd
     * @param confirmUserPwd
     * @param currentUserPwd
     * @return
     */
    VicrabResult<Boolean> modifyUserPassword(String userKey, String userPwd, String confirmUserPwd, String currentUserPwd);

    /**
     * 用户激活
     *
     * @param userKey
     * @param verifyEmail
     * @return
     */
    VicrabResult<Boolean> userActivate(String userKey, String verifyEmail);

    /**
     * 用户确认
     *
     * @param userKey
     * @param verifyEmail
     * @param organizeKey
     * @return
     */
    VicrabResult<Boolean> userInviteConfirm(String userKey, String verifyEmail, String organizeKey);

    /**
     * 设置用户
     *
     * @param userRegister
     * @return
     */
    VicrabResult<User> setUserInfo(UserRegister userRegister);
}
