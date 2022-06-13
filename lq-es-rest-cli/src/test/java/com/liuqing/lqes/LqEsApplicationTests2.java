//package com.liuqing.lqes;
//
//import cn.hutool.core.util.IdUtil;
//import com.alibaba.fastjson.JSON;
//import com.liuqing.lqes.config.EsHighLevalConfigure;
//import com.liuqing.lqes.model.Accout;
//import com.liuqing.lqes.model.IntellectualEntity;
//import com.liuqing.lqes.model.User;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.support.WriteRequest;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.BoolQueryBuilder;
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
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@SpringBootTest(classes = LqEsApplication.class)
//class LqEsApplicationTests2 {
//
//    @Autowired
//    private RestHighLevelClient restHighLevelClient;
//
//
//    @Test
//    public void testClient() throws IOException {
//        IntellectualEntity intellectual = new IntellectualEntity();
//        intellectual.setId(IdUtil.getSnowflakeNextId());
//        intellectual.setName("站思维方式");
//        intellectual.setType(0);
//        intellectual.setKeycode("z");
//        intellectual.setOfficeId(IdUtil.getSnowflakeNextId() + "");
//        intellectual.setOfficeName("");
//        intellectual.setTitular("");
//        intellectual.setApplyTime(System.currentTimeMillis());
//        intellectual.setEndTime(System.currentTimeMillis());
//        intellectual.setStatus("up");
//        intellectual.setAgentName("安静了发卡量");
//        insertIntel(intellectual);
//    }
//
//
//    @Test
//    public void updateIntel() throws IOException {
//        IntellectualEntity intellectual = new IntellectualEntity();
//        intellectual.setId(IdUtil.getSnowflakeNextId());
//        intellectual.setName("站思维方式");
//        intellectual.setType(0);
//        intellectual.setKeycode("z");
//        intellectual.setOfficeId(IdUtil.getSnowflakeNextId() + "");
//        intellectual.setOfficeName("");
//        intellectual.setTitular("");
//        intellectual.setApplyTime(System.currentTimeMillis());
//        intellectual.setEndTime(System.currentTimeMillis());
//        intellectual.setStatus("up");
//        intellectual.setAgentName("安静了发卡量");
//        updateIntel(intellectual);
//    }
//
//    @Test
//    public void deleteIntel() throws IOException {
//        IntellectualEntity intellectual = new IntellectualEntity();
//        intellectual.setId(1518093758177579008L);
//        deleteIntel(intellectual);
//    }
//
//
//    public void insertIntel(IntellectualEntity intellectualEntity) throws IOException {
//        //intellectual为索引名
//        IndexRequest indexRequest = new IndexRequest("intellectual")
//                .source(JSON.toJSONString(intellectualEntity), XContentType.JSON)
//                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
//                .id(intellectualEntity.getId()+"");//手动指定es文档的id
//        IndexResponse out = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
//        log.info("状态：{}", out.status());
//    }
//
//
//    public void updateIntel(IntellectualEntity entity) throws IOException {
//        //根据IntellectualEntity的id更新文档
//        UpdateRequest updateRequest = new UpdateRequest("intellectual",  "1518093758177579008");
//        entity.setId(null);
//        byte[] json = JSON.toJSONBytes(entity);
//        updateRequest.doc(json, XContentType.JSON);
//        UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
//        log.info("状态：{}", response.status());
//    }
//
//
//    public void deleteIntel(IntellectualEntity entity) throws IOException {
//        DeleteRequest deleteRequest=new DeleteRequest("intellectual",entity.getId()+"");
//        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
//        log.info("状态：{}", deleteResponse.status());
//    }
//
//    public List<Map<String,Object>> queryByContent(String content, Integer pageCurrent, Date startTimeApply, Date endTimeApply, Date startTimeEnd, Date endTimeEnd ) throws IOException {
//        //空格分割多条件，本搜索支持多搜索词条空格分开，多词条搜索关系用and
//        String[] manyStr = content.split("\\s+");
//        //定义一个list<map>作为返回结果
//        List<Map<String,Object>> list = new LinkedList<>();
//        //首先构造条件构造器
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        if(manyStr.length>1){
//            for (int i=0;i<manyStr.length;i++){
//                BoolQueryBuilder innerBoolQueryBuilder = QueryBuilders.boolQuery();
//                //nestedQuery，嵌套搜索条件
//                innerBoolQueryBuilder.should(QueryBuilders.nestedQuery("annex",QueryBuilders.matchQuery("annex.content", manyStr[i]) , ScoreMode.Max).boost(2));
//                innerBoolQueryBuilder.should(QueryBuilders.nestedQuery("annex",QueryBuilders.matchQuery("annex.simpleContent", manyStr[i]) , ScoreMode.Max).boost(2));
//                innerBoolQueryBuilder.should(QueryBuilders.nestedQuery("applicant",QueryBuilders.matchQuery("applicant.userName", manyStr[i]).prefixLength(2).maxExpansions(4).boost(5) , ScoreMode.Max));
//                innerBoolQueryBuilder.should(QueryBuilders.nestedQuery("applicant",QueryBuilders.matchQuery("applicant.outUsername", manyStr[i]).prefixLength(2).maxExpansions(4).boost(5) , ScoreMode.Max));
//                innerBoolQueryBuilder.should(QueryBuilders.matchQuery("name", manyStr[i]).boost(8));
//                innerBoolQueryBuilder.should(QueryBuilders.termsQuery("officeName", manyStr[i]).boost(100));
//                innerBoolQueryBuilder.should(QueryBuilders.fuzzyQuery("keycode", manyStr[i]).boost(5));
//                innerBoolQueryBuilder.should(QueryBuilders.matchQuery("agentName", manyStr[i]).boost(5));
//                innerBoolQueryBuilder.should(QueryBuilders.termsQuery("status", manyStr[i]).boost(30));
//                //and关系
//                boolQueryBuilder.must(innerBoolQueryBuilder);//
//            }
//        }
//        else {
//            //没有空格的
//            boolQueryBuilder.should(QueryBuilders.nestedQuery("annex",QueryBuilders.matchQuery("annex.content", content) , ScoreMode.Max).boost(2));
//            boolQueryBuilder.should(QueryBuilders.nestedQuery("annex",QueryBuilders.matchQuery("annex.simpleContent", content) , ScoreMode.Max).boost(2));
//            //暂不用嵌套高亮.innerHit(new InnerHitBuilder().setHighlightBuilder(highlightBuilder)
//            boolQueryBuilder.should(QueryBuilders.nestedQuery("applicant",QueryBuilders.matchQuery("applicant.userName", content).prefixLength(2).maxExpansions(4).boost(5) , ScoreMode.Max));
//            boolQueryBuilder.should(QueryBuilders.nestedQuery("applicant",QueryBuilders.matchQuery("applicant.outUsername", content).prefixLength(2).maxExpansions(4).boost(5) , ScoreMode.Max));
//            boolQueryBuilder.should(QueryBuilders.matchQuery("name", content).boost(8));
//            boolQueryBuilder.should(QueryBuilders.termsQuery("officeName", content).boost(100));
//            boolQueryBuilder.should(QueryBuilders.fuzzyQuery("keycode", content).boost(5));
//            boolQueryBuilder.should(QueryBuilders.matchQuery("agentName", content).boost(5));
//            boolQueryBuilder.should(QueryBuilders.termsQuery("status", content).boost(30));
//        }
//        if(startTimeApply!=null){
//            //filter 不参与评分，不会由于搜索时间条件造成搜索评分较高导致排序不准确
//            boolQueryBuilder.filter(QueryBuilders.rangeQuery("applyTime").gte(startTimeApply.getTime()));
//        }
//        if(endTimeApply!=null){
//            boolQueryBuilder.filter(QueryBuilders.rangeQuery("applyTime").lte(endTimeApply.getTime()));
//        }
//        if(startTimeEnd!=null){
//            boolQueryBuilder.filter(QueryBuilders.rangeQuery("endTime").gte(startTimeEnd.getTime()));
//        }
//        if(endTimeEnd!=null){
//            boolQueryBuilder.filter(QueryBuilders.rangeQuery("endTime").lte(endTimeEnd.getTime()));
//        }
//        //新建请求
//        SearchRequest searchRequest = new SearchRequest("intellectual");
//        //新建搜索配置器
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        //搜索配置器 -> 高亮配置器
//        searchSourceBuilder.highlighter(highlightBuilder);
//        //搜索配置器 -> 配置组合条件
//        //解释：minScore为搜索匹配项的最小评分，低于此分数的项不计入搜索结果，评分大小受到 搜索条件的.boost(权值)的影响，
//        //boost值越大，导致计算评分越大，如果搜索结果不满意，可以测试调整boost的值达到比较满意的结果，还有一种方案就是自定义计算评分公式，属于专家级使用方案
//        //分页解释：这种from-size分页当 页数过大的集群 可能导致搜索崩溃（因为搜索结果汇总数据条数过大，需要较大jvm内存，原理我就懒得讲太多，写不下，米娜桑有兴趣自行学习去吧），
//        //解决方案是使用深度分页，当然了，单机单分片的es机器from-size不会导致搜索崩溃
//        searchSourceBuilder
//                .minScore(9)//设置最小评分
//                .query(boolQueryBuilder)//装载搜索条件
//                .from((pageCurrent-1)*10)//起始条数,从0
//                .size(10)//每页展示记录
//        ;
//        //装载搜索配置器
//        searchRequest.source(searchSourceBuilder);
//        //搜索返回结果
//        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//        //可测试搜索的分数，结合调整boot值可以让搜索结果更加 “尽人意”
//        log.info("总条数"+search.getHits().getTotalHits().value);
//        log.info("符合条件的文档最大得分: "+search.getHits().getMaxScore());
//        //遍历搜索结果列表
//        for(SearchHit documentFields : search.getHits().getHits()){
//            //sourceAsMap是不包含高亮的结果，如果搜索不要求高亮，就直接返回结果
//            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
//            //highlightFieldsMap是高亮的结果
//            Map<String, HighlightField> highlightFieldsMap = documentFields.getHighlightFields();
//            //通过getHighLightMap方法将原不高亮的字段结果替换为高亮结果
//            sourceAsMap = changeHighLightMap(sourceAsMap, highlightFieldsMap);
//            //因为es存的时间设置为long时间戳类型，需要转化
//            sourceAsMap.put("applyTime", new Date(Long.parseLong(sourceAsMap.get("applyTime")+"")));
//            if(sourceAsMap.get("endTime")!=null){
//                sourceAsMap.put("endTime",new Date(Long.parseLong(sourceAsMap.get("endTime")+"")));
//            }
//            //打印分值
//            log.info(documentFields.getScore());
//            //存入list
//            list.add(sourceAsMap);
//        }
//        return list;
//    }
//
//
//
//
//}
