package com.linbinghui.csdn.view;

import java.util.Scanner;

public class LoginView {
    private static final Scanner scanner = new Scanner(System.in);

public static void loginMenu() {
    printSeparator();
    System.out.println("1. 搜索");
    System.out.println("2. 个人中心");
    System.out.println("3. 我的博文");
    System.out.println("4. 退出登录");
    printSeparator();
    System.out.print("请选择操作: ");
    }






    public static void error(){
        System.out.println("无效选项!");
    }
    private static void printSeparator() {
        System.out.println("-------------------------------------");
    }
}
