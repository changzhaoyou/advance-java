package com.ycz.data.structure.design.factory.method;

/**
 * @Description:客户端
 * @Author: hz18123767/ycz
 * @Date:2019/7/30
 */
public class Client {

    public static void main(String[] args) {
        LoggerFactory loggerFactory = new FileLoggerFactory();
        loggerFactory.createLogger();
        LoggerFactory consoleLoggerFactory = new ConsoleLoggerFactory();
        consoleLoggerFactory.createLogger();
    }
}
