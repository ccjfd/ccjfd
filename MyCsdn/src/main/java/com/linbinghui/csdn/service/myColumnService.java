package com.linbinghui.csdn.service;

import com.linbinghui.csdn.controller.CurrentUser;
import com.linbinghui.csdn.dao.ColumnMapper;
import com.linbinghui.csdn.entity.Blog;
import com.linbinghui.csdn.entity.Column;

import java.util.List;

public class myColumnService {

private static ColumnMapper columnMapper;
public myColumnService(ColumnMapper columnMapper){
    this.columnMapper=columnMapper;
}

//查询并返回我的专栏集合
public static List<Column> getMyColumn(){
    Column c = new Column();
    c.setUserid(CurrentUser.getCurrentUser().getId());
    List<Column> columns = columnMapper.selectByUId(c);
    if (columns.get(0) != null) {
        return columns;
    } else {
        return null;
        /*System.out.println("您还没有创建过专栏!");*/
    }
}
//添加专栏
    public static boolean addColumn(String columnName){
        Column c = new Column();
        c.setColumnname(columnName);
        Column C = columnMapper.selectByColumnName(c);
        if (C == null){
            Column column = new Column();
            column.setUserid(CurrentUser.getCurrentUser().getId());
            column.setColumnname(columnName);
            columnMapper.insert(column);
            return true;
        }
        return false;
    }
    //删除专栏
    public static boolean deleteColumn(String columnName){
        Column c = new Column();
        c.setColumnname(columnName);
        Column C = columnMapper.selectByColumnName(c);
        if (C != null){
            columnMapper.deleteByColumnName(C);
            return true;
        }
        return false;
    }
}
