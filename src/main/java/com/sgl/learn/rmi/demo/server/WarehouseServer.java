package com.sgl.learn.rmi.demo.server;

import com.sgl.learn.rmi.demo.impl.WarehouseImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Description：RMI服务
 * 1、通过 JNDI 发布
 * 2、将可远程访问的对象注册到 JNDI
 *
 * @author shaoguoli
 * @date 14:23 2018/10/8
 */
public class WarehouseServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        System.out.println("Constructing server implementation");
        WarehouseImpl warehouse = new WarehouseImpl();

        System.out.println("Binding server implementation to registry");

        /** 只需提供一个port，就在 JNDI 中创建了一个注册表 */
        LocateRegistry.createRegistry(1099);

        /** 通过 bind 方法，绑定了 RMI 地址与 RMI 服务实现类 */
        Naming.bind("rmi://localhost:1099/central_warehouse", warehouse);

        /** 执行了这个方法之后，就相当于自动发布了 RMI 服务，接下来要做的事情就是等待客户端调用已发布的 RMI 服务 */
        System.out.println("Waiting for invocation from clients ...");
    }
}
