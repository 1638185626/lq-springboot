package com.example.lqesmall.repository;

import com.example.lqesmall.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @className: EsProductRepository
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/4/8
 **/
@Repository
public interface JdGoodsRepository extends ElasticsearchRepository<JdGoods,String> {



}
