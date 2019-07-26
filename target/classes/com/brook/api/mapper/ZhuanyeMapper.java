package com.brook.api.mapper;

import com.brook.api.bean.Zhuanye;
import java.util.List;

public interface ZhuanyeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Zhuanye record);

    Zhuanye selectByPrimaryKey(Integer id);

    List<Zhuanye> selectAll();

    int updateByPrimaryKey(Zhuanye record);
}