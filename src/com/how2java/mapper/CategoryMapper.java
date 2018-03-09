package com.how2java.mapper;


import org.apache.ibatis.annotations.Select;

import com.how2java.pojo.Category;

public interface CategoryMapper {
	      
	@Select(" select * from category_ where id = #{id}")
    public Category get(int id);

}
