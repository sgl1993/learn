package com.sgl.learn.jdk.jdk8.methdreference;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;

/**
 * Description：Method Reference
 *
 * 方法引用：我们通常使用lambda表达式来创建匿名方法。然而，有时候我们仅仅是调用了一个已存在的方法
 *  eg: lambda:Arrays.sort(stringsArray,(s1,s2)->s1.compareToIgnoreCase(s2));
 *      方法引用：Arrays.sort(stringsArray, String::compareToIgnoreCase);
 *
 * 1、形式：类名::方法名
 * 引用静态方法	 -> ContainingClass::staticMethodName
 * 引用某个对象的实例方法		 -> containingObject::instanceMethodName
 * 引用某个类型的任意对象的实例方法	 -> ContainingType::methodName
 * 引用构造方法	 -> ClassName::new
 *
 * @author shaoguoli
 * @date 15:16 2018/7/19
 */
public class MethodRefDemo {
    public enum Sex{
        MALE,FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public Sex getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public static int compareByAge(MethodRefDemo a,MethodRefDemo b){
        return a.birthday.compareTo(b.birthday);
    }

    public int compareByName(MethodRefDemo a,MethodRefDemo b){
        return a.getName().compareTo(b.getName());
    }

    public int compareByAge2(MethodRefDemo a,MethodRefDemo b){
        return a.getBirthday().compareTo(b.getBirthday());
    }

    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
    DEST transferElements(SOURCE sourceColletions, Supplier<DEST> colltionFactory) {
        DEST result = colltionFactory.get();
        for (T t : sourceColletions) {
            result.add(t);
        }
        return result;
    }

    public static void main(String[] args) {
        MethodRefDemo[] methodRefDemos = new MethodRefDemo[10];

        //匿名类
        Arrays.sort(methodRefDemos, new Comparator<MethodRefDemo>() {
            @Override
            public int compare(MethodRefDemo o1, MethodRefDemo o2) {
                return o1.birthday.compareTo(o2.birthday);
            }
        });

        //lambda
        Arrays.sort(methodRefDemos, (o1, o2) -> o1.birthday.compareTo(o2.birthday));

        //lambda + 类的静态方法
        Arrays.sort(methodRefDemos, (o1, o2) -> MethodRefDemo.compareByAge(o1,o2));

        //使用类的静态方法引用
        Arrays.sort(methodRefDemos, MethodRefDemo::compareByAge);


        /**
         * --------------------------------------------------------------------------------------------
         */

        MethodRefDemo methodRefDemo = new MethodRefDemo();

        //lambda + 对象的实例方法
        Arrays.sort(methodRefDemos, (a, b) -> methodRefDemo.compareByAge2(a, b));

        //使用对象方法引用
        Arrays.sort(methodRefDemos, methodRefDemo::compareByAge2);

        /**
         * --------------------------------------------------------------------------------------------
         */

        String[] stringsArray = {"Hello","World"};

        //lambda + 类型对象的实例方法
        Arrays.sort(stringsArray, (a, b) -> a.compareToIgnoreCase(b));

        //使用类型对象引用
        Arrays.sort(stringsArray, String::compareToIgnoreCase);

        /**
         * --------------------------------------------------------------------------------------------
         */
        final List<MethodRefDemo> personList = Arrays.asList(methodRefDemos);

        // lambda
        Set<MethodRefDemo> methodRefDemoSet = transferElements(personList, () -> new HashSet<>());

        // 方法引用，引用的是构造方法
        Set<MethodRefDemo> methodRefDemoHashSet = transferElements(personList, HashSet::new);


    }
}
