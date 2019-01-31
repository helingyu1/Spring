package com.hly;

import com.hly.dao.EhcacheMapper;
import com.hly.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class EhcacheTest {

    SqlSessionFactory sqlSessionFactory = null;

    /**
     * 这种写法在多线程下调用是有问题的
     * @return
     * @throws IOException
     */
    public SqlSessionFactory initFactory() throws IOException{
        if(sqlSessionFactory == null){
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        return sqlSessionFactory;
    }

    public SqlSession getSession() throws IOException {
        initFactory();
        return this.sqlSessionFactory.openSession();
    }
    @Test
    public void testEhcache(){
        SqlSession sqlSession = null;
        try{
            sqlSession = getSession();
            EhcacheMapper mapper = sqlSession.getMapper(EhcacheMapper.class);
            Employee e1 = mapper.testEhcache(1);
            System.out.println(e1.getName());
            System.out.println("第一次查询完成");
            Employee e2 = mapper.testEhcache(1);
            System.out.println("第二次查询完成");
            boolean b1 = (e1 == e2);
            System.out.println("e1和e2是否相等：" + b1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
