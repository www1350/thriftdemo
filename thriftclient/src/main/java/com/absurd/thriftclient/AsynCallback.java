package com.absurd.thriftclient;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2016/9/28.
 */
public class AsynCallback implements AsyncMethodCallback<Hello.AsyncClient.helloString_call> {
    private CountDownLatch latch;
    public AsynCallback(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void onComplete(Hello.AsyncClient.helloString_call helloString_call) {
        System.out.println("onComplete");
        try {
            System.out.println("AsynCall result :"+helloString_call.getResult().toString());
        } catch (TException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }

    @Override
    public void onError(Exception e) {
        System.out.println("onError :" + e.getMessage());
        latch.countDown();
    }
}
