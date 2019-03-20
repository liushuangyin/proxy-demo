package com.gupao.liusy.proxy.dynamicproxy.myproxy;

import com.gupao.liusy.proxy.Person;
import com.gupao.liusy.proxy.Girl;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 19:29
 */
public class MyProxyTest {
    public static void main(String[] args) {
        Person person = (Person) new MyMeipo().getInstance(new Girl());
        person.findLove();
        person.marry();
    }
}