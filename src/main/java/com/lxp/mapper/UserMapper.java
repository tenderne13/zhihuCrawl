package com.lxp.mapper;

import com.lxp.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface UserMapper {
    Integer selectCount();
}
