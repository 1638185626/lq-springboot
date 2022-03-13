package com.example.lqmongo.mapper;

import com.example.lqmongo.model.StockDO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liuqing
 */
@Repository
public interface StockMapper extends MongoRepository<StockDO, String> {
}
