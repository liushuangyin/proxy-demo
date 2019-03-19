package com.gupao.liusy.proxy.dbroute;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 18:59
 */
public class OrderDao {
    public int insert(OrderInfo orderInfo){
        System.out.println("OrderDao创建Order成功!");
        return 1;
    }
}