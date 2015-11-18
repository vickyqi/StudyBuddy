package com.vicky.datastructure.sort;

import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

public class SortComparison2 {

	/**
	 * 比较全部排序算法
	 */
	@Test
	public void testAll() {
		Scanner scan = new Scanner(System.in);
		int num = -1;
		int maxValue = -1;
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
			long insertTimes = testStraightInsertionSort(data1);
			long shellTimes = testShellSort(data2);
			// 选择排序
			long selectTimes = testStraightSelectionSort(data3);
			long heapTimes = testHeapSort(data4);
			// 交换排序
			long bubbleTimes = testBubbleSort(data5);
			long quickTimes = testQuickSort(data6);
			// 归并排序
			long mergeTimes = testMergeSort(data7);
			// 基数排序
			long radixTimes = testRadixSort(data8);

			System.out.println("method       \ttime(ms)");
			System.out.println("InsertionSort\t" + insertTimes);
			System.out.println("ShellSort    \t" + shellTimes);
			System.out.println("SelectionSort\t" + selectTimes);
			System.out.println("HeapSort     \t" + heapTimes);
			System.out.println("BubbleSort   \t" + bubbleTimes);
			System.out.println("QuickSort    \t" + quickTimes);
			System.out.println("MergeSort    \t" + mergeTimes);
			System.out.println("RadixSort    \t" + radixTimes);
		}
		scan.close();
	}

	/**
	 *测试时间复杂度为O(n^2)的排序
	 */
	@Test
	public void testBase() {
		Scanner scan = new Scanner(System.in);
		int num = -1;
		int maxValue = -1;
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

			Random ran = new Random();
			Integer[] data = new Integer[num];
			Integer[] data1 = new Integer[data.length];
			Integer[] data2 = new Integer[data.length];
			Integer[] data3 = new Integer[data.length];
			Integer[] data4 = new Integer[data.length];
			for (int i = 0; i < data.length; i++) {
				data[i] = ran.nextInt(maxValue);
				data1[i] = data[i];
				data2[i] = data[i];
				data3[i] = data[i];
				data4[i] = data[i];
			}
			// 插入排序
			long insertTimes = testStraightInsertionSort(data1);
			long shellTimes = testShellSort(data2);
			// 选择排序
			long selectTimes = testStraightSelectionSort(data3);
			// 交换排序
			long bubbleTimes = testBubbleSort(data4);

			System.out.println("method       \ttime(ms)");
			System.out.println("InsertionSort\t" + insertTimes);
			System.out.println("ShellSort    \t" + shellTimes);
			System.out.println("SelectionSort\t" + selectTimes);
			System.out.println("BubbleSort   \t" + bubbleTimes);
		}
		scan.close();
	}

	/**
	 * 比较O(nlgn)左右的排序算法
	 * 
	 * 这里把希尔加上是因为虽然希尔时间复杂度是O(n^2)但是从实际结果来看其效率还是较高的，所以拿来跟O(nlgn)一起对比
	 */
	@Test
	public void testGood() {
		Scanner scan = new Scanner(System.in);
		int num = -1;
		int maxValue = -1;
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

			Random ran = new Random();
			Integer[] data = new Integer[num];
			Integer[] data1 = new Integer[data.length];
			Integer[] data2 = new Integer[data.length];
			Integer[] data3 = new Integer[data.length];
			Integer[] data4 = new Integer[data.length];
			Integer[] data5 = new Integer[data.length];
			for (int i = 0; i < data.length; i++) {
				data[i] = ran.nextInt(maxValue);
				data1[i] = data[i];
				data2[i] = data[i];
				data3[i] = data[i];
				data4[i] = data[i];
				data5[i] = data[i];
			}
			// 插入排序
			long shellTimes = testShellSort(data1);
			// 选择排序
			long heapTimes = testHeapSort(data2);
			// 交换排序
			long quickTimes = testQuickSort(data3);
			// 归并排序
			long mergeTimes = testMergeSort(data4);
			// 基数排序
			long radixTimes = testRadixSort(data5);

			System.out.println("method       \ttime(ms)");
			System.out.println("ShellSort    \t" + shellTimes);
			System.out.println("HeapSort     \t" + heapTimes);
			System.out.println("QuickSort    \t" + quickTimes);
			System.out.println("MergeSort    \t" + mergeTimes);
			System.out.println("RadixSort    \t" + radixTimes);
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
