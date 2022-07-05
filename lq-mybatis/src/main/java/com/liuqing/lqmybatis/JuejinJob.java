package com.liuqing.lqmybatis;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @author liuqing01
 * @version 1.0
 * @description TODO
 * @date 2021/11/8 11:07
 */
@Slf4j
@Component
@EnableScheduling   // 2.开启定时任务
public class JuejinJob {

    @Resource
    private RedisUtil redisUtil;

    Gson gson = new Gson();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String jobKey = "juejing:job_key";

    @Scheduled(cron = "0 0 8 * * ? ")
//    @Scheduled(cron = "56 23 10 09 11 ?")
    public void configureTasks() throws IOException {
        int random = getRandom();
        ThreadUtil.sleep((long) random);
        log.info("随机睡眠时间" + (long) random);
        log.info("------------------------------------执行掘金签到功能----------------------------");
        // 执行掘金签到功能
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.juejin.cn/growth_api/v1/check_in?aid=2608&uuid=7036265252035954206&_signature=_02B4Z6wo00901-xmo.gAAIDClKhI.JTSJTPsYqdAAJm2f5SpAWaFT471QNosySNdU.aBwhlLoXxaEfabjj-PK3rMLCQl9f.fzwL97Xz9uh1nX9zaWcaa.Fo3YVr92o8uQqoEQiw6V4izGFg377")
                .method("OPTIONS", body)
                .addHeader("authority", "api.juejin.cn")
                .addHeader("accept", "*/*")
                .addHeader("accept-language", "zh-CN,zh;q=0.9")
                .addHeader("access-control-request-headers", "content-type")
                .addHeader("access-control-request-method", "POST")
                .addHeader("origin", "https://juejin.cn")
                .addHeader("referer", "https://juejin.cn/")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-site", "same-site")
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.0.0 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();
        log.info("执行结果",response.body().string());
    }


    private int getRandom(){
        Random random = new Random();
        List<Integer> list = new ArrayList<>(1000);
        while (true){
            int nextInt = random.nextInt(30);
            if (nextInt > 0){
                return nextInt;
            }
        }
    }


}
