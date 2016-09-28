package com.absurd.thriftserver;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by Administrator on 2016/9/28.
 */
public class HelloNonblockingServer {

    public static void main(String[] args) throws TTransportException {
        // 传输通道 - 非阻塞方式
        TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(7911);

        TFramedTransport.Factory traFactory = new TFramedTransport.Factory();
        //使用高密度二进制协议
        TCompactProtocol.Factory tcpFactory =  new TCompactProtocol.Factory();
        //处理器
        TProcessor tprocessor = new Hello.Processor<Hello.Iface>(new HelloServiceImpl());

        //异步IO，需要使用TFramedTransport，它将分块缓存读取。
        TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
        tArgs.processor(tprocessor);
        tArgs.transportFactory(traFactory);
        tArgs.protocolFactory(tcpFactory);

        // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
        TServer server = new TNonblockingServer(tArgs);
        System.out.println("HelloTNonblockingServer start....");
        server.serve(); // 启动服务
    }

}
