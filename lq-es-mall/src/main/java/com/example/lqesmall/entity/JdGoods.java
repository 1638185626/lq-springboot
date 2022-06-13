package com.example.lqesmall.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * (JdGoods)实体类
 *
 * @author liuqing
 * @since 2022-04-08 16:21:33
 */
@Data
@Document(indexName = "jd_goods")
public class JdGoods implements Serializable {
    private static final long serialVersionUID = -11209521446158950L;
    /**
     * 京东商品Id
     */
    @Id
    private String jdId;
    @Field(type = FieldType.Text)
    private String jdGoodsName;
    /**
     * 商品价格
     */
    @Field(type = FieldType.Double)
    private Double jdPrice;




    public static void main(String[] args) {
        int[] nums = new int[]{3,3};
        twoSum(nums,6);
    }


    public static int[] twoSum(int[] nums, int target) {
        int[] tags = new int[2];
        HashMap<Integer, List<Integer>> maps = new HashMap<>();
        for(int i = 0 ; i < nums.length;i++){
            if(maps.containsKey(nums[i])){
                List<Integer> a = maps.get(nums[i]);
                a.add(i);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(i);
                maps.put(nums[i],list);
            }
        }

        for(int i = 0; i < nums.length;i++){
            int e = target - nums[i];
            if(e == nums[i]){
                tags[0] = i;
                tags[1] = i;
                continue;
            }
            List<Integer> a = maps.get(e);
            if(a != null && a.size() >= 2){
                int num1 = a.get(0);
                int num2 = a.get(1);
                if(target - (e + e) == 0){
                    tags[0] = num1;
                    tags[1] = num2;
                    break;
                }
            }
            if(a != null && target - (e + nums[i]) == 0){
                tags[0] = i;
                tags[1] = a.get(0);
                break;
            }
        }
        return tags;
    }


}

