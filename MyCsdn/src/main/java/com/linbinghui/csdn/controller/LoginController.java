package com.linbinghui.csdn.controller;

import com.linbinghui.csdn.view.LoginView;
import com.linbinghui.csdn.view.PersonalCenterView;

import java.util.Scanner;

public class LoginController {
    private static final Scanner scanner = new Scanner(System.in);
    static LoginView loginView = new LoginView();
    public static void loginOperater(boolean result){
        if(result){
            while (true) {
                loginView.loginMenu();
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1://搜索
                        SearchController.searchOperater();
                        break;
                    case 2://个人中心
                        PersonalCenterController.personalCenterOperater();
                        break;
                    case 3://我的博文
                        myBlogController.myBlogOperater();
                        break;
                    case 4://返回
                        return;

                    default:
                        loginView.error();
                        break;
                }
             }
        }else{
            return;
        }
    }

}
