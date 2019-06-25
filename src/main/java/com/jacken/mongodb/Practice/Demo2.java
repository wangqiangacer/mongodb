package com.jacken.mongodb.Practice;

import java.util.Optional;

public class Demo2 {
    public static void main(String[] args) {

      String s=null;
      if(Optional.ofNullable(s).isPresent()){
          System.out.println(111111);
      }else {
          System.out.println("empty");
      }

    }
}
