package com.gupao.liusy.proxy.dynamicproxy.myproxy;

import java.lang.reflect.Method;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 19:52
 */
public interface MyInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}