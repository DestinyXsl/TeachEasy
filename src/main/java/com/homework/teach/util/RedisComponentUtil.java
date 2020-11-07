package com.homework.teach.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisComponentUtil {

    private static Logger logger  = LoggerFactory.getLogger(RedisComponentUtil.class);
	@Resource  
    private RedisTemplate<String, Object> redisTemplate;
	
	public void set(String key, Object value){  
        HashOperations<String,String, Object> ops = redisTemplate.opsForHash();
        ops.put(key,"data", value);
    }  
	
	public void set(String key, Object value, long timeOut){
		 ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		 ops.set(key, value, timeOut, TimeUnit.MILLISECONDS);
	}
      
    public Object get(String key){
        Object result = "";
        Set<Object> keys = redisTemplate.opsForHash().keys(key);
        logger.info("size-----------:"+keys.size());
        for (Object k :keys){
            if(k.toString().equals("data")) {
                result = redisTemplate.opsForHash().get(key, k);
            }
        }
        return result;
    }  

    public void delete(String key){  
    	redisTemplate.delete(key);  
    }  
    
    public void delete(List<String> keys){
    	redisTemplate.delete(keys);
    }
}
