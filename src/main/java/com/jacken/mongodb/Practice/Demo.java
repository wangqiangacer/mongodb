package com.jacken.mongodb.Practice;

import com.jacken.mongodb.domain.User;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        ArrayList<User> list1 = new ArrayList<>();
        list1.add(new User("1","lisi"));
        list1.add(new User("2","zhangsan"));
        list1.add(new User("3","wangwu"));
//        Map<String, String> map = list.stream().collect(Collectors.toMap(User::getId, User::getName, v -> v, (k,v)-> v)));
//        System.out.println(map);
       // 以名字作为key  value不变  在排序
//        Map<String, User> map = list.stream().collect(Collectors.toMap(User::getName, v -> v, (k, v) -> v));
//        map.forEach((k, v) -> System.out.println("Key: " + k +", value: "+ v));

       // list.stream().map(item->item.getId()).filter(Objects::isNull).forEach(s -> System.out.println(s));
      //list.stream().map(item->)
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Supplier<Map<Integer,Integer>> mapSupplier = () -> list.stream().collect(Collectors.toMap(x->x, y-> y));
        System.out.println(mapSupplier);
        Map<Integer, Integer> integerIntegerMap = mapSupplier.get();
        System.out.println(integerIntegerMap);

        Map<Integer, Integer> mapValueAdd = list.stream().collect(Collectors.toMap(x->x, y->y, (v1,v2) -> v1+v2, mapSupplier));
        System.out.println(mapValueAdd);
       // list.stream().map(item->)
    }
}
