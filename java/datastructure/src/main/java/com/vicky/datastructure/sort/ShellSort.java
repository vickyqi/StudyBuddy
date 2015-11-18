package com.vicky.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * <p>
 * 希尔排序
 * 
 * 基本思想：
 * 先将整个待排元素序列分割成若干个子序列（由相隔某个“增量”的元素组成的）分别进行直接插入排序，然后依次缩减增量再进行排序，待整个序列中的元素基本有序（
 * 增量足够小）时，再对全体元素进行一次直接插入排序。因为直接插入排序在元素基本有序的情况下（接近最好情况），效率是很高的，
 * 因此希尔排序在时间效率上比前两种方法有较大提高。
 * 
 * 时间复杂度：根据增量(步长)的不同，最坏情况下的时间复杂度不同。 步长序列 Best Worst n/2(i) O(n) O(n(2)) 2(k)-1
 * O(n) O(n(3/2)) 2(i)3(j) O(n) O(nlog(2)n)
 * 来源：https://zh.wikipedia.org/wiki/%E5%B8%8C%E5%B0%94%E6%8E%92%E5%BA%8F(维基百科)
 * 
 * 空间复杂度：θ(1)
 * 
 * 稳定性：不稳定
 * </p>
 * 
 * @author Vicky
 * @date 2015-8-12
 */
public class ShellSort {
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
		int d = data.length / 2;// 增量
		while (d >= 1) {
			for (int i = 0; i < d; i++) {
				// 对同一组元素进行直接插入排序data[i], data[i+d],..., data[i+nd]
				int st = i + d;// 取第二个元素作为分界点
				for (; st < data.length; st += d) {
					T temp = data[st];
					int j = st - d;
					while (j >= 0 && temp.compareTo(data[j]) < 0) {
						data[j + d] = data[j];
						j -= d;
					}
					data[j + d] = temp;
				}
			}
			d = d / 2;
		}
	}

	public static void main(String[] args) {
		Random ran = new Random();
		Integer[] data = new Integer[100000];
		for (int i = 0; i < data.length; i++) {
			data[i] = ran.nextInt(10000000);
		}
		ShellSort.sort(data);
		System.out.println(Arrays.toString(data));
	}
}
