package com.linbinghui.csdn.util.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoundSql {
    //要执行的SQL语句(转换完毕后的SQL语句)
    private String sqlText;

    //执行SQL语句所需要的参数集合
    private List<String> parameterMappingList = new ArrayList<String>();

}
