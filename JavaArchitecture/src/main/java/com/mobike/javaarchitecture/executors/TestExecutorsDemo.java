package com.mobike.javaarchitecture.executors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 线程&线程池类使用
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/26 上午10:29
 */
public class TestExecutorsDemo {

    public static void main(String[] args) {


//        Storage storage = new Storage();
//        ExecutorService service = Executors.newCachedThreadPool();
//
//        Producer p = new Producer("张三", storage);
//        Producer p2 = new Producer("李四", storage);
//        Consumer c = new Consumer("王五", storage);
//        Consumer c2 = new Consumer("老刘", storage);
//        Consumer c3 = new Consumer("老林", storage);
//        service.submit(p);
//        //service.submit(p2);
//        service.submit(c);
//        service.submit(c2);
//        service.submit(c3);

        //
        try {
            testDays();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    // 生产者消费者模式 伪代码
    // 1.使用synchronize wait & notify
    public void testProductConsumer() {


    }


    private static void testDays() throws ParseException {
        String nowTime = convertCalendarToString3(Calendar.getInstance());

        System.out.println("保存的时间：" + nowTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String nowTime1 = simpleDateFormat.format(date);

        System.out.println("保存的时间：" + nowTime1);

        String startDate = "2019-05-20";

        Date beginDate = simpleDateFormat.parse(startDate);
        Date endDate = simpleDateFormat.parse(nowTime1);

        int days = getDiscrepantDays(beginDate, endDate);
        System.out.println("时间天数：" + days);
    }


    public static String convertCalendarToString3(Calendar calendar) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
            String date = sdf.format(calendar.getTime());
            return date;
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return "";

    }

    public static int getDiscrepantDays(Date dateStart, Date dateEnd) {
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24);
    }


}
