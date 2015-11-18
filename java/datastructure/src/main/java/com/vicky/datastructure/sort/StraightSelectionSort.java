package com.vicky.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * <p>
 * 选择排序：直接选择排序
 * 
 * 基本思想：
 * 直接选择排序同直接插入排序，也是将整个序列分为有序区和无序区，所不同的是直接播放排序是将无序区的第一个元素直接插入到有序区以形成一个更大的有序区
 * ，而直接选择排序是从无序区选一个最小的元素直接放到有序区的最后。
 * 
 * 时间复杂度：O(n^2)
 * 
 * 空间复杂度：θ(1)
 * 
 * 稳定性：不稳定
 * </p>
 * 
 * @author Vicky
 * @date 2015-8-13
 */
public class StraightSelectionSort {
	/**
	 * 排序
	 * 
	 * @param data
	 *            待排序的数组
	 */
	public static <T extends Comparable<T>> void sort(T[] data) {
		if (null == data) {
			throw new NullPointerException("data");
		}
		if (data.length == 1) {
			return;
		}
		for (int st = 0; st < data.length - 1; st++) {
			int j = st + 1;
			int min = st;
			// 找到最小元素的位置
			for (; j < data.length; j++) {
				if (data[j].compareTo(data[min]) < 0) {
					min = j;
				}
			}
			// 将最小元素放到有序区尾部
			if (min != st) {// 避免跟自身交换
				T temp = data[st];
				data[st] = data[min];
				data[min] = temp;
			}
		}
	}

	public static void main(String[] args) {
		Random ran = new Random();
		Integer[] data = new Integer[100000];
		for (int i = 0; i < data.length; i++) {
			data[i] = ran.nextInt(10000000);
		}
		StraightSelectionSort.sort(data);
		System.out.println(Arrays.toString(data));
	}
}
