package com.gupao.liusy.proxy.dynamicproxy.myproxy;

import com.gupao.liusy.proxy.Person;

import java.lang.reflect.Method;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 19:56
 */
public class MyMeipo implements MyInvocationHandler{
    private Person person;

    public Object getInstance(Person person){
        this.person = person;
        Class<?> clazz = person.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(),clazz.getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(this.person,args);
        after();
        return obj;
    }

    public void before() {
        System.out.println("我是媒婆，已确认需求");
    }

    public void after() {
        System.out.println("找到合适目标，合适的话把账单结了");
    }
}