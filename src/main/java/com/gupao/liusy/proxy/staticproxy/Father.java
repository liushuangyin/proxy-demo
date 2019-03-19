package com.gupao.liusy.proxy.staticproxy;

import com.gupao.liusy.proxy.Person;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 18:53
 */
public class Father implements Person {
    private Person person;
    public Father(Person person){
        this.person = person;
    }
    public void findLove() {
        System.out.println("父亲开始物色");
        this.person.findLove();
        System.out.println("征询儿子是否满意");
    }

    public void marry() {

    }
}