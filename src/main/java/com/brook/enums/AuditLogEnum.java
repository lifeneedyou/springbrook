package com.brook.enums;


/**
 * @Author: xuequan
 * @Date: 2019/4/25 16:10
 * @Description:
 */
public enum AuditLogEnum {

    /**
     * 组织
     */

    ORGANIZE_CREATE("org", "create", "created the organization"),

    ORGANIZE_EDIT("org", "edit", "edited the organization"),

    ORGANIZE_REMOVE("org", "remove", "removed the organization"),

    ORGANIZE_RESTORE("org", "restore", "restored the organization"),

    /**
     * 项目
     */

    PROJECT_CREATE("project", "create", "created project {projectName}"),

    PROJECT_EDIT("project", "edit", "edited project {projectName}"),

    PROJECT_SET_TEAM("project", "set", "set project {projectName} team"),

    PROJECT_REMOVE("project", "remove", "removed project {projectName}"),

    /**
     * 团队
     */

    TEAM_CREATE("team", "create", "created team {teamName}"),

    TEAM_EDIT("team", "edit", "edited team {teamName}"),

    TEAM_REMOVE("team", "remove", "removed team {teamName}"),

    /**
     * 成员
     */

    MEMBER_JOIN_TEAM("member", "join-team", "joined team {teamName}"),

    MEMBER_ADD_TEAM("member", "join-team", "added {userRealName} to team {teamName}"),

    MEMBER_LEAVE_TEAM("member", "leave-team", "leave team {teamName}"),

    MEMBER_REMOVE_TEAM("member", "leave-team", "removed {userRealName} from {teamName}"),

    MEMBER_ACCEPT_INVITE("member", "accept-invite", "accepted the membership invite"),

    MEMBER_INVITE("member", "invite", "invited member {userRealName}"),

    MEMBER_ADD("member", "add", "added member {userRealName}"),

    MEMBER_REMOVE("member", "remove", "removed member {userRealName}"),

    MEMBER_LEAVE("member", "remove", "leave the organization");

    private String type;
    private String action;
    private String content;


    AuditLogEnum(String type, String action, String content) {
        this.type = type;
        this.action = action;
        this.content = content;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
