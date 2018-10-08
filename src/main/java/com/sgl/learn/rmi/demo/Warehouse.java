package com.sgl.learn.rmi.demo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Description：远程对象表示的是一个仓库，而客户端程序向仓库询问某个产品的价格。
 *
 * 1、远程对象接口必须继承Remote
 * 2、接口中所有方法的声明必须抛出 RemoteException （远程接口总是存在失败的可能）
 *
 *
 * @author shaoguoli
 * @date 11:31 2018/10/8
 */
public interface Warehouse extends Remote {
    double getPrice(String description) throws RemoteException;
}
