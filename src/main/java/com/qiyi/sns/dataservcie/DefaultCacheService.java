package com.qiyi.sns.dataservcie;

import static com.qiyi.sns.dataservcie.dao.ExpiryCacheType.PERMANENT;

import org.joda.time.Duration;

import com.qiyi.sns.dataservcie.dao.ExpiryCacheType;

/**
 * Created by wangjunfei on 12/2/16.
 */
public class DefaultCacheService implements CacheService {
    private final CachePolicy policy;
    private final LocalCache localCache;

    public DefaultCacheService(CachePolicy policy, LocalCache localCache) {
        this.policy = policy;
        this.localCache = localCache;
    }

    @Override
    public <K extends CacheKey, V extends CacheValue> void set(K key, V value) {

    }

    @Override
    public <K extends CacheKey> void delete(K k) {

    }

//    @Override
//    public Object get(Class<?> clazz, KVStoreKey key) throws Exception {
//        return null;
//    }
//
//    @Override
//    public KVStoreValueBean getWithLogic(Class<?> clazz, KVStoreKey key) {
//        return null;
//    }
//
//    @Override
//    public KVStoreValueBean getWithLocalCache(Class<?> clazz, KVStoreKey key, LocalCache localCache,
//                                              long localCacheExpiry) throws Exception {
//        return null;
//    }
//
//    @Override
//    public KVStoreValueBean getWithLogic(Class<?> clazz, KVStoreKey key, boolean bUseLocalCache)
//            throws Exception {
//        return null;
//    }
//
//    @Override
//    public Map<KVStoreKey, KVStoreValueBean> getMutiWithLogic(Class<?> clazz, List<KVStoreKey> list)
//            throws Exception {
//        return null;
//    }
//
//    @Override
//    public Map<KVStoreKey, KVStoreValueBean> getMutiWithLogic(Class<?> clazz, List<KVStoreKey> list,
//                                                              boolean reportHitRate) throws Exception {
//        return null;
//    }
//
//    @Override
//    public Map<KVStoreKey, KVStoreValueBean> getMultiWithLocalCache(Class<?> clazz, List<KVStoreKey> keys,
//                                                                    LocalCache localCache,
//                                                                    long localCacheExpiry) throws Exception {
//        return null;
//    }
//
//    @Override
//    public Map<KVStoreKey, Object> getMultiWithLocalCacheNotLogic(Class<?> clazz, List<KVStoreKey> keys,
//                                                                  LocalCache localCache, long localCacheExpiry)
//            throws Exception {
//        return null;
//    }
//
//    @Override
//    public Map<KVStoreKey, KVStoreValueBean> getMuti(Class<?> clazz, List<KVStoreKey> list) throws Exception {
//        return null;
//    }
}
