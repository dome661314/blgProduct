package io.blg.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderCodeFactory {

    /**
     * 订单类别头
     */
    private static final String ORDER_CODE = "BLG-";
    /**
     * 随即编码
     */


    /**
     * 生成时间戳
     */
    private static String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    /**
     * 生成不带类别标头的编码
     *
     */
    private static synchronized String getCode() {
        return getDateTime();
    }

    /**
     * 生成订单单号编码
     *
     */
    public static String getOrderCode() {
        return ORDER_CODE + getCode();
    }

}
