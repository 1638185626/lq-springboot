package com.example.lqesmall.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.lqesmall.entity.TProduct;

/**
 * (TProduct)表数据库访问层
 *
 * @author liuqing
 * @since 2022-05-20 23:15:04
 */
@Mapper
public interface TProductDao extends BaseMapper<TProduct> {
//
///**
//* 批量新增数据（MyBatis原生foreach方法）
//*
//* @param entities List<TProduct> 实例对象列表
//* @return 影响行数
//*/
//int insertBatch(@Param("entities") List<TProduct> entities);
//
///**
//* 批量新增或按主键更新数据（MyBatis原生foreach方法）
//*
//* @param entities List<TProduct> 实例对象列表
//* @return 影响行数
//* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
//*/
//int insertOrUpdateBatch(@Param("entities") List<TProduct> entities);

}

