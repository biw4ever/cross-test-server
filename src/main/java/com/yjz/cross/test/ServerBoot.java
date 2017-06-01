package com.yjz.cross.test;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerBoot
{
    public static void main(String[] args) throws InterruptedException
    {
        new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        new CountDownLatch(1).await();
    }
}
