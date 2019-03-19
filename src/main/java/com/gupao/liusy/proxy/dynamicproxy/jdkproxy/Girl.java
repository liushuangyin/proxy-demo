package com.gupao.liusy.proxy.dynamicproxy.jdkproxy;

import com.gupao.liusy.proxy.Person;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 19:24
 */
public class Girl implements Person {

    public void findLove() {
        System.out.println("高富帅，身高180cm");
    }

    public void marry() {
        System.out.println("结婚了");
    }
}