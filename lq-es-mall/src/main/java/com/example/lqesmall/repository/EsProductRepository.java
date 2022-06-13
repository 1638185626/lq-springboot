package com.example.lqesmall.repository;

import com.example.lqesmall.model.EsProduct;
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
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {



    /**
     * 搜索查询
     *
     * @param name              商品名称
     * @param subTitle          商品标题
     * @param keywords          商品关键字
     * @param page              分页信息
     * @return
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);

}
