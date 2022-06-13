package com.example.lqesmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lqesmall.dao.TProductDao;
import com.example.lqesmall.entity.TProduct;
import com.example.lqesmall.service.TProductService;
import org.springframework.stereotype.Service;

/**
 * (TProduct)表服务实现类
 *
 * @author liuqing
 * @since 2022-05-20 23:15:08
 */
@Service("tProductService")
public class TProductServiceImpl extends ServiceImpl<TProductDao, TProduct> implements TProductService {

}

