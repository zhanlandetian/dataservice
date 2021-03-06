package com.qiyi.sns.dataservcie;

import com.qiyi.sns.dataservcie.dao.ExpiryCacheType;

/**
 * Created by wangjunfei on 12/2/16.
 * 1.根据class 类型做缓存策略,符合特定policy的class会运行使用本地缓存,本地缓存去不到后,自动去远端Couchbase取缓存
 * 2.缓存默认的策略都是LRU的方式,即限定本地和远端缓存总大小
 * 3.本地缓存固定大小,缓存接近阀值时会自动清理.原则上建议不要过渡依赖本地缓存,因为每次上线导致的jvm重启或虚机重启都会导致缓存失效
 * 4.远端缓存目前没有atomic语义
 *
 */
public interface CacheService {
    <K extends CacheKey, V extends CacheValue> void set(K key, V value);

    <K extends CacheKey> void delete(K k);
}
