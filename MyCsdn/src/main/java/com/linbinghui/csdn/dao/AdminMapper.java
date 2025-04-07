package com.linbinghui.csdn.dao;

import com.linbinghui.csdn.entity.Admin;

public interface AdminMapper {
    Admin selectByName(Admin admin);
    Integer insert(Admin admin);
}
