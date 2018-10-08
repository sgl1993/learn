package com.sgl.learn.jdk.jdk8.staticinterface;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Description：默认方法 和 静态接口方法
 *
 *  默认方法：默认方法让我们能给我们的软件库的接口增加新的方法，并且能保证对使用这个接口的老版本代码的兼容性。
 *
 *  继承含有默认方法的接口：
 *      1、不去管默认方法，继承的接口直接继承默认方法
 *      public interface AnotherTimeClient  extends  TimeClient{ }
 *
 *      2、重新声明默认方法，这样会使得这个方法变成抽象方法
 *      public interface AbstractZoneTimeClient extends TimeClient{
 *          @Override
 *          ZonedDateTime getZonedDateTime(String zoneString);
 *      }
 *
 *      3、重新定义默认方法，这样会使得方法被重写
 *      public interface OverRideStaticInterfaceDemo extends StaticInterfaceDemo {
 *          default ZonedDateTime getZonedDateTime(String zoneString){
 *              try {
 *                  return ZonedDateTime.of(getLocalDateTime(), ZoneId.of(zoneString));
 *              } catch (DateTimeException e) {
 *                  System.err.println("Invalid zone ID: " + zoneString +
 *                     "; using the default time zone instead.");
 *                  return ZonedDateTime.of(getLocalDateTime(),ZoneId.systemDefault());
 *              }
 *          }
 *       }
 *
 *
 *  静态接口方法：
 *      Java8的接口中，我们不光能写默认方法，还能写静态方法。下面的例子中正好用到了静态方法。
 *
 *
 * @author shaoguoli
 * @date 14:28 2018/7/19
 */
public interface StaticInterfaceDemo {
    void setTime(int hour, int minute, int second);
    void setDate(int day, int month, int year);
    void setDateAndTime(int day, int month, int year,
                        int hour, int minute, int second);
    LocalDateTime getLocalDateTime();

    static ZoneId getZoneId (String zoneString) {
        try {
            return ZoneId.of(zoneString);
        } catch (DateTimeException e) {
            System.err.println("Invalid time zone: " + zoneString +
                    "; using default time zone instead.");
            return ZoneId.systemDefault();
        }
    }

    //默认方法
    default ZonedDateTime getZonedDateTime(String zoneString) {
        return ZonedDateTime.of(getLocalDateTime(), getZoneId(zoneString));
    }
}
