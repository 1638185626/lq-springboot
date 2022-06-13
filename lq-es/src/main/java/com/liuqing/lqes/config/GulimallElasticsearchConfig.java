package com.liuqing.lqes.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: GulimallElasticsearchConfig
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/13
 **/
@Configuration
public class GulimallElasticsearchConfig {


    public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        //        builder.addHeader("Authorization", "Bearer " + TOKEN);
        //        builder.setHttpAsyncResponseConsumerFactory(
        //                new HttpAsyncResponseConsumerFactory
        //                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }



    @Bean
    public RestHighLevelClient esRestClient() {
        RestClientBuilder builder = null;
        builder = RestClient.builder(new HttpHost("116.63.37.0", 9200, "http"));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        //        RestHighLevelClient client = new RestHighLevelClient(
        //                RestClient.builder(
        //                        new HttpHost("localhost", 9200, "http"),
        //                        new HttpHost("localhost", 9201, "http")));
        return client;
    }

}
