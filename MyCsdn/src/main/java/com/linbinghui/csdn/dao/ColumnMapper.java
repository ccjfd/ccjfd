package com.linbinghui.csdn.dao;

import com.linbinghui.csdn.entity.Column;

import java.util.List;

public interface ColumnMapper {
    List<Column> selectlist();
    List<Column> selectByUId(Column column);
    Column selectByColumnName(Column column);
    Integer insert(Column column);
    String deleteByColumnName(Column column);
}
