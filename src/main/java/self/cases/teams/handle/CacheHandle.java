package self.cases.teams.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import self.cases.teams.entity.Users;

/**
 * 缓存处理
 */
@Component("cacheHandle")
public class CacheHandle {

    @Autowired
    private CacheManager cacheManager;

    private final String USER_KEY = "users";

    /**
     * 获取用户缓存对象
     * @return
     */
    public Cache getUserCache(){

        Cache cache = cacheManager.getCache(USER_KEY);

        return  cache;
    }

    /**
     * 存储登录用户信息
     * @param key 缓存用户 token
     * @param val 登录用户 信息
     */
    public void addUserCache(String key, Object val) {

        Cache cache = getUserCache();

        cache.put(key, val);
    }

    /**
     * 移除缓存登录用户信息
     * @param key 缓存用户 token
     */
    public void removeUserCache(String key){

        Cache cache = getUserCache();

        cache.evict(key);
    }

    /**
     * 获取缓存的登录用户信息
     * @param key 缓存用户 token
     * @return
     */
    public String getUserInfoCache(String key){

        Cache cache = getUserCache();

        String userId = cache.get(key, String.class);

        return userId;
    }
}
