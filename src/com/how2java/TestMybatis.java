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
import com.how2java.pojo.Product;

public class TestMybatis {
	
	public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        
        List<Product> ps = session.selectList("listProduct");
        for (Product p : ps) {
            System.out.println(p + "对应的分类是\t" + p.getCategory());
        }  
        
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
