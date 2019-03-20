package com.gupao.liusy.proxy.dbroute;

import com.gupao.liusy.proxy.dbroute.proxy.OrderServiceDynamicProxy;
import com.gupao.liusy.proxy.dbroute.proxy.OrderServiceStaticProxy;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 19:20
 */
public class DbrouteProxyTest {
    public static void main(String[] args) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateTime(System.currentTimeMillis());
        OrderService orderService= new OrderServiceImpl();
        OrderServiceStaticProxy proxy = new OrderServiceStaticProxy(orderService);
        proxy.createOrder(orderInfo);
        OrderService dynamicOrderService = (OrderService) new OrderServiceDynamicProxy().getInstance(new OrderServiceImpl());

        dynamicOrderService.createOrder(orderInfo);
    }
}