package com.axzhengxin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilTool {
	/**
     * 将某个日期以固定格式转化成字符串
     * @param date
     * @return String
     */
    public static String dateToStr(Date date,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String str = sdf.format(date);
        return str;
    }
    
    /**
     * 判断任意一个整数是否素数
     * 
     * @param n
     * @return boolean
     */
    public static boolean isPrimes(int n){
        for (int i = 2; i <= Math.sqrt(n); i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 获得任意一个整数的阶乘，递归
      * 
     * @param n
     * @return n!
     */
    public static int factorial(int n){
        if (n == 1){
            return 1;
        }
        return n * factorial(n - 1);
    }

}
