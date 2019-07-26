package com.brook.controller;

import io.swagger.annotations.ApiParam;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.brook.bean.OperateCode;
import com.brook.bean.OperateResult;
import com.brook.bean.User;
import com.brook.bean.UserRegister;
import com.brook.bean.VicrabResult;
import com.brook.enums.AuditLogAnnotation;
import com.brook.enums.AuditLogEnum;
import com.brook.service.UserService;
import com.brook.utils.MailUtils;

import javax.validation.constraints.*;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-16T11:20:10.134+08:00")

@Controller
public class UserApiController implements UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<OperateResult> getUser(@ApiParam(value = "用户key", required = true) @PathVariable("user_key") String userKey) {
        OperateResult operateResult = new OperateResult();
        try {
            if (StringUtils.isBlank(userKey)) {
                operateResult = OperateResult.set(OperateCode.PARAM_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }
            User user = userService.getUser(userKey);
            operateResult = OperateResult.success(user);
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("api getUser error.{}", e);
            operateResult = OperateResult.exception(OperateCode.SYSTEM_ERROR, e);
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<OperateResult> getUserByEmail(@NotNull @ApiParam(value = "用户邮箱", required = true) @RequestParam(value = "user_email", required = true) String userEmail) {
        OperateResult operateResult = new OperateResult();
        try {
            if (StringUtils.isBlank(userEmail)) {
                operateResult = OperateResult.set(OperateCode.PARAM_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }
            operateResult = userService.getUserByEmail(userEmail);

            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("api getUser error.{}", e);
            operateResult = OperateResult.exception(OperateCode.SYSTEM_ERROR, e);
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<OperateResult> login(@NotNull @ApiParam(value = "用户邮箱", required = true) @RequestParam(value = "user_email", required = true) String userEmail, @NotNull @ApiParam(value = "用户密码", required = true) @RequestParam(value = "user_pwd", required = true) String userPwd) {
        OperateResult operateResult = new OperateResult();
        try {
            if (StringUtils.isBlank(userEmail) || StringUtils.isBlank(userPwd)) {
                operateResult = OperateResult.set(OperateCode.PARAM_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }
            // 验证邮箱
            if (!MailUtils.verifyEmail(userEmail)) {
                operateResult = OperateResult.set(OperateCode.EMAIL_PATTERN_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }

            VicrabResult<User> vicrabResult = userService.login(userEmail, userPwd);
            operateResult = OperateResult.set(vicrabResult.getOperateCode(), vicrabResult.getData());
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("api login error.{}", e);
            operateResult = OperateResult.exception(OperateCode.SYSTEM_ERROR, e);
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<OperateResult> loginAuthorized(@NotNull @ApiParam(value = "用户邮箱", required = true) @RequestParam(value = "user_email", required = true) String userEmail) {
        OperateResult operateResult = new OperateResult();
        try {
            if (StringUtils.isBlank(userEmail)) {
                operateResult = OperateResult.set(OperateCode.PARAM_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }
            // 验证邮箱
            if (!MailUtils.verifyEmail(userEmail)) {
                operateResult = OperateResult.set(OperateCode.EMAIL_PATTERN_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }

            VicrabResult<User> vicrabResult = userService.loginAuthorized(userEmail);
            operateResult = OperateResult.set(vicrabResult.getOperateCode(), vicrabResult.getData());
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("api loginAuthorized error.{}", e);
            operateResult = OperateResult.exception(OperateCode.SYSTEM_ERROR, e);
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<OperateResult> modifyUserEmail(@ApiParam(value = "用户编号", required = true) @PathVariable("user_key") String userKey, @NotNull @ApiParam(value = "用户邮箱", required = true) @RequestParam(value = "user_email", required = true) String userEmail, @NotNull @ApiParam(value = "用户当前密码", required = true) @RequestParam(value = "current_user_pwd", required = true) String currentUserPwd) {
        OperateResult operateResult = new OperateResult();
        try {
            if (StringUtils.isBlank(userKey) || StringUtils.isBlank(userEmail) || StringUtils.isBlank(currentUserPwd)) {
                operateResult = OperateResult.set(OperateCode.PARAM_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }

            // 验证邮箱
            if (!MailUtils.verifyEmail(userEmail)) {
                operateResult = OperateResult.set(OperateCode.EMAIL_PATTERN_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }

            VicrabResult<Boolean> vicrabResult = userService.modifyUserEmail(userKey, userEmail, currentUserPwd);
            operateResult = OperateResult.set(vicrabResult.getOperateCode(), vicrabResult.getData());
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("api modifyUserEmail error.{}", e);
            operateResult = OperateResult.exception(OperateCode.SYSTEM_ERROR, e);
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<OperateResult> modifyUserPassword(@ApiParam(value = "用户编号", required = true) @PathVariable("user_key") String userKey, @NotNull @ApiParam(value = "用户当前密码", required = true) @RequestParam(value = "current_user_pwd", required = true) String currentUserPwd, @NotNull @ApiParam(value = "用户密码", required = true) @RequestParam(value = "user_pwd", required = true) String userPwd, @NotNull @ApiParam(value = "用户确认密码", required = true) @RequestParam(value = "confirm_user_pwd", required = true) String confirmUserPwd) {
        OperateResult operateResult = new OperateResult();
        try {
            if (StringUtils.isBlank(userKey) || StringUtils.isBlank(userPwd) || StringUtils.isBlank(confirmUserPwd) || StringUtils.isBlank(currentUserPwd)) {
                operateResult = OperateResult.set(OperateCode.PARAM_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }
            VicrabResult<Boolean> vicrabResult = userService.modifyUserPassword(userKey, userPwd, confirmUserPwd, currentUserPwd);
            operateResult = OperateResult.set(vicrabResult.getOperateCode(), vicrabResult.getData());
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("api modifyUserPassword error.{}", e);
            operateResult = OperateResult.exception(OperateCode.SYSTEM_ERROR, e);
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    public ResponseEntity<OperateResult> setUserInfo(@ApiParam(value = "", required = true) @Valid @RequestBody UserRegister user) {
        OperateResult operateResult = new OperateResult();
        try {
            if (user == null || StringUtils.isBlank(user.getUserEmail()) || StringUtils.isBlank(user.getUserPwd()) || StringUtils.isBlank(user.getUserRealname())) {
                operateResult = OperateResult.set(OperateCode.PARAM_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }

            // 验证邮箱
            if (!MailUtils.verifyEmail(user.getUserEmail())) {
                operateResult = OperateResult.set(OperateCode.EMAIL_PATTERN_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }

            VicrabResult<User> vicrabResult = userService.setUserInfo(user);
            operateResult = OperateResult.set(vicrabResult.getOperateCode(), vicrabResult.getData());
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("api register error.{}", e);
            operateResult = OperateResult.exception(OperateCode.SYSTEM_ERROR, e);
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<OperateResult> userActivate(@NotNull @ApiParam(value = "用户key", required = true) @RequestParam(value = "user_key", required = true) String userKey, @NotNull @ApiParam(value = "验证邮箱", required = true) @RequestParam(value = "verify_email", required = true) String verifyEmail) {
        OperateResult operateResult = new OperateResult();
        try {
            if (StringUtils.isBlank(verifyEmail)) {
                operateResult = OperateResult.set(OperateCode.PARAM_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }
            VicrabResult<Boolean> vicrabResult = userService.userActivate(userKey, verifyEmail);
            operateResult = OperateResult.set(vicrabResult.getOperateCode(), vicrabResult.getData());
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("api userActivate error.{}", e);
            operateResult = OperateResult.exception(OperateCode.SYSTEM_ERROR, e);
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @AuditLogAnnotation(event = AuditLogEnum.MEMBER_ACCEPT_INVITE)
    @Override
    public ResponseEntity<OperateResult> userInviteConfirm(@NotNull @ApiParam(value = "用户key", required = true) @RequestParam(value = "user_key", required = true) String userKey, @NotNull @ApiParam(value = "验证邮箱", required = true) @RequestParam(value = "verify_email", required = true) String verifyEmail, @NotNull @ApiParam(value = "组织编号", required = true) @RequestParam(value = "organize_key", required = true) String organizeKey) {
        OperateResult operateResult = new OperateResult();
        try {
            if (StringUtils.isBlank(userKey) || StringUtils.isBlank(organizeKey) || StringUtils.isBlank(verifyEmail)) {
                operateResult = OperateResult.set(OperateCode.PARAM_ERROR);
                return new ResponseEntity<OperateResult>(operateResult, HttpStatus.BAD_REQUEST);
            }
            VicrabResult<Boolean> vicrabResult = userService.userInviteConfirm(userKey, verifyEmail, organizeKey);
            operateResult = OperateResult.set(vicrabResult.getOperateCode(), vicrabResult.getData());
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("api userInviteConfirm error.{}", e);
            operateResult = OperateResult.exception(OperateCode.SYSTEM_ERROR, e);
            return new ResponseEntity<OperateResult>(operateResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
