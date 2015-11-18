package com.vicky.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * <p>
 * 插入排序：直接插入排序
 * 
 * 基本思想:
 * 将一个记录插入到已排序好的有序表中，从而得到一个新，记录数增1的有序表。即：先将序列的第1个记录看成是一个有序的子序列，然后从第2个记录逐个进行插入，
 * 直至整个序列有序为止。
 * 
 * 时间复杂度：O(n(2))
 * 
 * 空间复杂度：θ(1)
 * 
 * 稳定性：稳定
 * </p>
 * 
 * @author Vicky
 * @date 2015-8-12
 */
public class StraightInsertionSort {
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
		// 选择起始点
		int st = 1;
		T temp;
		for (; st < data.length; st++) {
			temp = data[st];
			int j = st - 1;
			// 如果比其前一个数大，则无需移动，直接处理下一个
			if (temp.compareTo(data[j]) >= 0) {
				continue;
			}
			// 依次与已排好序部分每个数进行比较，直至找到一个比其小的数或者找到头
			for (; j >= 0 && temp.compareTo(data[j]) < 0; j--) {
				data[j + 1] = data[j];
			}
			data[j + 1] = temp;
		}
	}

	public static void main(String[] args) {
		Random ran = new Random();
		Integer[] data = new Integer[100000];
		for (int i = 0; i < data.length; i++) {
			data[i] = ran.nextInt(10000000);
		}
		StraightInsertionSort.sort(data);
		System.out.println(Arrays.toString(data));
	}
}
