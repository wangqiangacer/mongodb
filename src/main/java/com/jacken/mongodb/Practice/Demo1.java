package com.jacken.mongodb.Practice;



import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo1 {
    public static void main(String[] args) {
//        boolean numeric = isNumeric("3456g");
//        System.out.println(numeric);
        String s="1234ghdg";
//        if(StringUtils.isNumeric(s)){
//            System.out.println(true);
//        }else {
//            System.out.println(false);
//        }

        if(StringUtils.hasText(s)){
            System.out.println("1111");
        }
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

}
