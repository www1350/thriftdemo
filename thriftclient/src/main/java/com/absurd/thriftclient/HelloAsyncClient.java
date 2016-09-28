package com.absurd.thriftclient;

import org.apache.thrift.TException;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/9/28.
 */
public class HelloAsyncClient {
    public static void main(String[] args) throws IOException, InterruptedException, TException {
        //异步调用管理器
        TAsyncClientManager clientManager = new TAsyncClientManager();
        //设置传输通道，调用非阻塞IO
        TNonblockingTransport transport = new TNonblockingSocket("localhost", 7911);
        // 协议要和服务端一致
        TProtocolFactory tprotocol = new TBinaryProtocol.Factory();

        Hello.AsyncClient asyncClient = new Hello.AsyncClient(tprotocol, clientManager, transport);
        CountDownLatch latch = new CountDownLatch(1);
        AsynCallback callBack = new AsynCallback(latch);
        System.out.println("call method sayHello start ...");
        // 调用服务
        asyncClient.helloString("hello",callBack);
        System.out.println("call method sayHello .... end");
        //等待完成异步调用
        boolean wait = latch.await(30, TimeUnit.SECONDS);
        System.out.println("latch.await =:" + wait);
    }


}