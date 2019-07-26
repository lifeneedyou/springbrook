package com.brook.bean;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 */
@ApiModel(description = "分页")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-22T16:47:48.823+08:00")

public class PageData<T> {
    @JsonProperty("data")
    private List<T> data = null;

    @JsonProperty("data_total")
    private Integer dataTotal = null;

    public PageData(Integer dataTotal, List<T> data) {
        this.dataTotal = dataTotal;
        this.data = data;
    }

    public static <T> PageData<T> success(Integer dataTotal, List<T> data) {
        return new PageData<T>(dataTotal, data);
    }

    public static <T> PageData<T> empty() {
        return (PageData<T>) new PageData<Object>(0, Lists.newArrayList());
    }

    public PageData data(List<T> data) {
        this.data = data;
        return this;
    }

    public PageData addDataItem(T dataItem) {
        if (this.data == null) {
            this.data = new ArrayList<T>();
        }
        this.data.add(dataItem);
        return this;
    }

    /**
     * 列表
     *
     * @return data
     **/
    @ApiModelProperty(value = "列表")


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public PageData dataTotal(Integer dataTotal) {
        this.dataTotal = dataTotal;
        return this;
    }

    /**
     * 总数
     *
     * @return dataTotal
     **/
    @ApiModelProperty(value = "总数")


    public Integer getDataTotal() {
        return dataTotal;
    }

    public void setDataTotal(Integer dataTotal) {
        this.dataTotal = dataTotal;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PageData pageData = (PageData) o;
        return Objects.equals(this.data, pageData.data) &&
                Objects.equals(this.dataTotal, pageData.dataTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, dataTotal);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PageData {\n");

        sb.append("    data: ").append(toIndentedString(data)).append("\n");
        sb.append("    dataTotal: ").append(toIndentedString(dataTotal)).append("\n");
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

