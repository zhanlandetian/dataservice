package com.qiyi.sns.dataservcie;

import java.util.Objects;

/**
 * Created by wangjunfei on 12/2/16.
 */
public class CacheValueString implements CacheValue {
    private final String value;

    public CacheValueString(String value) {
        this.value = value;
    }

    public static CacheValueString of(String value) {
        return new CacheValueString(value);
    }

    @Override
    public int getWeight() {
        return value.length();
    }

    @Override
    public int hashCode() {return Objects.hash(value);}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        final CacheValueString other = (CacheValueString) obj;
        return Objects.equals(this.value, other.value);
    }
}
