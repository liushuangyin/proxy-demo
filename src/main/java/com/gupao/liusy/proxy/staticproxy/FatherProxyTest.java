package com.gupao.liusy.proxy.staticproxy;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 18:56
 */
public class FatherProxyTest {
    public static void main(String[] args) {
        Son son = new Son();
        Father father = new Father(son);
        father.findLove();
    }
}