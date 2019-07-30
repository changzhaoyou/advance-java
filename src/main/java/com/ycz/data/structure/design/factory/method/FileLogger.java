package com.ycz.data.structure.design.factory.method;

/**
 * @Description:文件日志
 * @Author: hz18123767/ycz
 * @Date:2019/7/29
 */
public class FileLogger implements Logger{

    @Override
    public void writeLog() {
        System.out.println("文件日志输出");
    }
}
