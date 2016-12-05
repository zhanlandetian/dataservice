package com.qiyi.sns.dataservcie.dao;

import org.joda.time.Duration;

/**
 * Created by wangjunfei on 12/3/16.
 * 永久型cache需要实现cacheValue interface以便计算cache占用大小
 * 过期型cache的类型,需要定义过期时间和总条目数量
 * 初始化设置:永久型存储5G,过期型最大10000条
 */
public enum ExpiryCacheType {
    PERMANENT(Duration.ZERO, 5*1024),TEN_MIN(Duration.standardMinutes(10),10000);
    private Duration expiry;
    private long maximumCount;

    ExpiryCacheType(Duration expiry, long maximumCount) {
        this.maximumCount = maximumCount;
        this.expiry = expiry;
    }

    public Duration getExpiry() {
        return this.expiry;
    }

    public long maximumCount() {
        return this.maximumCount;

    }

}
