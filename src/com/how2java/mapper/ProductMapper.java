package com.how2java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.One;

import com.how2java.pojo.Product;

public interface ProductMapper {
	@Select("select * from product_ where id = #{id}")

	public Product get(int id);
}
