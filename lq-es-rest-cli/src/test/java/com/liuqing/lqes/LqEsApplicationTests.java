//package com.liuqing.lqes;
//
//import com.alibaba.fastjson.JSON;
//import com.liuqing.lqes.config.EsHighLevalConfigure;
//import com.liuqing.lqes.config.GulimallElasticsearchConfig;
//import com.liuqing.lqes.model.Accout;
//import com.liuqing.lqes.model.User;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.aggregations.AggregationBuilders;
//import org.elasticsearch.search.aggregations.Aggregations;
//import org.elasticsearch.search.aggregations.bucket.terms.Terms;
//import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.Avg;
//import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//@Slf4j
//@SpringBootTest
//class LqEsApplicationTests {
//
//
//
//    @Autowired
//    private RestHighLevelClient client;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Test
//    public void contextLoads() {
//        // 爬取爱奇艺视频
//
//
//    }
//
//    /**
//     * 创建索引
//     * @throws IOException
//     */
//    @Test
//    void createIndex() throws IOException {
//        CreateIndexRequest request = new CreateIndexRequest("users");
//        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
//        boolean acknowledged = createIndexResponse.isAcknowledged();
//        log.info("索引操作===>"+acknowledged);
//
//    }
//
//
//    /**
//     * 添加或者更新
//     * @throws IOException
//     */
//    @Test
//    public void indexData() throws IOException {
//        IndexRequest indexRequest = new IndexRequest("users");
//        User user = new User();
//        user.setAge(19);
//        user.setGender("男");
//        user.setUserName("张三");
//        String jsonString = JSON.toJSONString(user);
//        indexRequest.source(jsonString, XContentType.JSON);
//
//        // 执行操作
//        IndexResponse index = client.index(indexRequest, EsHighLevalConfigure.COMMON_OPTIONS);
//
//        // 提取有用的响应数据
//        System.out.println(index);
//    }
//
//
//    @Test
//    public void searchTest() throws IOException {
//        // 1、创建检索请求
//        SearchRequest searchRequest = new SearchRequest();
//        // 指定索引
//        searchRequest.indices("bank");
//        // 指定 DSL，检索条件
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//        sourceBuilder.query(QueryBuilders.matchQuery("address", "mill"));
//
//        //1、2 按照年龄值分布进行聚合
//        TermsAggregationBuilder aggAvg = AggregationBuilders.terms("ageAgg").field("age").size(10);
//        sourceBuilder.aggregation(aggAvg);
//
//        //1、3 计算平均薪资
//        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
//        sourceBuilder.aggregation(balanceAvg);
//
//
//        System.out.println("检索条件" + sourceBuilder.toString());
//
//        searchRequest.source(sourceBuilder);
//
//        // 2、执行检索
//        SearchResponse searchResponse = client.search(searchRequest);
//
//        // 3、分析结果
//        System.out.println(searchResponse.toString());
//
//        // 4、拿到命中得结果
//        SearchHits hits = searchResponse.getHits();
//        // 5、搜索请求的匹配
//        SearchHit[] searchHits = hits.getHits();
//        // 6、进行遍历
//        for (SearchHit hit : searchHits) {
//            // 7、拿到完整结果字符串
//            String sourceAsString = hit.getSourceAsString();
//            // 8、转换成实体类
//            Accout accout = JSON.parseObject(sourceAsString, Accout.class);
//            System.out.println("account:" + accout );
//        }
//
//        // 9、拿到聚合
//        Aggregations aggregations = searchResponse.getAggregations();
////        for (Aggregation aggregation : aggregations) {
////
////        }
//        // 10、通过先前名字拿到对应聚合
//        Terms ageAgg1 = aggregations.get("ageAgg");
//        for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
//            // 11、拿到结果
//            String keyAsString = bucket.getKeyAsString();
//            System.out.println("年龄:" + keyAsString);
//            long docCount = bucket.getDocCount();
//            System.out.println("个数：" + docCount);
//        }
//        Avg balanceAvg1 = aggregations.get("balanceAvg");
//        System.out.println("平均薪资：" + balanceAvg1.getValue());
//        System.out.println(searchResponse.toString());
//    }
//
//}
