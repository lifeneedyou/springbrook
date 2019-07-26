package com.brook.bean;

public enum OperateCode {

    /**
     * 系统异常
     */

    SYSTEM_ERROR("9001", "系统异常"),
    EX_NULL("9002", "空指针异常"),
    EX_CAST("9003", "类型转换异常"),
    EX_IO("9004", "类型转换异常"),
    EX_NO_SUCH_METHOD("9005", "未知方法异常"),
    EX_INDEX_OUT_OF_BOUNDNS("9006", "数组越界异常"),
    EX_STACK_OVER_FLOW("9007", "栈溢出"),
    BAD_REQUEST("9008", "400 Bad Request"),
    METHOD_NOT_ALLOWED("9009", "405 Method Not Allowed"),
    NOT_ACCEPTABLE("9010", "406 Not Acceptable"),
    INTERNAL_SERVER_ERROR("9011", "500 Internal Server Error"),
    EX_OTHER("9012", "其他错误"),
    EX_RUN("9013", "运行时异常"),
    UNAUTHORIZED("9014", "未授权，请设置token"),
    UNPERMISSION("9015", "没有权限"),

    SUCCESS("0000", "成功"),
    FAIL("0001", "失败"),
    PARAM_ERROR("1001", "参数错误"),

    /**
     * 用户
     */
    USER_EXIST("2001", "用户已注册"),

    USER_NOT_EXIST("2002", "用户不存在"),

    PASSWORD_DIFF("2003", "密码不一致"),

    LOGIN_ERROR("2O04", "用户邮箱或密码错误"),

    EMAIL_PATTERN_ERROR("2O05", "邮箱格式错误"),

    USER_REGISTER_ERROR("2006", "用户注册失败"),

    USER_ORIGIN_PWD_ERROR("2007", "密码不一致"),

    EMAIL_EXIST("2008", "用户邮箱已存在"),

    EMAIL_DIFF("2009", "用户邮箱不一致"),

    VERIFY_ERROR("2010", "验证错误"),

    VERIFY_CODE_EXPIRE("2011", "验证码过期"),

    VERIFYED("2012", "已验证"),

    VERIFY_ATTR_ERROR("2013", "验证业务信息错误"),

    VERIFY_ATTR_DIFF("2014", "验证业务信息不一致"),

    USER_NO_ACTIVATE("2015", "用户未激活"),

    USER_INVITED("2016", "被邀请用户"),

    USER_DISABLE("2017", "用户已被禁用"),

    ORIGIN_PASSWORD_ERROR("2018", "原始密码错误"),

    ORIGIN_PASSWORD_EQUAL("2019", "新密码与原始密码相同，请输入新密码"),

    /**
     * 组织
     */
    ORGANIZE_EXIST("2101", "组织已存在"),

    ORGANIZE_NOT_EXIST("2102", "组织不存在"),

    /**
     * 团队
     */
    TEAM_EXIST("2201", "团队已存在"),

    TEAM_NOT_EXIST("2202", "团队不存在"),

    TEAM_NAME_EXIST("2203", "组织中团队名称已存在"),
    /**
     * 成员
     */
    MEMBER_INVITE_ERROR("2301", "邀请失败"),

    MEMBER_EXIST("2302", "成员已存在"),

    MEMBER_NOT_EXIST("2303", "成员不存在"),

    MEMBER_OWNER_ROLE("2304", "必须存在一个OWNER角色"),


    /**
     * 项目
     */
    PROJECT_EXIST("2401", "项目已存在"),

    PROJECT_NAME_EXIST("2404", "项目名称已存在"),

    PROJECT_NOT_EXIST("2402", "项目不存在"),

    PROJECT_INSTALL_GUIDE("2403", "安装指引"),

    /**
     * 问题
     */
    ISSUE_NOT_EXIST("2501", "问题不存在"),

    ISSUE_SUMMARY_INIT("2601", "issue summary 未完成初始化"),

    ISSUE_SUMMARY_EXIST("2602", "issue summary 不存在");

    private String operateCode;
    private String operateMsg;

    OperateCode(String operateCode, String operateMsg) {
        this.operateCode = operateCode;
        this.operateMsg = operateMsg;
    }

    public java.lang.String getOperateCode() {
        return operateCode;
    }

    public java.lang.String getOperateMsg() {
        return operateMsg;
    }
}
