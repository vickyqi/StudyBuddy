package com.vicky.datastructure.sort;

import java.util.Random;

/**
 * 归并排序
 * 
 * 时间复杂度：O(nlgn)
 * 
 * 空间复杂度：O(n)
 * 
 * 稳定性：稳定
 * 
 * @author Vicky
 * 
 */
public class MergeSort {
	public static <T extends Comparable<T>> void sort(T[] data) {
		if (null == data) {
			throw new NullPointerException("data");
		}
		if (data.length == 1) {
			return;
		}
		Object[] newData = new Object[data.length];
		devideSort(newData, data, 0, data.length - 1);
	}

	/**
	 * 分治法排序
	 * 
	 * @param <T>
	 * @param newData
	 * @param data
	 * @param start
	 * @param end
	 */
	private static <T extends Comparable<T>> void devideSort(Object[] newData, T[] data, int start, int end) {
		int middle = (start + end) / 2;
		if (middle > start) {
			devideSort(newData, data, start, middle);
		}
		if (middle + 1 < end) {
			devideSort(newData, data, middle + 1, end);
		}
		mergeArray(newData, data, start, middle, middle + 1, end);
	}

	/**
	 * 合并两个数组
	 * 
	 * @param <T>
	 * @param newData
	 *            用于辅助
	 * @param data
	 *            被合并的数组
	 * @param start1
	 * @param end1
	 * @param start2
	 * @param end2
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>> void mergeArray(Object[] newData, T[] data, int start1, int end1,
			int start2, int end2) {
		int i = start1, j = start2, index = start1;
		while (i <= end1 || j <= end2) {
			if (i > end1) {// 第一个数组已经全部合并到新的数组，则将第二个数组剩下的元素按顺序直接复制到新的数组
				newData[index++] = data[j++];
				continue;
			}
			if (j > end2) {// 第二个数组已经全部合并到新的数组，则将第一个数组剩下的元素按顺序直接复制到新的数组
				newData[index++] = data[i++];
				continue;
			}
			// 将两个数组从第一位开始比较，取出较小的复制到新的数组，同时继续将较大的元素与较小的元素所在的数组后续的元素进行对比
			if (data[i].compareTo(data[j]) <= 0) {
				// 此处i<=j的时候也选择i的原因是为了保证稳定性，因为i在原数组中是在j前面的，
				// 所以即使i==j我们也选择i保证排序后i依旧在j前面
				newData[index++] = data[i++];
			} else {
				newData[index++] = data[j++];
			}
		}
		// 将排好序的元素复制回原数组
		for (i = start1; i <= end2; i++) {
			data[i] = (T) newData[i];
		}
	}

	public static void main(String[] args) {
		Random ran = new Random();
		Integer[] data = new Integer[10000];
		Integer[] data2 = new Integer[data.length];
		Integer[] data3 = new Integer[data.length];
		for (int i = 0; i < data.length; i++) {
			data[i] = ran.nextInt(1000);
			data2[i] = data[i];
			data3[i] = data[i];
		}
		MergeSort.sort(data);
		QuickSort.sort(data2);
		RadixSort.sort(data3);
		// SortUtils.printArray(data);
		// SortUtils.printArray(data2);
		// SortUtils.printArray(data3);
	}
}
