package com.gupao.liusy.proxy.dbroute.proxy;

import com.gupao.liusy.proxy.dbroute.db.DynamicDataSourceEntity;
import com.gupao.liusy.proxy.dynamicproxy.myproxy.MyClassLoader;
import com.gupao.liusy.proxy.dynamicproxy.myproxy.MyInvocationHandler;
import com.gupao.liusy.proxy.dynamicproxy.myproxy.MyProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 19:07
 */
public class OrderServiceDynamicProxy implements MyInvocationHandler {
    Object object;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");

    public Object getInstance(Object target) {
        this.object = target;
        Class<?> clazz = target.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args[0]);
        Object object = method.invoke(this.object, args);
        after();
        return object;
    }

    public void after() {
        //还原成默认的数据源
        DynamicDataSourceEntity.restore();
    }

    public void before(Object target) {
        try {
            Long time = (Long) target.getClass().getMethod("getCreateTime").invoke(target);
            Integer dbRoute = Integer.valueOf(simpleDateFormat.format(new Date(time)));
            DynamicDataSourceEntity.set(dbRoute);
            System.out.println("动态代理类自动分配到【DB_" + dbRoute + "】数据源");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}