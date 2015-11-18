package com.vicky.datastructure.sort;

import java.util.Random;
import java.util.Scanner;

/**
 * <p>
 * 八大排序算法对比
 * </p>
 * 
 * @author Vicky
 * @date 2015-8-21
 */
public class SortComparison {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = -1;
		int maxValue = -1;
		int times = 10;
		while (true) {
			// 从命令行输入元素数量，以及最大值，格式：10,1000，输入quit结束
			String input = scan.next();
			if ("quit".equals(input)) {
				break;
			}
			String[] strs = input.split(",");
			num = Integer.parseInt(strs[0]);
			maxValue = Integer.parseInt(strs[1]);
			System.out.println("Sort Data Num = " + num + ", MaxValue = " + maxValue);

			// 用于记录各排序的用时
			long[] insertTimes = new long[times];
			long[] shellTimes = new long[times];
			long[] selectTimes = new long[times];
			long[] heapTimes = new long[times];
			long[] bubbleTimes = new long[times];
			long[] quickTimes = new long[times];
			long[] mergeTimes = new long[times];
			long[] radixTimes = new long[times];
			// 循环测试10次，取均值
			for (int j = 0; j < times; j++) {
				Random ran = new Random();
				Integer[] data = new Integer[num];
				Integer[] data1 = new Integer[data.length];
				Integer[] data2 = new Integer[data.length];
				Integer[] data3 = new Integer[data.length];
				Integer[] data4 = new Integer[data.length];
				Integer[] data5 = new Integer[data.length];
				Integer[] data6 = new Integer[data.length];
				Integer[] data7 = new Integer[data.length];
				Integer[] data8 = new Integer[data.length];
				for (int i = 0; i < data.length; i++) {
					data[i] = ran.nextInt(maxValue);
					data1[i] = data[i];
					data2[i] = data[i];
					data3[i] = data[i];
					data4[i] = data[i];
					data5[i] = data[i];
					data6[i] = data[i];
					data7[i] = data[i];
					data8[i] = data[i];
				}
				// 插入排序
//				insertTimes[j] = testStraightInsertionSort(data1);
				shellTimes[j] = testShellSort(data2);
				// 选择排序
//				selectTimes[j] = testStraightSelectionSort(data3);
				heapTimes[j] = testHeapSort(data4);
				// 交换排序
//				bubbleTimes[j] = testBubbleSort(data5);
				quickTimes[j] = testQuickSort(data6);
				// 归并排序
				mergeTimes[j] = testMergeSort(data7);
				// 基数排序
				radixTimes[j] = testRadixSort(data8);
			}

			// 计算用时最大值，最小值，均值
			long[] insertRes = SortUtils.countArray(insertTimes);
			long[] shellRes = SortUtils.countArray(shellTimes);
			long[] selectRes = SortUtils.countArray(selectTimes);
			long[] heapRes = SortUtils.countArray(heapTimes);
			long[] bubbleRes = SortUtils.countArray(bubbleTimes);
			long[] quickRes = SortUtils.countArray(quickTimes);
			long[] mergeRes = SortUtils.countArray(mergeTimes);
			long[] radixRes = SortUtils.countArray(radixTimes);
			System.out.println("method\tmax\tmin\tavg\t");
			System.out.println("InsertionSort" + "\t" + insertRes[0] + "\t" + insertRes[1] + "\t" + insertRes[2]);
			System.out.println("ShellSort" + "\t" + shellRes[0] + "\t" + shellRes[1] + "\t" + shellRes[2]);
			System.out.println("SelectionSort" + "\t" + selectRes[0] + "\t" + selectRes[1] + "\t" + selectRes[2]);
			System.out.println("HeapSort" + "\t" + heapRes[0] + "\t" + heapRes[1] + "\t" + heapRes[2]);
			System.out.println("BubbleSort" + "\t" + bubbleRes[0] + "\t" + bubbleRes[1] + "\t" + bubbleRes[2]);
			System.out.println("QuickSort" + "\t" + quickRes[0] + "\t" + quickRes[1] + "\t" + quickRes[2]);
			System.out.println("MergeSort" + "\t" + mergeRes[0] + "\t" + mergeRes[1] + "\t" + mergeRes[2]);
			System.out.println("RadixSort" + "\t" + radixRes[0] + "\t" + radixRes[1] + "\t" + radixRes[2]);
		}
		scan.close();
	}

	public static <T extends Comparable<T>> long testStraightInsertionSort(T[] data) {
		long start = System.nanoTime();
		StraightInsertionSort.sort(data);
		return (System.nanoTime() - start) / 1000;
	}

	public static <T extends Comparable<T>> long testShellSort(T[] data) {
		long start = System.nanoTime();
		ShellSort.sort(data);
		return (System.nanoTime() - start) / 1000;
	}

	public static <T extends Comparable<T>> long testStraightSelectionSort(T[] data) {
		long start = System.nanoTime();
		StraightSelectionSort.sort(data);
		return (System.nanoTime() - start) / 1000;
	}

	public static <T extends Comparable<T>> long testHeapSort(T[] data) {
		long start = System.nanoTime();
		HeapSort.sort(data);
		return (System.nanoTime() - start) / 1000;
	}

	public static <T extends Comparable<T>> long testBubbleSort(T[] data) {
		long start = System.nanoTime();
		BubbleSort.sort(data);
		return (System.nanoTime() - start) / 1000;
	}

	public static <T extends Comparable<T>> long testQuickSort(T[] data) {
		long start = System.nanoTime();
		QuickSort.sort(data);
		return (System.nanoTime() - start) / 1000;
	}

	public static <T extends Comparable<T>> long testMergeSort(T[] data) {
		long start = System.nanoTime();
		MergeSort.sort(data);
		return (System.nanoTime() - start) / 1000;
	}

	public static long testRadixSort(Integer[] data) {
		long start = System.nanoTime();
		RadixSort.sort(data);
		return (System.nanoTime() - start) / 1000;
	}
}
