package com.ycz.data.structure.design.factory.method;

/**
 * @Description:文件日志工厂
 * @Author: hz18123767/ycz
 * @Date:2019/7/30
 */
public class FileLoggerFactory implements LoggerFactory {
    @Override
    public void createLogger() {
        Logger logger = new FileLogger();
        logger.writeLog();
    }
}
