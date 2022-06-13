package com.liuqing.lqwechat.wechat.http;

import java.io.IOException;

/**
 * @author liuqing01
 * @version 1.0
 * @description: http请求接口
 * @date 2021/11/4 12:37
 */
public interface WeChatHttp {

    /**
     * 获取token
     * @return 成功返回token，失败返回空
     */
    String getToken() throws IOException;
}
