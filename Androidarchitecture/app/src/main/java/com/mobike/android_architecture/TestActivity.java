package com.mobike.android_architecture;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/2 下午7:41
 */
public class TestActivity {

    public static void main(String[] args) {
        int num = 0;
        String first = "2330260";
        String middle;
        String last = "00000";
        String finalStr;

        for (int i = 0; i < 1200; i++) {
            num++;
            if (num < 10) {
                middle = "000" + num;
            } else if (num < 100) {
                middle = "00" + num;
            } else {
                middle = num + "";
            }
            StringBuilder sb = new StringBuilder(first);
            finalStr = sb.append(middle).append(last).toString();
            System.out.println(finalStr + "\n");
        }
    }
}
