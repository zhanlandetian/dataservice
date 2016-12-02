package com.qiyi.sns.dataservcie.dao;

import com.qiyi.sns.dataservcie.QueueName;

/**
 * Created by wangjunfei on 12/1/16.
 */
public class DefaultMQService implements MQService {
    @Override
    public void enQueue(QueueName name, String Content) throws Exception {
//        QxQueueFactory
//                .getActiveMQIns(name.value())
//                .send(Content);

    }
}
