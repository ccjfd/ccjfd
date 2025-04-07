package com.linbinghui.csdn.view;

import com.linbinghui.csdn.controller.AuthController;

import java.util.Scanner;

public class ConsoleView {
    private static final Scanner scanner = new Scanner(System.in);

    public void printHeader() {
        System.out.println("====================================\n" +
                "           CSDN 用户管理系统 \n" +
                "====================================");
    }
public void choseAuthMenu() {
        System.out.println("您的身份是：");
        System.out.println("1. 普通用户    2. 管理员");
    }

    public static void AuthMenu() {
        printSeparator();
        System.out.println("1. 注册用户");
        System.out.println("2. 用户登录");
        System.out.println("3. 退出系统");
        printSeparator();
        System.out.print("请选择操作: ");
    }

    //用户名的输入，有返回值
    public static String InputUsername() {
        System.out.print("请输入用户名: ");
        String username = scanner.next();
        return username;
    }

    //密码的输入，有返回值
    public static String InputPassword() {
        System.out.print("请输入密码: ");
        String password = scanner.next();
        return password;
    }

    //用户登录注册成功或失败的提示
    public static void printResult(boolean result, String successMessage, String failureMessage) {
        printSeparator();
        System.out.println(result ? successMessage : failureMessage);
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println("-------------------------------------");
    }

    public static void exit() {
        System.out.println("感谢使用，再见！");
        System.exit(0);
    }

    public static void errorregister(){
        System.out.println("用户昵称已存在!");
    }
    public static void error(){
        System.out.println("无效选项!");
    }
}
