package com.gupao.liusy.proxy.dynamicproxy.jdkproxy;

import com.gupao.liusy.proxy.Person;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 19:29
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        Person person = (Person) new JDKMeipo().getInstance(new Girl());
        person.findLove();
        byte[] bytes = ProxyGenerator.generateProxyClass("$proxy0",new Class[]{Person.class});
        FileOutputStream os = null;
        try {
            os = new FileOutputStream("E://$proxy0.class");
            os.write(bytes);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}