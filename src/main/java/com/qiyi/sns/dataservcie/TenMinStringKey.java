package com.qiyi.sns.dataservcie;

import java.util.Objects;

/**
 * Created by wangjunfei on 12/3/16.
 */
public class TenMinStringKey implements CacheKey {
    private  final String key;
    private final String identity;

    public TenMinStringKey(String identity,String key){
        this.key = key;
        this.identity = identity;

    }
    public static TenMinStringKey of(String identity,String key){
        return new TenMinStringKey(identity, key);
    }
    public static TenMinStringKey of(String key){
        return new TenMinStringKey("ten_minutes", key);
    }
    @Override
    public String typeIdentity() {
        return this.identity;
    }

    @Override
    public int hashCode() {return Objects.hash(key, identity);}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        final TenMinStringKey other = (TenMinStringKey) obj;
        return Objects.equals(this.key, other.key)
               && Objects.equals(this.identity, other.identity);
    }
}
