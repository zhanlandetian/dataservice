package com.qiyi.sns.dataservcie;

import com.google.common.annotations.VisibleForTesting;

/**
 * Created by wangjunfei on 12/2/16.
 */
public interface CachePolicy {
    <T> boolean allowLocal(T classT);

    <T> int getLocalCacheWeight(T classT);

    @VisibleForTesting
    <T> void addAllowType(Class<T> classT, int weight);
}
