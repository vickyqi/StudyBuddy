package com.vicky.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * <p>
 * 选择排序：堆排序
 * 
 * 时间复杂度：O(nlgn)
 * </p>
 * 
 * @author Vicky
 * @date 2015-8-13
 * @param <T>
 */
public class HeapSort {
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
		buildMaxHeap(data);
		// 末尾与头交换，交换后调整最大堆
		for (int i = data.length - 1; i > 0; i--) {
			T temp = data[0];
			data[0] = data[i];
			data[i] = temp;
			adjustMaxHeap(data, i, 0);
		}
	}

	/**
	 * 构建最大堆
	 * 
	 * @param <T>
	 * @param data
	 * @param index
	 *            序列起始点
	 */
	private static <T extends Comparable<T>> void buildMaxHeap(T[] data) {
		// 自下而上构建最大堆，即从最后一个元素的父节点开始构建最大堆
		int start = getParentIndex(data.length - 1);
		for (; start >= 0; start--) {
			adjustMaxHeap(data, data.length, start);
		}
	}

	/**
	 * 调整最大堆，自下而上
	 * 
	 * @param <T>
	 * @param data
	 * @param heapsize
	 *            堆的大小，即对data中从0开始到heapsize之间的元素构建最大堆
	 * @param index
	 *            当前需要构建最大堆的位置
	 */
	private static <T extends Comparable<T>> void adjustMaxHeap(T[] data, int heapsize, int index) {
		// 获取该元素左右子元素
		int left = getLeftChildIndex(index);
		int right = getRightChildIndex(index);
		int max = index;
		// 取三个元素中最大值与父节点进行交换
		if (left < heapsize && data[max].compareTo(data[left]) < 0) {
			max = left;
		}
		if (right < heapsize && data[max].compareTo(data[right]) < 0) {
			max = right;
		}
		if (max != index) {
			swap(data, index, max);
			adjustMaxHeap(data, heapsize, max);
		}
	}

	/**
	 * 交换元素
	 * 
	 * @param <T>
	 * @param data
	 * @param i
	 * @param j
	 */
	private static <T extends Comparable<T>> void swap(T[] data, int i, int j) {
		T temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	/**
	 * 获取父节点位置
	 * 
	 * @param i
	 * @return
	 */
	private static int getParentIndex(int i) {
		return (i - 1) >> 1;
	}

	/**
	 * 获取左子节点位置
	 * 
	 * @param current
	 * @return
	 */
	private static int getLeftChildIndex(int current) {
		return (current << 1) + 1;
	}

	/**
	 * 获取右子节点位置
	 * 
	 * @param current
	 * @return
	 */
	private static int getRightChildIndex(int current) {
		return (current << 1) + 2;
	}

	public static void main(String[] args) {
		Random ran = new Random();
		Integer[] data = new Integer[100000];
		for (int i = 0; i < data.length; i++) {
			data[i] = ran.nextInt(100000000);
		}
		HeapSort.sort(data);
		System.out.println(Arrays.toString(data));
	}
}
