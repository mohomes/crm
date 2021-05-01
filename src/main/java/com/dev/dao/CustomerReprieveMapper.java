package com.dev.dao;

import com.dev.base.BaseMapper;
import com.dev.vo.CustomerReprieve;

public interface CustomerReprieveMapper extends BaseMapper<CustomerReprieve,Integer> {

    int deleteRepById(Integer id);
}