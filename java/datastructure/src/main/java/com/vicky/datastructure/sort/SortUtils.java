package com.vicky.datastructure.sort;

import java.util.Arrays;

public class SortUtils {
	/**
	 * 校验排序结果，升序
	 * 
	 * @param <T>
	 * @param data
	 * @return
	 */
	public static <T extends Comparable<T>> boolean checkSort(T[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			if (data[i].compareTo(data[i + 1]) > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 交换元素
	 * 
	 * @param data
	 * @param i
	 * @param j
	 */
	public static void swap(Object[] data, int i, int j) {
		Object temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	/**
	 * 打印数组内容
	 * 
	 * @param data
	 */
	public static void printArray(Object[] data) {
		System.out.println(Arrays.toString(data));
	}

	/**
	 * 计算数组最大值，最小值，均值
	 * 
	 * @param useTimes
	 * @return
	 */
	public static long[] countArray(long[] useTimes) {
		long max = 0, min = Long.MAX_VALUE, sum = 0;
		for (long time : useTimes) {
			if (time > max) {
				max = time;
			}
			if (time < min) {
				min = time;
			}
			sum += time;
		}
		return new long[] { max, min, sum / useTimes.length };
	}
}
