package com.yjz.cross.test;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.yjz.cross.CrossException;
import com.yjz.cross.config.Configuration;
import com.yjz.cross.server.annotation.CrossService;
import com.yjz.cross.server.init.CrossServerInitializer;

//@Component
public class TestAppAware implements ApplicationContextAware
{
    private static final Logger logger = LoggerFactory.getLogger(TestAppAware.class);
    
    @Value("#{cross['cross.server.address']}")
    private String serverAddress;
    
    @Value("#{cross['zk.address']}")
    private String zkAddress;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        try
        {
            // 获取标注了CrossService的所有SpringBean对象
            Map<String, Object> crossServiceMap = applicationContext.getBeansWithAnnotation(CrossService.class);
            Object[] objects = crossServiceMap.values().toArray();
            
            Configuration conf = new Configuration();
            conf.addRegistry(zkAddress.trim());
            conf.setServerAddress(serverAddress.trim());
            
            CrossServerInitializer.bootStrap(objects, conf);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new CrossException(e);
        }
    }
    
}
