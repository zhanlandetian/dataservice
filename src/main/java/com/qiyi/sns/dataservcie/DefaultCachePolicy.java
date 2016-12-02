package com.qiyi.sns.dataservcie;

import java.util.HashMap;
import java.util.Map;

import com.google.common.annotations.VisibleForTesting;

/**
 * Created by wangjunfei on 12/2/16.
 */
public class DefaultCachePolicy implements CachePolicy {
    private static final Map<Class<?>, Integer> classes2Weight = new HashMap<>();

    static {
        classes2Weight.put(String.class, 1);
    }

    @Override
    public <T> boolean allowLocal(T classT) {
        return classes2Weight.containsKey(classT);
    }

    @Override
    public <T> int getLocalCacheWeight(T classT) {
        return classes2Weight.containsKey(classT) ? 0 : classes2Weight.get(classT);
    }

    @Override
    @VisibleForTesting
    public <T> void addAllowType(Class<T> classT,int weight) {
        classes2Weight.put(classT, weight);
    }
}
