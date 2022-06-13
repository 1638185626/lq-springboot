package com.liuqing.lqredis.controller;

import com.liuqing.lqredis.lock.DistributedLocker;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.TimeUnit;

/**
 * @author liuqing01
 * @version 1.0
 * @description: TODO
 * @date 2021/10/22 15:23
 */
@Slf4j
public class LockController {

    private String key = "redis:key";


    @Autowired
    private DistributedLocker distributedLocker;


    @GetMapping("/test")
    public String lock(){
        RLock lock = distributedLocker.lock(key);
        boolean getLock = false;
        try {
            if (getLock = lock.tryLock(0, 5, TimeUnit.SECONDS)) {
                int hour = 2;

            } else {
                log.info("Redisson分布式锁没有获取到锁:{},ThreadName :{}", key, Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            log.error("Redisson 获取分布式锁异常",e);
        }finally {
            if (!getLock) {
                return "";
            }
            lock.unlock();
            log.info("Redisson分布式锁释放锁:{},ThreadName :{}",key, Thread.currentThread().getName());
        }
        return "";
    }
}
