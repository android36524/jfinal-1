package com.xiaoan.wlt.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.redis.Redis;


/**
 * shiro 用户缓存redis
 * @author liangjiahong
 * @date 2016年10月28日
 * @param <K>
 * @param <V>
 */
public class ShiroRedisCache<K, V> implements Cache<K, V> {

	private final static Log LOG = Log.getLog(ShiroRedisCache.class);  
	private String CACHE_PREFIX;  
	private static com.jfinal.plugin.redis.Cache cache = Redis.use("session");
	
	public ShiroRedisCache(String cacheName) {
		super();
//		this.cacheName = cacheName;
		CACHE_PREFIX = "session-"; 
	}
    
    @Override  
    public V get(K key) throws CacheException {  
		V value = ((V)getCache().get(keyToString(key)));  
        if(LOG.isDebugEnabled()){  
            LOG.debug("get the entity json is " + key + " : " + value);  
        }  
        return value;  
    }  
  
    @Override  
    public V put(K key, V value) throws CacheException {  
        if(LOG.isDebugEnabled()){  
            LOG.debug("begin save the "+ key + " : " + value+" to memcache");  
        }  
        getCache().set(keyToString(key), value);  
        return value;  
    }  
  
    @Override  
    public V remove(K key) throws CacheException {  
        if(LOG.isDebugEnabled()){  
            LOG.debug("begin remove the "+key+" from memcache");  
        }  
        V value = get(key);  
        getCache().del(keyToString(key));  
        return value;  
    }  
  
    @Override  
    public void clear() throws CacheException {  
        for(Iterator<K> keys =keys().iterator();keys.hasNext();){  
            K key = keys.next();  
            remove(key);  
        }  
    }  
  
    @Override  
    public int size() {  
        return keys().size();  
    }  
  
    @Override  
    public Set<K> keys() {  
        Set<String> keys = new HashSet<String>();  
        for(String key : getCache().keys("*")){  
            if(StringUtils.startsWith(key, CACHE_PREFIX)){  
                keys.add(key);  
            }  
        }  
        return (Set<K>)keys ;  
    }  
  
    @Override  
    public Collection<V> values() {  
        List<V> values = new ArrayList<V>();  
        for(Iterator<K> keys =keys().iterator();keys.hasNext();){  
            K key = keys.next();  
            V value = getValue(key);  
            values.add(value);  
        }  
        return values;  
    }  
      
    private V getValue(K key) throws CacheException {  
        V value = ((V)getCache().get(String.valueOf(key)));  
        if(LOG.isDebugEnabled()){  
            LOG.debug("get the entity json is " + key + " : " + value);  
        }  
        return value;  
    }  
      
    private String keyToString(K key){  
        String k = String.valueOf(key);  
        if(StringUtils.startsWith(k, CACHE_PREFIX)){  
            return k;  
        }  
        return CACHE_PREFIX+k;  
    }

	public com.jfinal.plugin.redis.Cache getCache() {
		return  Redis.use("session");
	}

}
