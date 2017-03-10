package com.xiaoan.wlt.utils;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

public class RedisUtils {

	public static Object get(String cacheName, String key, Object data) {
		Cache cache = Redis.use(cacheName);
		if (cache.get(key) == null)
			cache.set(key, data);
		return cache.get(key);
	}
	
	public static void move(String cacheName, String key){
		Cache cache = Redis.use(cacheName);
	}
	
}
