package com.synway.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

//@RestController
public class TestRedis {

/*    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redisson;
//    @RequestMapping("/testRedis")
    public void testRedis(){
        RLock rLock = redisson.getLock("lock");
        rLock.lock(30, TimeUnit.SECONDS);
        try {
            int count = Integer.parseInt(stringRedisTemplate.opsForValue().get("stores"));
            if(count>0){
                count--;
                stringRedisTemplate.opsForValue().set("stores",String.valueOf(count));
                System.out.println("减库存完毕,剩余:" +count);
            }
            System.out.println(count);
         }finally {
            rLock.unlock();
        }
    }*/

}
