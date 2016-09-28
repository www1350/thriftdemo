package com.absurd.thriftclient;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by Administrator on 2016/9/28.
 */
public class HelloNonblockingClient {

    public static void main(String[] args) {
        TTransport transport = null;
        try {
            // 设置调用的服务地址为本地，端口为 7911
            transport = new TFramedTransport(new TSocket("localhost", 7911));
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TCompactProtocol(transport);
            Hello.Client client = new Hello.Client(protocol);
            transport.open();
            // 调用服务的 helloVoid 方法
            client.helloVoid();
            System.out.println(client.helloInt(1));
            System.out.println(client.helloBoolean(false));
            System.out.println(client.helloString("hello"));

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }finally {
            transport.close();
        }
    }
}
