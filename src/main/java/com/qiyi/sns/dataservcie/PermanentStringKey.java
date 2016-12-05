package com.qiyi.sns.dataservcie;

import java.util.Objects;

/**
 * Created by wangjunfei on 12/2/16.
 */
public class PermanentStringKey implements CacheKey {
    private final String identity;
    private final String key;

    public PermanentStringKey(String identity, String key) {
        this.key = key;
        this.identity = identity;
    }

    public static PermanentStringKey of(String identity, String key) {
        return new PermanentStringKey(identity, key);
    }

    public static PermanentStringKey of(String key) {
        return of("common", key);
    }

    @Override
    public String typeIdentity() {
        return identity;
    }

    @Override
    public int hashCode() {return Objects.hash(identity, key);}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        final PermanentStringKey other = (PermanentStringKey) obj;
        return Objects.equals(this.identity, other.identity)
               && Objects.equals(this.key, other.key);
    }
}
