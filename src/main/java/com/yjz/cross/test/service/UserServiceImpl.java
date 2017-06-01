package com.yjz.cross.test.service;

import org.springframework.stereotype.Service;

import com.yjz.cross.server.annotation.CrossService;
import com.yjz.cross.test.intf.UserService;

@Service
@CrossService
public class UserServiceImpl implements UserService
{
    public String getUserName()
    {
        return "user";
    }  
}
