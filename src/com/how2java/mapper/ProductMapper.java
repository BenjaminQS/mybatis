package com.how2java.mapper;


import org.apache.ibatis.annotations.Select;


import com.how2java.pojo.Product;

public interface ProductMapper {
	
	@Select("select * from product_ where id = #{id}")
	public Product get(int id);
}
