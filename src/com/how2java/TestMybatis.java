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
		CategoryMapper mapper = session.getMapper(CategoryMapper.class);
		
		update(mapper);
		
//		Map<String, Object> params = new HashMap<>();
//		params.put("name", "a");
//		List<Product> ps2 = session.selectList("listProduct", params);
//		for (Product p : ps2){
//			System.out.println(p);
//		}
		
		session.commit();
		session.close();
	}
	
	private static void update(CategoryMapper mapper) {
		Category c = mapper.get(2);
		c.setName("category2");
		mapper.update(c);
		listAll(mapper);
	}
	

	private static void listOrder(SqlSession session) {
		List<Order> os = session.selectList("listOrder");
		for (Order o : os) {
			System.out.println(o.getCode());
			List<OrderItem> ois = o.getOrderItems();
			for (OrderItem oi : ois) {
				System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(), oi.getProduct().getPrice(),
						oi.getNumber());
			}
		}
	}
	
	private static void addOrderItem(SqlSession session){
		
		Order o1 = session.selectOne("getOrder", 1);
		Product p6 = session.selectOne("getProduct", 6);
		OrderItem oi = new OrderItem();
		oi.setProduct(p6);
		oi.setOrder(o1);
		oi.setNumber(200);
		
		session.insert("addOrderItem", oi);
	}
	
	private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }
}
