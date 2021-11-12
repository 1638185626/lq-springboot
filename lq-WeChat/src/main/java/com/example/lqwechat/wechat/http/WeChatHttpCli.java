package com.example.lqwechat.wechat.http;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author liuqing01
 * @version 1.0
 * @description TODO
 * @date 2021/11/4 14:00
 */
@Slf4j
@Component
public class WeChatHttpCli implements WeChatHttp{

    @Value("${weChat.host}")
    private String weChatHostUrl;
    @Value("${weChat.appId}")
    private String appId;
    @Value("${weChat.secret}")
    private String secret;

    /**
     * 获取token域名
     */
    private static final  String getTokenUrl = "/cgi-bin/token";


    @Resource
    private RestTemplate restTemplate;


    /**
     * 获取token
     *
     * @return 成功返回token，失败返回空
     */
    @Override
    public String getToken(){
        Map<String, String> map = new HashMap<>();
        map.put("appid",appId);
        map.put("secret",secret);
        map.put("grant_type","client_credential");
        JSONObject wxRes = null;
        String accessToken = null;
        for (int i = 0; i < 3; i++) {
           try {
               String url = weChatHostUrl + getTokenUrl +"?"+ buildMap(map);
               wxRes =  restTemplate.getForObject(url, JSONObject.class);
           }catch (Exception e){
               log.error("[微信公众号token获取失败] {}",e.getMessage());
           }
           if (Objects.isNull(wxRes)){
               continue;
           }
            Integer errcode = wxRes.getInteger("errcode");
            if (errcode != null){
                log.error("[微信公众号token获取失败] {}",wxRes);
            }
            accessToken = wxRes.getString("access_token");
            if (null != accessToken){
                // 保存token到redis
                break;
            }
        }
        return accessToken;
    }



    public  static  String buildMap(Map<String, String> map) {
        StringBuffer sb = new StringBuffer();
        if (map.size() > 0) {
            for (String key : map.keySet()) {
                sb.append(key + "=");
                if (StringUtils.isEmpty(map.get(key))) {
                    sb.append("&");
                } else {
                    String value = map.get(key);
                    try {
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    sb.append(value + "&");
                }
            }
        }
        return sb.toString();
    }
}
