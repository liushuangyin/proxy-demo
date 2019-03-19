package com.gupao.liusy.proxy.dynamicproxy.myproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 说明：
 *
 * @author liusy
 * @date 2019/3/18 19:53
 */
public class MyProxy {
    private final static String ln = "\r\n";

    public static Object newProxyInstance(MyClassLoader loader, Class<?>[] interfaces, MyInvocationHandler h)
            throws IllegalArgumentException {
        try {
            String src = generates(interfaces);
            System.out.println(src);
            String filepath = MyProxy.class.getResource("").getPath();
            File file = new File(filepath,"$Proxy0.java");
            FileWriter writer = new FileWriter(file);
            writer.write(src);
            writer.flush();
            writer.close();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null,manager,null,null,null,iterable);
            task.call();
            manager.close();

            //4、编译生成的.class文件加载到JVM中来
            Class proxyClass =  loader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(MyInvocationHandler.class);
            file.delete();

            //5、返回字节码重组以后的新的代理对象
            return c.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generates(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.gupao.liusy.proxy.dynamicproxy.myproxy;").append(ln);
        for (Class clazz : interfaces) {
            sb.append("import ").append(clazz.getName()).append(";").append(ln);
        }
        sb.append("import java.lang.reflect.*;").append(ln);
        sb.append("public class $Proxy0 implements ");
        int i = 0;
        for (Class clazz : interfaces) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(clazz.getName());
            i++;
        }
        sb.append("{").append(ln);

        sb.append("MyInvocationHandler h;").append(ln);
        sb.append("public $Proxy0(MyInvocationHandler h){this.h = h;}").append(ln);
        for (Class clazz : interfaces) {
            for (Method m : clazz.getMethods()) {
                sb.append("public ").append(m.getReturnType().getName()).append(" ").append(m.getName()).append("(){").append(ln);
                sb.append("try{").append(ln);
                sb.append("Method m = ").append(clazz.getName()).append(".class.getMethod(\"").append(m.getName()).append("\",new Class[]{});").append(ln);
                sb.append("this.h.invoke(this,m,null);").append(ln);
                sb.append("}catch(Throwable e){").append(ln);
                sb.append(" e.printStackTrace();");
                sb.append("}").append(ln);
                sb.append("}").append(ln);
            }
        }
        sb.append("}").append(ln);

        return sb.toString();
    }
}