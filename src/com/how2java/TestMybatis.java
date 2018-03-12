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

import com.how2java.mapper.CategoryMapper;
import com.how2java.mapper.OrderMapper;
import com.how2java.mapper.ProductMapper;
import com.how2java.pojo.Category;
import com.how2java.pojo.Order;
import com.how2java.pojo.OrderItem;
import com.how2java.pojo.Product;

public class TestMybatis {

	public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
//        ProductMapper mapper = session.getMapper(ProductMapper.class);
 
        listOrder(session);
              
        session.commit();
        session.close();
   
    }
  
    private static void listOrder(SqlSession session) {
    	OrderMapper mapper = session.getMapper(OrderMapper.class);
    	List<Order> os = mapper.list();
    	for (Order o:os){
    		System.out.println(o.getCode());
    		List<OrderItem> ois = o.getOrderItems();
    		if(null != ois){
    			for(OrderItem oi : ois) {
    				System.out.format("\t%s\t%f\t%d%n", 
    						oi.getProduct().getName(),oi.getProduct().getPrice(),oi.getNumber());
    			}
    		}
    		
    	}
    }
}
