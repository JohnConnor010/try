package com.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.myapp.domain.Category;

public interface CategoryMapper
{
	@Results({
		@Result(property = "id",column = "id"),
		@Result(property = "name",column = "name")
	})
	@Select("SELECT * FROM category")
	List<Category> getAllCategory();
}
