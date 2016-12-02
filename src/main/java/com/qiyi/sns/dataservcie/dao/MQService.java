package com.qiyi.sns.dataservcie.dao;

import com.qiyi.sns.dataservcie.QueueName;

/**
 * Created by wangjunfei on 12/1/16.
 */
public interface MQService {
   void enQueue(QueueName name, String Content) throws Exception;
}
