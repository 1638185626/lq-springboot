package com.example.lqmybatis;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
    public void configureTasks() {
        log.info("------------------------------------执行掘金签到功能----------------------------");
        // 执行掘金签到功能
        String jobListStr = redisUtil.get(jobKey);
        JSONArray jsonArray = JSONArray.parseArray(jobListStr);
        jsonArray.forEach(data -> {
            String cookie = (String) data;
            String url = "https://api.juejin.cn/growth_api/v1/check_in";
            OkHttpClient okHttpClient = new OkHttpClient();
            Map map = new HashMap<>();
            String param= gson.toJson(map);
            try {
                final Request request = new Request.Builder()
                        .url(url)
                        .addHeader("cookie",cookie)
                        .post(RequestBody.create(JSON, param))
                        .build();
                Response execute = okHttpClient.newCall(request).execute();
                System.out.println(execute.body().string());
                log.info("------------------------------------执行结果-成功---------------------------");
            }catch (Exception e){
                log.error("签到失败！ {}",e.getMessage());
            }
        });
    }


}
