package com.sgl.learn.jdk.jdk8.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * Description：Java8 Base64
 *
 * 1、是什么：Base64 编码是Java类库的标准。Java 8 内置了 Base64 编码的编码器和解码器。
 *
 * 2、有什么：
 *      Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：
 *
 *      基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。**URL编码也是我们经常会面对的需求，但由于URL对反斜线“/”有特殊的意义，因此URL编码需要替换掉它，使用下划线替换。
 *            比如下面的例子：[c3ViamVjdHM/YWJjZA==] 与 [c3ViamVjdHM_YWJjZA==]
 *      URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
 *      MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
 *
 * 3、怎么做：
 *
 *  类说明：
 *      1	static class Base64.Decoder：该类实现一个解码器用于，使用 Base64 编码来解码字节数据。
 *
 *      2	static class Base64.Encoder：该类实现一个编码器，使用 Base64 编码来编码字节数据。
 *
 *  方法说明：
 *     序号	    方法名 & 描述
 *      1	static Base64.Decoder getDecoder()：返回一个 Base64.Decoder ，解码使用基本型 base64 编码方案。
 *
 *      2	static Base64.Encoder getEncoder()：返回一个 Base64.Encoder ，编码使用基本型 base64 编码方案。
 *
 *      3	static Base64.Decoder getMimeDecoder()：返回一个 Base64.Decoder ，解码使用 MIME 型 base64 编码方案。
 *
 *      4   static Base64.Encoder getMimeEncoder()：返回一个 Base64.Encoder ，编码使用 MIME 型 base64 编码方案。
 *
 *      5	static Base64.Encoder getMimeEncoder(int lineLength, byte[] lineSeparator)：返回一个 Base64.Encoder ，编码使用 MIME 型 base64 编码方案，可以通过参数指定每行的长度及行的分隔符。
 *
 *      6	static Base64.Decoder getUrlDecoder()：返回一个 Base64.Decoder ，解码使用 URL 和文件名安全型 base64 编码方案。
 *
 *      7	static Base64.Encoder getUrlEncoder()：返回一个 Base64.Encoder ，编码使用 URL 和文件名安全型 base64 编码方案。
 *
 * @author shaoguoli
 * @date 10:39 2018/7/30
 */
public class Base64Demo {

    public static void main(String[] args) {
        try {
            String demoStr1 = "subjects?abcd";
            //使用基本编码
            String encodeByGeneral = Base64.getEncoder().encodeToString(demoStr1.getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (基本) :" + encodeByGeneral);

            //使用基本解码
            byte[] decodeArr = Base64.getDecoder().decode(encodeByGeneral);
            System.out.println("原始字符串: " + new String(decodeArr, "utf-8"));

            String encodeByUrl = Base64.getUrlEncoder().encodeToString(demoStr1.getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (URL) :" + encodeByUrl);

            StringBuilder sb = new StringBuilder();

            for (int i = 0 ; i < 10; i++) {
                sb.append(UUID.randomUUID().toString());
            }

            byte[] mimeBytes = sb.toString().getBytes("utf-8");
            String encodeByMime = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 编码字符串 (MIME) :" + encodeByMime);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
