package com.absurd.thriftserver;

import org.apache.thrift.TException;
/**
 * Created by Administrator on 2016/9/28.
 */
public class HelloServiceImpl implements Hello.Iface {
    @Override
    public boolean helloBoolean(boolean para) throws TException {
        System.out.println("receive Boolean>>>"+para);
        return !para;
    }
    @Override
    public int helloInt(int para) throws TException {
        System.out.println("receive Int>>>"+para);
        return para+1;
    }
    @Override
    public String helloNull() throws TException {
        System.out.println("receive Null");
        return null;
    }
    @Override
    public String helloString(String para) throws TException {
        System.out.println("receive a String>>>>>"+para);
        return para+">>>echo";
    }
    @Override
    public void helloVoid() throws TException {
        System.out.println("receive a Void");
    }
}