package com.how2java.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.how2java.pojo.Order;

import java.util.List;

import org.apache.ibatis.annotations.Many;
public interface OrderMapper {
	
	@Select("select * from order_")
    @Results({
           @Result(property = "id", column = "id"),
           @Result(property = "orderItems", javaType = List.class, column = "id", 
                   many = @Many(select = "com.how2java.mapper.OrderItemMapper.listByOrder"))
           })      
   public List<Order> list();

}
