package com.lxp.mapper;

import com.lxp.util.mapper.MyBatisRepository;
import com.lxp.vo.Person;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface PersonMapper {
    List<Person> getPersonList(Map<String,Object> parmap);
}
