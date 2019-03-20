package com.gupao.liusy.proxy.dbroute.proxy;

import com.gupao.liusy.proxy.dbroute.OrderInfo;
import com.gupao.liusy.proxy.dbroute.OrderService;
import com.gupao.liusy.proxy.dbroute.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 19:07
 */
public class OrderServiceStaticProxy implements OrderService {
    private OrderService orderService;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
    public OrderServiceStaticProxy(OrderService orderService){
        this.orderService = orderService;
    }
    @Override
    public int createOrder(OrderInfo orderInfo) {
        long time = orderInfo.getCreateTime();
        Integer dbRoute = Integer.valueOf(simpleDateFormat.format(new Date(time)));
        System.out.println("静态代理类自动分配到【DB_"+dbRoute+"】数据源");
        DynamicDataSourceEntity.set(dbRoute);
        return this.orderService.createOrder(orderInfo);
    }
}