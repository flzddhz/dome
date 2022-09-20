package com.redis;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisTest {
    public static void main(String[] args) {
        try{
            Jedis jedis = new Jedis("127.0.0.1");
//        jedis.auth("");   如果设置了密码就要
            System.out.println("jedis:" + jedis.ping());
            System.out.println("连接成功！");

//            //操作字符串
//            jedis.set("str1","测试1");
//            jedis.set("str2","测试1");
//            jedis.set("str1","测试2");
//            jedis.del("str1");
//            System.out.println(jedis.get("s"));
//            System.out.println(jedis.get("str2"));

            //操作list数组    先进后出
            jedis.lpush("list","测试1");
            jedis.lpush("list","测试2");
            jedis.lpush("list","测试3");
            List<String> list1 = jedis.lrange("list",0,2);
            for(String str1:list1){
                System.out.println(str1);
            }
            jedis.del("list");


//            // 获取所有的数据数据并输出
//            Set<String> keys = jedis.keys("*");
//            Iterator<String> it=keys.iterator() ;
//            while(it.hasNext()){
//                String key = it.next();
//                System.out.println(key);
//            }






        }catch (Exception e){
            System.out.println("redis错误！" + e.getMessage());
            e.printStackTrace();
        }

    }
}
