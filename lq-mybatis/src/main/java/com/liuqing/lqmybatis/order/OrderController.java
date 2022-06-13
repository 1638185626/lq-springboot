package com.liuqing.lqmybatis.order;

import com.liuqing.lqmybatis.log.LogRecord;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuqing01
 * @version 1.0
 * @description TODO
 * @date 2021/11/9 17:28
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @LogRecord(content = "修改了订单的配送地址：从“#oldAddress”, 修改到“#request.address”",
            bizNo="#request.deliveryOrderNo")
    public void modifyAddress(UpdateDeliveryRequest request, String oldAddress){
        // 更新派送信息 电话，收件人、地址
        // doUpdate(request);
    }
}
