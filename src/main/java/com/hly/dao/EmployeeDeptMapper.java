package com.hly.dao;

import com.hly.entity.Employee;

public interface EmployeeDeptMapper {
    public Employee getEmployeeWithDeptById0(int id);

    public Employee getEmployeeWithDeptById(int id);

    public Employee getEmpByStep(int id);
}
