package com.vicky.datastructure.sort;

import java.util.Random;

/**
 * 交换排序：冒泡排序
 * 
 * 时间复杂度：O(n^2)
 * 
 * 空间复杂度：O(1)
 * 
 * 稳定性：稳定
 * 
 * @author Vicky
 * 
 */
public class BubbleSort {

	/**
	 * 未改进的冒泡排序
	 * 
	 * @param <T>
	 * @param data
	 */
	public static <T extends Comparable<T>> void sort(T[] data) {
		if (null == data) {
			throw new NullPointerException("data");
		}
		if (data.length == 1) {
			return;
		}
		// n-1趟遍历
		for (int i = 0; i < data.length - 1; i++) {
			// 每次遍历从0开始依次比较相邻元素
			for (int j = 0; j < data.length - 1 - i; j++) {
				// 前面元素>后面元素则交换
				if (data[j].compareTo(data[j + 1]) > 0) {
					T temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 改进后冒泡排序
	 * 
	 * 改进原理：如果一次遍历过程未发生交换，则说明序列已经是有序的，故无需再进行遍历。
	 * 
	 * @param <T>
	 * @param data
	 */
	public static <T extends Comparable<T>> void sortImprove(T[] data) {
		if (null == data) {
			throw new NullPointerException("data");
		}
		if (data.length == 1) {
			return;
		}
		boolean exchange = false;// 记录一趟遍历是否发生交换
		// n-1趟遍历
		for (int i = 0; i < data.length - 1; i++) {
			// 每次遍历从0开始依次比较相邻元素
			for (int j = 0; j < data.length - 1 - i; j++) {
				// 前面元素>后面元素则交换
				if (data[j].compareTo(data[j + 1]) > 0) {
					T temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					exchange = true;
				}
			}
			// 如果本次遍历未发生交换，则说明序列已是有序的，则无需继续遍历
			if (!exchange) {
				return;
			}
		}
	}

	/**
	 * 改进后冒泡排序
	 * 
	 * 改进原理：在冒泡排序的每趟扫描中，记住最后一次交换发生的位置lastexchange也能有所帮助。因为该位置之后的部分已经是有序的(未发生交换，
	 * 所以是有序)，
	 * 故下一趟排序开始的时候，只需处理0到lastexchange部分，lastexchange到n-1是有序区。同时如果未发生交换则退出即可
	 * 
	 * @param <T>
	 * @param data
	 */
	public static <T extends Comparable<T>> void sortImprove2(T[] data) {
		if (null == data) {
			throw new NullPointerException("data");
		}
		if (data.length == 1) {
			return;
		}
		int lastChange = data.length - 1;// 记录一趟遍历最后一次发生交换的位置，该位置之后是有序的
		// 上次遍历发生交换则lastChange>0，继续遍历
		while (lastChange > 0) {
			// 本次遍历从0开始到上次遍历最后一次交换的位置结束
			int end = lastChange;
			lastChange = 0;
			// 每次遍历从0开始依次比较相邻元素
			for (int j = 0; j < end; j++) {
				// 前面元素>后面元素则交换
				if (data[j].compareTo(data[j + 1]) > 0) {
					T temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					lastChange = j + 1;
				}
			}
		}
	}

	public static void main(String[] args) {
		// 用于记录三种冒泡排序的用时
		long[] useTime1 = new long[10];
		long[] useTime2 = new long[10];
		long[] useTime3 = new long[10];
		// 循环测试10次，取均值
		for (int times = 0; times < 10; times++) {
			// 构建10000个元素的序列进行排序
			Random ran = new Random();
			Integer[] data = new Integer[10000];
			for (int i = 0; i < data.length; i++) {
				data[i] = ran.nextInt(10000000);
			}
			// 使用System.arraycopy复制三个数组分别用于排序
			Integer[] data1 = new Integer[data.length];
			Integer[] data2 = new Integer[data.length];
			Integer[] data3 = new Integer[data.length];
			System.arraycopy(data, 0, data1, 0, data.length);
			System.arraycopy(data, 0, data2, 0, data.length);
			System.arraycopy(data, 0, data3, 0, data.length);
			// 分别记录三种冒泡排序的用时
			long start = System.nanoTime();
			BubbleSort.sort(data1);
			useTime1[times] = (System.nanoTime() - start) / 1000000;
			// SortUtils.printArray(data1);
			start = System.nanoTime();
			BubbleSort.sortImprove(data2);
			useTime2[times] = (System.nanoTime() - start) / 1000000;
			start = System.nanoTime();
			// SortUtils.printArray(data2);
			BubbleSort.sortImprove2(data3);
			useTime3[times] = (System.nanoTime() - start) / 1000000;
			// SortUtils.printArray(data3);
		}
		// 计算用时最大值，最小值，均值
		long[] res1 = SortUtils.countArray(useTime1);
		long[] res2 = SortUtils.countArray(useTime2);
		long[] res3 = SortUtils.countArray(useTime3);
		System.out.println("method\tmax\tmin\tavg\t");
		System.out.println("sort" + "\t" + res1[0] + "\t" + res1[1] + "\t" + res1[2]);
		System.out.println("sortImprove" + "\t" + res2[0] + "\t" + res2[1] + "\t" + res2[2]);
		System.out.println("sortImprove2" + "\t" + res3[0] + "\t" + res3[1] + "\t" + res3[2]);
		// 测试结果，第一种改进方法效率比不改进还差一些，
		// 可能由于出现提前完成排序的可能性较小，每次遍历加入了过多的赋值以及判断操作导致效率反而降低
		// 第二种改进方法还是有一些效果的
		// method max min avg
		// sort 1190 1073 1123
		// sortImprove 1258 1097 1146
		// sortImprove2 1205 1056 1099
	}
}
