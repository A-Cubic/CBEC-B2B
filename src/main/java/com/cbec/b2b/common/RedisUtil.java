package com.cbec.b2b.common;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
	@SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;
	
	@Value("${spring.redis.expireTime}")
	private Long expireTime;

    /**
     * 根据key删除
     * @param key
     */
    @SuppressWarnings("unchecked")
	public void remove(String key){
        if(this.isExistKey(key)){
            redisTemplate.delete(key);
        }
    }

    /**
     * 根据key批量删除
     * @param keys
     */
    public void remove(String... keys){
        for(String key : keys){
            this.remove(key);
        }
    }

    /**
     * 根据key判断是否存在对应的value
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean isExistKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    @SuppressWarnings("rawtypes")
	public Object get(String key){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
    
    /**
     * 根据key获取过期时间
     * @param key
     * @return
     */
	@SuppressWarnings("unchecked")
	public Object getExpire(String key){
        return redisTemplate.getExpire(key);
    }

    /**
     * 写入缓存(配置文件默认过期时间)
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean set(String key, Object value){
        try {
            ValueOperations valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 写入缓存(传入过期时间)
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean set(String key, Object value, Long expireTime){
        try {
            ValueOperations valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
}
