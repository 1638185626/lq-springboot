//package com.example.lqesmall.controller;
//
//
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.api.ApiController;
//import com.baomidou.mybatisplus.extension.api.R;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.example.lqesmall.entity.TProduct;
//import com.example.lqesmall.service.TProductService;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.io.Serializable;
//import java.util.List;
//
///**
// * (TProduct)表控制层
// *
// * @author liuqing
// * @since 2022-05-20 23:15:04
// */
//@RestController
//@RequestMapping("tProduct")
//public class TProductController extends ApiController {
//    /**
//     * 服务对象
//     */
//    @Resource
//    private TProductService tProductService;
//
//    /**
//     * 分页查询所有数据
//     *
//     * @param page 分页对象
//     * @param tProduct 查询实体
//     * @return 所有数据
//     */
//    @GetMapping
//    public R selectAll(Page<TProduct> page, TProduct tProduct) {
//        return success(this.tProductService.page(page, new QueryWrapper<>(tProduct)));
//    }
//
//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("{id}")
//    public R selectOne(@PathVariable Serializable id) {
//        return success(this.tProductService.getById(id));
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param tProduct 实体对象
//     * @return 新增结果
//     */
//    @PostMapping
//    public R insert(@RequestBody TProduct tProduct) {
//        return success(this.tProductService.save(tProduct));
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param tProduct 实体对象
//     * @return 修改结果
//     */
//    @PutMapping
//    public R update(@RequestBody TProduct tProduct) {
//        return success(this.tProductService.updateById(tProduct));
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param idList 主键结合
//     * @return 删除结果
//     */
//    @DeleteMapping
//    public R delete(@RequestParam("idList") List<Long> idList) {
//        return success(this.tProductService.removeByIds(idList));
//    }
//}
//
