package com.linbinghui.csdn.controller;

import com.linbinghui.csdn.service.PersonalCenterService;
import com.linbinghui.csdn.view.PersonalCenterView;

import java.util.Scanner;

import static java.lang.System.exit;

public class PersonalCenterController {
    private static final Scanner scanner = new Scanner(System.in);
    private static PersonalCenterService personalCenterService = null;

    public PersonalCenterController(PersonalCenterService personalCenterService) {
        this.personalCenterService = personalCenterService;
    }

    public static void personalCenterOperater()
    {
        while (true){
            PersonalCenterView.personalCenterMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1://个人信息
                    PersonalCenterService.personalInformation(CurrentUser.getCurrentUser().getName());
                    break;
                case 2://我关注过的博主
                    PersonalCenterView.followedBlogger(personalCenterService.followedBlogger());
                    break;
                case 3://我点赞过的博文
                    PersonalCenterView.likedBlog(personalCenterService.likedBlog());
                    break;
                case 4://退出
                    return;
                default:
                    PersonalCenterView.error();
                    break;
            }
        }
    }


}
