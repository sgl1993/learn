package com.sgl.learn.jdk.jdk8.date;

import java.time.*;

/**
 * Description：新的日期时间 API
 *  1、为什么要引入新的日期时间API：
 *      在旧版的 Java 中，日期时间 API 存在诸多问题，其中有：
 *
 *      非线程安全 − java.util.Date 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一。
 *
 *      设计很差 − Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化和解析的类在java.text包中定义。java.util.Date同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入java.sql包并不合理。另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。
 *
 *      时区处理麻烦 − 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类，但他们同样存在上述所有的问题。
 *
 *  2、新 API：
 *      Local(本地) − 简化了日期时间的处理，没有时区的问题。
 *
 *      Zoned(时区) − 通过制定的时区处理日期时间。
 *
 *      说明：新的java.time包涵盖了所有处理日期，时间，日期/时间，时区，时刻（instants），过程（during）与时钟（clock）的操作。
 *
 *
 *
 * @author shaoguoli
 * @date 16:24 2018/7/27
 */
public class NewDateAPIDemo {
    public static void main(String[] args) {
        NewDateAPIDemo newDateAPIDemo = new NewDateAPIDemo();

//        newDateAPIDemo.testLocalDateTime();
        newDateAPIDemo.testZonedDateTime();
    }

    /**
     * 本地化日期时间 API：LocalDate/LocalTime 和 LocalDateTime 类可以在处理时区不是必须的情况。
     */
    public void testLocalDateTime() {
        //获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);

        LocalDate localDate = currentTime.toLocalDate();
        System.out.println("localDate: " + localDate);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();
        System.out.println("月: " + month +", 日: " + day +", 秒: " + seconds);

        LocalDateTime date1 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date1: " + date1);

        LocalDate date2 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date2: " + date2);

        LocalTime time1 = LocalTime.of(22, 15);
        System.out.println("time1: " + time1);

        // 解析字符串
        LocalTime time2 = LocalTime.parse("20:15:30");
        System.out.println("time2: " + time2);
    }

    /**
     * 需要考虑到时区，就可以使用时区的日期时间API.
     */
    public void testZonedDateTime() {

        //获取当前时区时间
        ZonedDateTime zonedNow = ZonedDateTime.now();
        System.out.println("zonedNow=" + zonedNow);// 2018-07-30T10:21:03.424+08:00[Asia/Shanghai]

        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2018-07-30T10:21:03.424+08:00[Asia/Shanghai]");
        System.out.println("zonedDateTime=" + zonedDateTime);

        //获取区域ID
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        System.out.println("zoneId=" + zoneId);
        zoneId = ZoneId.of("Asia/Shanghai");
        System.out.println("zoneId=" + zoneId);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);

    }

}
