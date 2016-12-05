package com.qiyi.sns.dataservcie;

import static com.qiyi.sns.dataservcie.dao.ExpiryCacheType.PERMANENT;
import static com.qiyi.sns.dataservcie.dao.ExpiryCacheType.TEN_MIN;

import org.apache.commons.lang3.ClassUtils;

import com.google.common.collect.ImmutableMap;
import com.qiyi.sns.dataservcie.dao.ExpiryCacheType;

/**
 * Created by wangjunfei on 12/2/16.
 * 缓存策略:
 * <ul>
 * <li>classKey--->PERMANENT</li>
 * <li>classKey-->expiryType</li>
 * </ul>
 *
 *
 */
public class DefaultCachePolicy implements CachePolicy {
    private static final ImmutableMap classes2CacheType = ImmutableMap.of(PermanentStringKey.class, PERMANENT
            , TenMinStringKey.class, TEN_MIN);

    @Override
    public boolean allowLocal(Class classT) {
        return classes2CacheType.containsKey(classT);
    }

    @Override
    public ExpiryCacheType cacheTypeOf(Class classType) {
        for (Class cls : ClassUtils.getAllInterfaces(classType)) {
            if (classes2CacheType.containsKey(cls)) {
                return (ExpiryCacheType) classes2CacheType.get(classType);
            }
        }

        return PERMANENT;

    }

}
