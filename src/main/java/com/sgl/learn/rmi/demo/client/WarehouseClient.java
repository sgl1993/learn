package com.sgl.learn.rmi.demo.client;

import com.sgl.learn.rmi.demo.Warehouse;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Description：调用 RMI 服务
 *
 * @author shaoguoli
 * @date 14:54 2018/10/8
 */
public class WarehouseClient {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        System.out.println("RMI registry binding:");
        String rmiUrl = "rmi://localhost:1099/central_warehouse";

        /** 通过 rmiUrl 去 JNDI 注册表获取远程目标对象的代理
         *  注意：一定不能用实现类，否则就就是本地调用了 */
        Warehouse warehouse = (Warehouse) Naming.lookup(rmiUrl);
        String description = "mate7";
        double price = warehouse.getPrice(description);

        System.out.println("the " + description + "'s price is " + price);
    }
}