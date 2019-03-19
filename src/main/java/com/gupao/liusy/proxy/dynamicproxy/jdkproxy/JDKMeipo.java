package com.gupao.liusy.proxy.dynamicproxy.jdkproxy;

import com.gupao.liusy.proxy.Person;

import javax.jnlp.PersistenceService;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 说明：动态代理
 *
 * @author liusy
 * @date 2019/3/18 19:26
 */
public class JDKMeipo implements InvocationHandler{

    private Person person;

    public Object getInstance(Person person){
        this.person = person;
        Class<?> clazz = person.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
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