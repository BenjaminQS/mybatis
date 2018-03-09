package com.how2java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.how2java.pojo.Category;

public interface CategoryMapper {
	      
	@Select(" select * from category_ ")
    @Results({ 
                @Result(property = "id", column = "id"),
                @Result(property = "products", javaType = List.class, column = "id", many = @Many(select = 
                "com.how2java.mapper.ProductMapper.listByCategory") )
            })
    public List<Category> list();

}
