package com.hly;

import com.hly.dao.EmployeeMapper;
import com.hly.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
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
}
