package com.sgl.learn.jdk.jdk8.paixu;

/**
 * Description：桶排序
 * 第一种：桶号包含所有元素,即元素之间的差值不是很大，使用如下排序法。跟计数排序一致
 *  eg:{5, 3, 7, 1, 8, 2, 9, 4, 7, 2, 6, 6, 2, 6, 6}
 *
 * 第二种：待排元素大小和个数相差远，桶号不宜和元素值对应,些数据全部在1—100之间。
 * 因此我们定制10个桶，然后确定映射函数f(k)=k/10。则第一个关键字49将定位到第4个桶中(42/10=4)。
 * 依次将所有关键字全部堆入桶中，并在每个非空的桶中进行插入排序后得到。
 *  eg:{5,18,27,33,42,66,90,8,81,47,13,27,67,9,36,62,22};
 * @author shaoguoli
 * @date 17:17 2018/11/1
 */
public class BucketSort {

    //第一种，元素差值不大，每个桶的下标即arr中的值，桶元素中的值即下标出现次数
    public static void bucketSort(int[] arr){
        if (arr==null||arr.length<2){
            return;
        }
        //常用写法
        int max = Integer.MIN_VALUE;
        for (int i =0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
        }

        int[] bucket = new int[max+1];

        for (int i =0;i<arr.length;i++){
            //桶数组此下标有数据，数值就加一
//            bucket[arr[i]]++;
            bucket[arr[i]] = bucket[arr[i]] + 1;
        }

        int i = 0;

        for (int j = 0;j<bucket.length;j++){
            while (bucket[j]-->0){
                arr[i++]=j;
            }
        }
        System.out.println(arr.length);
        for (int s : arr) {
            System.out.println(s);
        }
    }

    public static void bucketSort2(int[] arr){
        if (arr==null||arr.length<2){
            return;
        }
        //常用写法
        int max = Integer.MIN_VALUE;
        for (int i =0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
        }

        int[] bucket = new int[max+1];

        for (int i =0;i<arr.length;i++){
            //桶数组此下标有数据，数值就加一
//            bucket[arr[i]]++;
            bucket[arr[i]] = bucket[arr[i]] + 1;
        }

        int i = 0;

        for (int j = 0;j<bucket.length;j++){
            while (bucket[j]-->0){
                arr[i++]=j;
            }
        }
        System.out.println(arr.length);
        for (int s : arr) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5,18,27,33,42,66,90,8,81,47,13,27,67,9,36,62,22};
        bucketSort(arr);
    }

}
