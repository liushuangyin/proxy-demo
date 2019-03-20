package com.gupao.liusy.proxy.dynamicproxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/20 17:32
 */
public class CglibTest {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"E://cglib");
        Customer customer = (Customer) new CglibMeipo().getInstance(Customer.class);
        System.out.println(customer);
        customer.marry();
    }
}