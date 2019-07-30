package com.ycz.data.structure.design.factory.method;

/**
 * @Description:控制台工厂
 * @Author: hz18123767/ycz
 * @Date:2019/7/30
 */
public class ConsoleLoggerFactory implements LoggerFactory {


    @Override
    public void createLogger() {
        Logger logger = new ConsoleLogger();
        logger.writeLog();
    }

}
