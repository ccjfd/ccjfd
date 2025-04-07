package com.linbinghui.csdn.controller;

import com.linbinghui.csdn.entity.User;

public class CurrentUser {
    private static User currentUser;
    public static User getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(User currentUser) {
        CurrentUser.currentUser = currentUser;
    }
}
