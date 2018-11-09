package com.sgl.learn.jdk.jdk8.paixu;

/**
 * Description：快速排序 【https://blog.csdn.net/morewindows/article/details/6684558】
 *
 * @author shaoguoli
 * @date 21:00 2018/11/5
 */
public class QuickSort {

    public static void quickSort(int[] arr , int l , int r){
        //首先去arr[i]作为基数，分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。

        if (l < r) {
            int i = l, j = r, x = arr[i];
            while (i < j) {
                //拿x 与 arr[j] 从右至左进行比较，直到找出 x>a[j]的数
                while(i < j && arr[j] >= x) {
                    j--;
                }
                //如果 x> a[j], 那么就将a[i] = j[j];
                if (i < j) {
                    arr[i] = arr[j];
                }

                //那 x 与 arr[i] 从左至右进行比较，直到找出 x <= arr[i] 的数
                while(i < j && arr[i] < x) {
                    i++;
                }
                if (i < j) {
                    arr[j] = arr[i];
                }
            }
            //i >= j
            arr[i] = x;
            quickSort(arr, l , i -1);
            quickSort(arr, i + 1 , r);
        }

    }

    public static void main(String[] args) {
        int[] arr = {72,6,57,88,60,42,83,73,48,85};
        quickSort(arr, 0 , arr.length - 1);
        for (int i :arr) {
            System.out.println(i);
        }
    }

}
