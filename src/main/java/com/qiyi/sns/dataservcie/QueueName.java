package com.qiyi.sns.dataservcie;

/**
 * Created by wangjunfei on 12/1/16.
 */
public enum QueueName {
    QUEUE_COMMENT_LIKE("comment_like");

    QueueName(String name) {
        this.name = name;

    }

    public String value() {
        return this.name;
    }

    private final String name;

}
