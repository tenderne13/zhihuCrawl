package com.lxp.mapper;

import com.lxp.util.mapper.MyBatisRepository;
import com.lxp.vo.Installation;
import com.lxp.vo.Person;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface InstallationMapper {
    List<Installation> getInstallationIndexList(Map<String, Object> parmap);
}
