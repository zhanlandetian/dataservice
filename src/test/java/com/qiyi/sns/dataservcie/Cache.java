package com.qiyi.sns.dataservcie;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Test;

import com.google.common.base.Optional;

/**
 * Created by wangjunfei on 12/2/16.
 */
public class Cache {

    class Key1 implements CacheKey {

        private final int member;

        @Override
        public String typeIdentity() {
            return "Key1_kind";
        }

        public Key1(int member) {
            this.member = member;
        }

        @Override
        public String toString() {
            return "key1_" + member;
        }

        @Override
        public int hashCode() {return Objects.hash(member);}

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {return true;}
            if (obj == null || getClass() != obj.getClass()) {return false;}
            final Key1 other = (Key1) obj;
            return Objects.equals(this.member, other.member);
        }
    }

    /**
     * Key3 type like Key1 type,has same private member, and same hashcode ,but not equals
     */
    class Key3 implements CacheKey {

        private final int member;

        @Override
        public String typeIdentity() {
            return "Key1_kind";
        }

        public Key3(int member) {
            this.member = member;
        }

        @Override
        public String toString() {
            return "key1_" + member;
        }

        @Override
        public int hashCode() {return Objects.hash(member);}

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {return true;}
            if (obj == null || getClass() != obj.getClass()) {return false;}
            final Key3 other = (Key3) obj;
            return Objects.equals(this.member, other.member);
        }
    }

    class Value1 implements CacheValue {

        private final String member;

        @Override
        public int getWeight() {
            return 100;
        }

        public Value1(String member) {
            this.member = member;
        }
    }

    class Key2 implements CacheKey {

        private final String member;

        @Override
        public String typeIdentity() {
            return "key2_kind";
        }

        public Key2(String member) {
            this.member = member;
        }

        @Override
        public String toString() {
            return "key2_" + member;
        }

        @Override
        public int hashCode() {return Objects.hash(member);}

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {return true;}
            if (obj == null || getClass() != obj.getClass()) {return false;}
            final Key2 other = (Key2) obj;
            return Objects.equals(this.member, other.member);
        }
    }

    class Value2 implements CacheValue {
        private final int member;

        @Override
        public int getWeight() {
            return 200;
        }

        public Value2(int member) {
            this.member = member;
        }

    }

    @Test
    public void localCache() {
        Key1 key1 = new Key1(1);
        Value1 value1 = new Value1("1");

        Key2 key2 = new Key2("1");
        Value2 value2 = new Value2(2);

        //maximumWeight 1000byte
        LocalCache cache = new LocalCache(2000);

        //basic set , get and delete
        cache.put(key1, value1);
        cache.put(key2, value2);
        assertTrue(cache.get(key1, Value1.class).isPresent());
        assertThat(cache.get(key1, Value1.class).get(), is(value1));

        //object equals and hashcode equals will get the same value (CacheKey must implements hashcode and equals method)
        assertThat(cache.get(new Key1(1), Value1.class).get(), is(value1));

        //object not equals and hashcode equals will not be treated as same key
        assertFalse(cache.get(new Key3(1), Value1.class).isPresent());

        cache.delete(key1);
        assertFalse(cache.get(new Key1(1),Value1.class).isPresent());
        assertTrue(cache.get(new Key2("1"),Value2.class).isPresent());


        //String key test
        cache.put(CacheKeyString.of("key1"),value1);
        assertTrue(cache.get(CacheKeyString.of("key1"),Value1.class).isPresent());
        assertEquals(cache.get(CacheKeyString.of("key1"),Value1.class).get(),value1);

        Optional<Object> rawObject=cache.get(CacheKeyString.of("key1"));
        assertEquals(rawObject.get(),value1);
        assertTrue(rawObject.get() instanceof Value1);



    }

}
