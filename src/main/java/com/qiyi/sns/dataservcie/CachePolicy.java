package com.qiyi.sns.dataservcie;

import com.google.common.annotations.VisibleForTesting;
import com.qiyi.sns.dataservcie.dao.ExpiryCacheType;

/**
 * Created by wangjunfei on 12/2/16.
 */
public interface CachePolicy {
    boolean allowLocal(Class classType);

    /**
     * Cache过期政策
     * @param classType
     * @return 默认的都是永久类型的cache
     */
    ExpiryCacheType cacheTypeOf(Class classType);

}
