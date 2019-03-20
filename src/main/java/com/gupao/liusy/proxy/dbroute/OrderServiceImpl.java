package com.gupao.liusy.proxy.dbroute;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 19:08
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;

    public OrderServiceImpl(){
        //如果使用Spring应该是自动注入的
        //我们为了使用方便，在构造方法中将orderDao直接初始化了
        orderDao = new OrderDao();
    }

    @Override
    public int createOrder(OrderInfo order) {
        System.out.println("OrderServiceimpl 调用orderDao创建订单");
        return orderDao.insert(order);
    }
}