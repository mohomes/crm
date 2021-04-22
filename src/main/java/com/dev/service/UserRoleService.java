package com.dev.service;

import com.dev.base.BaseService;
import com.dev.vo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/4/22 14:48
 * @ClassName UserRoleService
 */
@Service
public class UserRoleService extends BaseService<UserRole,Integer> {

    @Autowired
    private UserRoleService userRoleService;
}
