package com.qiyi.sns.dataservcie;

import org.joda.time.Duration;

/**
 * Created by wangjunfei on 12/2/16.
 */
public class DefaultCacheService implements CacheService {
    @Override
    public <K, V> boolean set(K key, V value) {
        return false;
    }

    @Override
    public <K, V> boolean set(K key, V value, Duration expiry) {
        return false;
    }

    @Override
    public <K, V> V delete(K k) {
        return null;
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
