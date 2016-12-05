package com.qiyi.sns.dataservcie;

import com.qiyi.sns.dataservcie.dao.ExpiryCacheType;

/**
 * Created by wangjunfei on 12/2/16.
 * cache key必须要实现该接口,或者是String类型的key
 * 注意: 请实现hashcode和equals两个方法,以确保,只有两个object Key是equals且同时hashcode是相同时,取出的value才是一样的
 */
public interface CacheKey {
    String typeIdentity();
}
