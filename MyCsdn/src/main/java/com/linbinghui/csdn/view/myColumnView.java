package com.linbinghui.csdn.view;

import com.linbinghui.csdn.entity.Column;

import java.util.List;

public class myColumnView {

    public static List<Column> showMyColumn(List<Column> columns) {
        printSeparator();
        if (columns != null) {
            for (int i = 1; i <= columns.size(); i++) {
                System.out.println(i + ". " + columns.get(i - 1).getColumnname());
            }
            return columns;
        }else{
                System.out.println("您还没有创建过专栏!");
                return null;
            }
        }

    public static void myColumnMenu() {
        printSeparator();
        System.out.println("1. 新增专栏");
        System.out.println("2. 删除专栏");
        System.out.println("3. 返回");
        printSeparator();
        System.out.print("请选择操作: ");
    }
//新增专栏
    public static void addcolumnView() {
        System.out.print("请输入专栏名称: ");
    }
//删除专栏
    public static void deleteColumnView() {
        System.out.print("请输入专栏名称: ");
    }





    //错误提示
    public static void error(){
        System.out.println("无效选项!");
    }
    public static void errorAddColumn(){
        System.out.println("专栏名称不可重复!");
    }
    public static void errorDeleteColumn(){
        System.out.println("该专栏不存在!");
    }
    //成功提示
    public static void success(){
        System.out.println("操作成功!");
    }
    public static void printSeparator() {
        System.out.println("-------------------------------------");
    }
}
