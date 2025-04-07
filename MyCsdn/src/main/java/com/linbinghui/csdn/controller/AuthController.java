package com.linbinghui.csdn.controller;

import com.linbinghui.csdn.service.AuthService;
import com.linbinghui.csdn.view.ConsoleView;
import com.linbinghui.csdn.view.LoginView;

import java.util.Scanner;


public class AuthController {
private static AuthService authService = null;
private static final Scanner scanner = new Scanner(System.in);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    static ConsoleView consoleView = new ConsoleView();

    public static void AuthOperater() {
        consoleView.printHeader();
        consoleView.choseAuthMenu();
        int Auth=scanner.nextInt();
        while (true) {
            ConsoleView.AuthMenu();
            int choice = scanner.nextInt();
            if (Auth==1){
                switch (choice) {
                    case 1://注册
                        handleRegister();
                        break;
                    case 2://登录
                        LoginController.loginOperater(handleLogin());
                        break;
                    case 3://退出
                        consoleView.exit();
                    default:
                        ConsoleView.error();
                        break;
                }
            }else{
                switch (choice) {
                    case 1://注册
                        handleAdminRegister();
                        break;
                    case 2://登录
                        AdminController.AdminOperater(handleAdminLogin());
                        break;
                    case 3://退出
                        consoleView.exit();
                    default:
                        ConsoleView.error();
                        break;
                }
            }
        }
    }

//管理员操作
//用户操作
    // 用户注册
    public static void handleRegister() {
        String username = ConsoleView.InputUsername();
        String password = ConsoleView.InputPassword();
        boolean result = authService.register(username, password);
        ConsoleView.printResult(result, "注册成功", "该昵称已存在,注册失败");
    }

    // 用户登录
    public static boolean handleLogin(){
        String username = ConsoleView.InputUsername();
        String password = ConsoleView.InputPassword();
        boolean result = authService.login(username, password);
        ConsoleView.printResult(result, "登录成功", "账号或密码错误，登录失败");
        return result;
    }
    //管理员登录
    public static boolean handleAdminLogin(){
        String username = ConsoleView.InputUsername();
        String password = ConsoleView.InputPassword();
        boolean result = authService.adminLogin(username, password);
        ConsoleView.printResult(result, "登录成功", "账号或密码错误，登录失败");
        return result;
    }
    // 管理员注册
    public static void handleAdminRegister() {
        String username = ConsoleView.InputUsername();
        String password = ConsoleView.InputPassword();
        boolean result = authService.adminRegister(username, password);
        ConsoleView.printResult(result, "注册成功", "该昵称已存在,注册失败");
    }
}
