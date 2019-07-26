package com.brook.bean;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * 时间类型：MINUTE: 分钟 HOUR: 小时 DAY: 天 MONTH: 月 YEAR: 年
 */
public enum DateType {
  
  MINUTE("MINUTE"),
  
  HOUR("HOUR"),
  
  DAY("DAY"),
  
  MONTH("MONTH"),
  
  YEAR("YEAR");

  private String value;

  DateType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static DateType fromValue(String text) {
    for (DateType b : DateType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

