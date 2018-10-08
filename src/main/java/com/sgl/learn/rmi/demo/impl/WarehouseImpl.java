package com.sgl.learn.rmi.demo.impl;

import com.sgl.learn.rmi.demo.Warehouse;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Description：远程方法调用的目标
 *
 * 1、继承 UnicastRemoteObject 使得这个类的对象可供远程访问
 *
 * @author shaoguoli
 * @date 11:34 2018/10/8
 */
public class WarehouseImpl extends UnicastRemoteObject implements Warehouse {

    private static final long serialVersionUID = 1L;

    private Map<String, Double> prices;

    public WarehouseImpl() throws RemoteException {
        prices = new HashMap<>();
        prices.put("mate7",3700.00);
    }

    @Override
    public double getPrice(String description) throws RemoteException {
        Double price = prices.get(description);
        return price;
    }
}

