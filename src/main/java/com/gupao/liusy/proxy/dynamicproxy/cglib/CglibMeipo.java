package com.gupao.liusy.proxy.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/20 14:55
 */
public class CglibMeipo implements MethodInterceptor {
    public Object getInstance(Class<?> clazz) {
        //相当于Proxy，代理的工具类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object object = methodProxy.invokeSuper(o,objects);
        after();
        return null;
    }


    public void before() {
        System.out.println("我是媒婆，已确认需求");
    }

    public void after() {
        System.out.println("找到合适目标，合适的话把账单结了");
    }
}