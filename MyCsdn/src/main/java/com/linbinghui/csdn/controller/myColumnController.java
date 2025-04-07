package com.linbinghui.csdn.controller;

import com.linbinghui.csdn.entity.Column;
import com.linbinghui.csdn.service.myColumnService;
import com.linbinghui.csdn.view.myColumnView;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class myColumnController {
    static myColumnView mycolumnView = new myColumnView();
    private static final Scanner scanner = new Scanner(System.in);

    private static myColumnService mycolumnService=null;
    public myColumnController(myColumnService mycolumnService) {
        this.mycolumnService = mycolumnService;
    }



    public static void myColumnOperater(){
        List<Column> columnList = myColumnView.showMyColumn(mycolumnService.getMyColumn());
        if (columnList == null){
            return;
        }
        while (true){
            mycolumnView.myColumnMenu();
            int choice = scanner.nextInt();
            switch (choice){
                case 1://新增专栏
                    AddColumn();
                    break;
                case 2://删除专栏
                    DeleteColumn();
                    break;
                case 3://返回
                    return;

                default:
                    mycolumnView.error();
                    break;
            }
        }
    }


// 新增专栏
    public static void AddColumn(){
        mycolumnView.printSeparator();
        myColumnView.addcolumnView();
        String columnname = scanner.next();
        if (mycolumnService.addColumn(columnname)){
            mycolumnView.success();
        }else{
            mycolumnView.errorAddColumn();
        }
    }
    // 删除专栏
    public static void DeleteColumn(){
        mycolumnView.printSeparator();
        mycolumnView.deleteColumnView();
        String columnname = scanner.next();
        if (mycolumnService.deleteColumn(columnname)){
            mycolumnView.success();
        }else{
            mycolumnView.errorDeleteColumn();
        }
    }
}
