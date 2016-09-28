package com.absurd.thriftserver;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by Administrator on 2016/9/28.
 */
public class HelloTHsHaServer {
    public static void main(String[] args) throws TTransportException {
        // 传输通道 - 非阻塞方式
        TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(7911);

        TFramedTransport.Factory traFactory = new TFramedTransport.Factory();
        // 设置协议工厂为 TBinaryProtocol.Factory
        TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();

        //处理器
        TProcessor tprocessor = new Hello.Processor<Hello.Iface>(new HelloServiceImpl());
        //半同步半异步
        THsHaServer.Args tArgs = new THsHaServer.Args(serverTransport);

        tArgs.processor(tprocessor);
        tArgs.protocolFactory(proFactory);
        tArgs.transportFactory(traFactory);
        // 半同步半异步的服务模型
        TServer server = new THsHaServer(tArgs);
        System.out.println("HelloTHsHaServer start....");
        server.serve(); // 启动服务

    }
}
