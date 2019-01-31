package com.hly.dao;

import com.hly.entity.Employee;

import java.util.List;

public interface CacheMapper {

    public Employee testFirstLevelCache(Integer id);

    public void updateEmp(Employee employee);
}
