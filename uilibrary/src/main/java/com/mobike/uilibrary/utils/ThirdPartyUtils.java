package com.mobike.uilibrary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/12 下午1:42
 */
public class ThirdPartyUtils {
    /**
     * 判断email格式是否正确
     */
    public static boolean isEmail(String email) {
        try {
            if (email == null || email.equals(""))
                return false;
            String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(email);
            return m.matches();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;

    }

    /**
     * 判断是否是电话号码
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isMobilePhoneOrFamilyPhone(String phoneNumber) {
        try {
            // -------------手机号码，以1开始，13,15,18,19,为合法，后根9位数字------
            String regEx = "[0-9]{11}"; // 表示a或f
            boolean isMobliePhone = Pattern.compile(regEx).matcher(phoneNumber)
                    .find() && (phoneNumber.length() == 11);
            String tregEx = "([0]{1}[0-9]{2,3}-?)?[0-9]{7,8}"; // 表示a或f 0832-80691990 覆盖3-4位区号 有无区号 有无斜杠 7-8位号码
            boolean isFamilyPhone = Pattern.compile(tregEx)
                    .matcher(phoneNumber).matches();
            if (isMobliePhone || isFamilyPhone) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是否是手机号码
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isMobilePhone(String phoneNumber) {
        try {
            // -------------手机号码，以1开始，13,15,18,19,为合法，后根9位数字------
            String regEx = "[1]{1}[3,4,5,8,6,7]{1}[0-9]{9}"; // 表示a或f
            boolean isMobliePhone = Pattern.compile(regEx).matcher(phoneNumber)
                    .find() && (phoneNumber.length() == 11);
            if (isMobliePhone) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是否是QQ
     *
     * @param qq
     * @return
     */
    public static boolean isQQ(String qq) {
        try {
            if (qq == null || qq.equals(""))
                return false;
            String str = "[1-9]{1}+[0-9]{4,19}";
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(qq);
            return m.matches();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
