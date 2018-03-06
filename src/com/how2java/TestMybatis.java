package com.how2java;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.how2java.pojo.Category;

public class TestMybatis {
	
	public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        
        
        Map<String,Object> params = new HashMap<>();
        params.put("id", 2);
        params.put("name", "cat");
        List<Category> cs = session.selectList("listCategoryByIdAndName",params);
        for (Category c : cs) {
            System.out.println(c.getName());
        }
//        listAll(session);
        session.commit();
        session.close();
    }
	 private static void listAll(SqlSession session) {
	        List<Category> cs = session.selectList("listCategory");
	        for (Category c : cs) {
	            System.out.println(c.getName());
	        }
	    }
}
