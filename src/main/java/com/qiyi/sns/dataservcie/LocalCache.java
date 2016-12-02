package com.qiyi.sns.dataservcie;

import javax.swing.text.html.Option;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Weigher;

/**
 * Created by wangjunfei on 12/2/16.
 *
 */
public class LocalCache {

    private Cache cache = null;
    private CachePolicy policy = null;

    /**
     *
     * @param maximumWeight
     */
    public LocalCache(long maximumWeight) {
        cache = CacheBuilder.newBuilder()
                            .maximumWeight(maximumWeight)
                            .weigher(new Weigher<CacheKey, CacheValue>() {
                                public int weigh(CacheKey k, CacheValue v) {
                                    return v.getWeight();
                                }
                            }).build();
    }

    public <K extends CacheKey, V extends CacheValue> void put(K k, V v) {
        cache.put(k, v);
    }

    public <K extends CacheKey, V extends CacheValue> Optional<V> get(K k, Class<V> vClass) {
        Object value = cache.getIfPresent(k);
        if (value != null && value.getClass().equals(vClass)) { return Optional.of((V) value); }
        return Optional.absent();

    }

    public <K extends CacheKey> Optional<Object> get(K k) {
        return Optional.fromNullable(cache.getIfPresent(k));
    }

    public <K extends CacheKey> void delete(K k) {
        cache.invalidate(k);
    }

    public void delete(String k) {
        cache.invalidate(k);
    }

    @VisibleForTesting
    public Cache getUnderlyingCache() {
        return cache;
    }
}
