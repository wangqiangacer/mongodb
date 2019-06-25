package com.jacken.mongodb.Practice;

import com.jacken.mongodb.domain.User;

import java.lang.reflect.Method;

public class Reflect {
    public static void main(String[] args) throws Exception {
        Class aClass = User.class;

//        Method[] methods = aClass.getMethods();
//        for (Method method : methods) {
//            System.out.println(method);
//        }

        //通过反射获取指定的方法名
        Method method = aClass.getMethod("eat", String.class);
        Object instance = aClass.newInstance();
        //方法调用
        method.invoke(instance,"牛肉");


    }
}
