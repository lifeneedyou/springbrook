package com.brook.bean;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserRegister
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T16:37:00.923+08:00")

public class UserRegister   {
  @JsonProperty("user_realname")
  private String userRealname = null;

  @JsonProperty("user_email")
  private String userEmail = null;

  @JsonProperty("user_pwd")
  private String userPwd = null;

  public UserRegister userRealname(String userRealname) {
    this.userRealname = userRealname;
    return this;
  }

   /**
   * 用户真实姓名
   * @return userRealname
  **/
  @ApiModelProperty(value = "用户真实姓名")


  public String getUserRealname() {
    return userRealname;
  }

  public void setUserRealname(String userRealname) {
    this.userRealname = userRealname;
  }

  public UserRegister userEmail(String userEmail) {
    this.userEmail = userEmail;
    return this;
  }

   /**
   * 用户邮箱
   * @return userEmail
  **/
  @ApiModelProperty(value = "用户邮箱")


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public UserRegister userPwd(String userPwd) {
    this.userPwd = userPwd;
    return this;
  }

   /**
   * 用户密码
   * @return userPwd
  **/
  @ApiModelProperty(value = "用户密码")


  public String getUserPwd() {
    return userPwd;
  }

  public void setUserPwd(String userPwd) {
    this.userPwd = userPwd;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserRegister userRegister = (UserRegister) o;
    return Objects.equals(this.userRealname, userRegister.userRealname) &&
        Objects.equals(this.userEmail, userRegister.userEmail) &&
        Objects.equals(this.userPwd, userRegister.userPwd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userRealname, userEmail, userPwd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserRegister {\n");
    
    sb.append("    userRealname: ").append(toIndentedString(userRealname)).append("\n");
    sb.append("    userEmail: ").append(toIndentedString(userEmail)).append("\n");
    sb.append("    userPwd: ").append(toIndentedString(userPwd)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

