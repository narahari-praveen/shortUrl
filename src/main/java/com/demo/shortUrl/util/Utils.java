package com.demo.shortUrl.util;

import java.time.LocalDateTime;
import static com.demo.shortUrl.util.Constants.*;

public class Utils {

    public static String getUniqueID(int num){
        StringBuilder  stringBuilder = new StringBuilder();
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear()/2000;
        int month = localDateTime.getMonthValue();
        int date = (int) (localDateTime.getDayOfMonth()/1.28);
        int second = localDateTime.getSecond();
        stringBuilder.append(LOWER_CASE.charAt(date)).append(second).append(UPPER_CASE.charAt(year)).append(getStringByNumber(num));
        return stringBuilder.toString();
    }

    private static String getStringByNumber(int number){
        StringBuilder  stringBuilder = new StringBuilder();
        while (number > 0) {
            int remainder = number % 10;
            if(number % 2 ==0){
            stringBuilder.append(LOWER_CASE.charAt(remainder));
            }else{
                stringBuilder.append(UPPER_CASE.charAt(remainder));
            }
            number = number / 10;
        }
        return stringBuilder.toString();
    }

    public static String verifyUrl(String str){
        if(str.contains("http://") || str.contains("https://")){
            return str;
        }else{
            str += "http://";
            return str;
        }
    }
}
