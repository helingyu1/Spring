package com.hly.dao;

import com.hly.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

    public Employee getEmpById(int id);

    public void insertEmployee(Employee employee);

    public void updateEmployee(Employee employee);

    public void deleteEmployee(int id);

    /**
     * 通过Param注解，指定参数map集合的key，否则需要使用param1，param2的形式
     * @param name
     * @param gender
     * @return
     */
    public List<Employee> getEmpByNameAndGender(@Param("name") String name, @Param("gender") String gender);
}

