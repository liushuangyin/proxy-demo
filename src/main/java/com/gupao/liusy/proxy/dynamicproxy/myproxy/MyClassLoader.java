package com.gupao.liusy.proxy.dynamicproxy.myproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 19:56
 */
public class MyClassLoader extends ClassLoader {
    private File classPathFile;

    public MyClassLoader() {
        String classPath = MyClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = MyClassLoader.class.getPackage().getName() + "." + name;
        if(classPathFile != null){
            File classFile = new File(classPathFile,name + ".class");
            if(classFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try{
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    while((len = in.read(bytes)) != -1){
                        out.write(bytes,0,len);
                    }
                    return defineClass(className,out.toByteArray(),0,out.size());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return super.findClass(name);
    }
}