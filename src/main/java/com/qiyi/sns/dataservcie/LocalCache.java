package com.qiyi.sns.dataservcie;

import static com.qiyi.sns.dataservcie.dao.ExpiryCacheType.PERMANENT;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import com.qiyi.sns.dataservcie.dao.ExpiryCacheType;

/**
 * Created by wangjunfei on 12/2/16.
 * 目前分两种cache:
 * <ul>
 *  <li>永久存储型cache,此类cache需设置一个总体占用空间大小</li>
 *  <li>过期型cache,需要设置过期时间和总体数目大小</li>
 * </ul>
 */
public class LocalCache {

    private final LoadingCache<ExpiryCacheType, Cache> cacheMap;
    private final int CACHE_MAP_SIZE = 100;
    private final CachePolicy policy;

    public LocalCache(CachePolicy policy) {
        cacheMap = CacheBuilder.newBuilder()
                               .maximumSize(CACHE_MAP_SIZE)
                               .build(new CacheLoader<ExpiryCacheType, Cache>() {
                                   @Override
                                   public Cache load(ExpiryCacheType key) throws Exception {
                                       return createCache(key);
                                   }
                               });
        this.policy = policy;
    }

    /**
     * put k,v into cache
     * 只有你确定自己是atomi语义时再使用这个方法,比如application启动时,单线程执行的时候
     * 注意: 这个方法没有get(k,Callable)那种atomic的语义(即,如果不存在的话,再计算后补充缓存)
     *  不要用get检查然后再put这种方法填充缓存(这种没有atomic语义,有可能存在线程安全)
     * @param k
     * @param v
     * @param <K>
     * @param <V>
     */
    public <K extends CacheKey, V extends CacheValue> void put(K k, V v) {
        try {
            cacheMap.get(policy.cacheTypeOf(k.getClass())).put(k, v);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存k的值
     * @param k
     * @param vClass
     * @param <K>
     * @param <V>
     * @return
     */
    public <K extends CacheKey, V extends CacheValue> Optional<V> get(K k, Class<V> vClass) {
        Object value = cacheMap.getIfPresent(policy.cacheTypeOf(k.getClass())).getIfPresent(k);
        if (value != null && value.getClass().equals(vClass)) { return Optional.of((V) value); }
        return Optional.absent();

    }

    public <K extends CacheKey, V extends CacheValue> Optional<V> get(K k, Callable<V> callable,
                                                                      Class<V> vClass) {
        try {
            Object value = cacheMap.getIfPresent(policy.cacheTypeOf(k.getClass())).get(k, callable);
            if (value != null && value.getClass().equals(vClass)) { return Optional.of((V) value); }

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.absent();

    }

    public <K extends CacheKey> void delete(K k) {
        cacheMap.getIfPresent(policy.cacheTypeOf(k.getClass())).invalidate(k);
    }

    public void delete(String k) {
        cacheMap.getIfPresent(policy.cacheTypeOf(k.getClass())).invalidate(k);
    }

    private Cache createCache(ExpiryCacheType cacheType) {

        if (cacheType.equals(PERMANENT))

        {
            return CacheBuilder.newBuilder()
                               .maximumWeight(cacheType.maximumCount())
                               .weigher(new Weigher<CacheKey, CacheValue>() {
                                   public int weigh(CacheKey k, CacheValue v) {
                                       return v.getWeight();
                                   }
                               }).build();
        } else {
            return CacheBuilder.newBuilder()
                               .maximumSize(cacheType.maximumCount())
                               .expireAfterAccess(cacheType.getExpiry().getStandardMinutes(), TimeUnit.SECONDS)
                               .recordStats()
                               .build();

        }

    }

}
