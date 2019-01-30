package com.hly;

import com.hly.dao.EmployeeConditionMapper;
import com.hly.dao.EmployeeDeptMapper;
import com.hly.dao.EmployeeMapper;
import com.hly.entity.Department;
import com.hly.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    public SqlSession getSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }

    @Test
    public void testQuery(){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee e = employeeMapper.getEmpById(1);
            System.out.println(e);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSession();
            Employee employee = new Employee();
            employee.setName("John He1122");
            employee.setGender("1");
            employee.setEmail("1@webank.com");
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            employeeMapper.insertEmployee(employee);
            sqlSession.commit();
            System.out.println("插入后，拿到的Id：" + employee.getId());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSession();
            Employee employee = new Employee();
            employee.setId(1);
            employee.setName("Lingyu He");
            employee.setGender("1");
            employee.setEmail("hhh@webank.com");
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            employeeMapper.updateEmployee(employee);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void testDelete(){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            employeeMapper.deleteEmployee(3);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void testMultiParamQuery(){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> list = employeeMapper.getEmpByNameAndGender("%h%","1");
            for (Employee e: list) {
                System.out.println(e);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void testAssociation0(){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSession();
            EmployeeDeptMapper employeeMapper = sqlSession.getMapper(EmployeeDeptMapper.class);
            Employee e = employeeMapper.getEmployeeWithDeptById0(4);
            System.out.println(e);
            System.out.println(e.getDepartment());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void testAssociation(){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSession();
            EmployeeDeptMapper employeeMapper = sqlSession.getMapper(EmployeeDeptMapper.class);
            Employee e = employeeMapper.getEmployeeWithDeptById(4);
            System.out.println(e);
            System.out.println(e.getDepartment());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void testAssociationByStep(){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSession();
            EmployeeDeptMapper employeeMapper = sqlSession.getMapper(EmployeeDeptMapper.class);
            Employee e = employeeMapper.getEmpByStep(1);
            System.out.println(e.getName());
            //System.out.println(e.getDepartment().getDepartmentName());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void testConditionIf() {
        SqlSession sqlSession = null;
        try {
            sqlSession = getSession();
            Employee e = new Employee();
            //e.setId(1);
            e.setName(null);
            e.setEmail("aaa@webank.com");
            e.setGender("1");
            EmployeeConditionMapper employeeConditionMapper = sqlSession.getMapper(EmployeeConditionMapper.class);
            List<Employee> emps = employeeConditionMapper.getEmployeeByConditionIf(e);
            for (Employee emp : emps) {
                System.out.println(emp.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testConditionChoose(){
        SqlSession sqlSession = null;
        try{
            sqlSession = getSession();
            Employee e = new Employee();
            //e.setId(1);
            e.setName(null);
            e.setEmail("@");
            e.setGender(null);
            EmployeeConditionMapper employeeConditionMapper = sqlSession.getMapper(EmployeeConditionMapper.class);
            List<Employee> emps = employeeConditionMapper.getEmployeeByConditionChoose(e);
            for (Employee emp : emps) {
                System.out.println(emp.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            sqlSession.close();
        }
    }

    @Test
    public void testConditionSet(){
        SqlSession sqlSession = null;
        try{
            sqlSession = getSession();
            Employee e = new Employee();
            e.setId(1);
            e.setName("何凌宇");
            e.setEmail("hhh@163.com");
            e.setGender("2");
            EmployeeConditionMapper employeeConditionMapper = sqlSession.getMapper(EmployeeConditionMapper.class);
            Integer ret = employeeConditionMapper.updateEmployeeById(e);
            System.out.println(ret);
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            sqlSession.close();
        }
    }

    @Test
    public void testConditionForeach(){
        SqlSession sqlSession = null;
        try{
            sqlSession = getSession();
            List<Integer> list = new ArrayList<Integer>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            EmployeeConditionMapper employeeConditionMapper = sqlSession.getMapper(EmployeeConditionMapper.class);
            List<Employee> emps = employeeConditionMapper.getEmpsInIds(list);
            for (Employee e: emps) {
                System.out.println(e);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            sqlSession.close();
        }
    }

    @Test
    public void testConditionForeachForAdd(){
        SqlSession sqlSession = null;
        try{
            sqlSession = getSession();
            List<Employee> list = new ArrayList<Employee>();
            Employee e1 = new Employee("test1","1","aaa@163.com", new Department(1));
            Employee e2 = new Employee("test2","0","bbb@163.com", new Department(1));
            EmployeeConditionMapper employeeConditionMapper = sqlSession.getMapper(EmployeeConditionMapper.class);
            list.add(e1);
            list.add(e2);
            employeeConditionMapper.batchAddEmps(list);
            sqlSession.commit();

        }catch (Exception e){
            e.printStackTrace();
        } finally{
            sqlSession.close();
        }
    }
}
