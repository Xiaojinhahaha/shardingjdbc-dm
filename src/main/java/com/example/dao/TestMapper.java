package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.TestDo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TestMapper  extends BaseMapper<TestDo> {

}
