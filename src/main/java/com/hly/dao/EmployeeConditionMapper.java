package com.hly.dao;

import com.hly.entity.Employee;

import java.util.List;

public interface EmployeeConditionMapper {

    /**
     * 测试if/where标签
     * @param employee
     * @return
     */
    public List<Employee> getEmployeeByConditionIf(Employee employee);

    /**
     * 测试choose/when/otherwise标签
     */
    public List<Employee> getEmployeeByConditionChoose(Employee employee);

    /**
     * 测试set标签
     */
    public Integer updateEmployeeById(Employee employee);

    /**
     * 测试foreach标签--用于in
     */
    public List<Employee> getEmpsInIds(List<Integer> ids);

    /**
     * 测试foreach标签--用于批量插入
     */
    public void batchAddEmps(List<Employee> list);
}
