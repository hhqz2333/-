package com.lv.sell.until;

import java.util.Random;

/**
 * @author ：LV hang
 * @date ：Created in 2020/3/24 0024  21:28
 * @modified By：
 */
public class KeyUtil {

    public static synchronized String  getPrimaryKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
