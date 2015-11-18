package com.vicky.datastructure.sort;

import java.util.Random;

/**
 * 交换排序：快速排序
 * 
 * 时间复杂度：O(nlgn)
 * 
 * @author Vicky
 * 
 */
public class QuickSort {
	public static <T extends Comparable<T>> void sort(T[] data) {
		if (null == data) {
			throw new NullPointerException("data");
		}
		if (data.length == 1) {
			return;
		}
		devideSort(data, 0, data.length - 1);
	}

	private static <T extends Comparable<T>> void devideSort(T[] data, int start, int end) {
		int i = start;
		int j = end;
		T temp = data[start];
		while (i < j) {
			// 从尾部开始，一直查找比较，直到找到一个小于等于temp的值，然后交换，继续从头部开始查找
			while (i < j && data[j].compareTo(temp) > 0) {
				j--;
			}
			data[i] = data[j];
			while (i < j && data[i].compareTo(temp) <= 0) {
				i++;
			}
			data[j] = data[i];
		}
		data[i] = temp;
		if (start < i - 1) {
			devideSort(data, start, i - 1);
		}
		if (i + 1 < end) {
			devideSort(data, i + 1, end);
		}
	}

	@SuppressWarnings("unused")
	private static <T extends Comparable<T>> void devideSort2(T[] data, int start, int end) {
		int i = start;
		int j = end;
		T temp = data[start];
		boolean direct = false;
		while (i < j) {
			if (direct) {
				if (data[i].compareTo(temp) >= 0) {
					SortUtils.swap(data, i, j);
					j--;
					direct = false;
				} else {
					i++;
				}
			} else {
				if (data[j].compareTo(temp) < 0) {
					SortUtils.swap(data, i, j);
					i++;
					direct = true;
				} else {
					j--;
				}
			}
		}
		if (start < i - 1) {
			devideSort(data, start, i - 1);
		}
		if (i + 1 < end) {
			devideSort(data, i + 1, end);
		}
	}

	public static void main(String[] args) {
		Random ran = new Random();
		Integer[] data = new Integer[10];
		for (int i = 0; i < data.length; i++) {
			data[i] = ran.nextInt(100000);
		}
		QuickSort.sort(data);
		SortUtils.printArray(data);
	}
}