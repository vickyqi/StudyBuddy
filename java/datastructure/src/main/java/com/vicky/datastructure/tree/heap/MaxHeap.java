package com.vicky.datastructure.tree.heap;

import java.util.Arrays;

/**
 * <p>
 * 最大堆
 * </p>
 *
 * @author Vicky
 * @email vicky01200059@163.com
 * @2015年11月18日
 *
 */
public class MaxHeap extends Heap {
	public MaxHeap(int[] data) {
		super(data);
	}

	/**
	 * {@inheritDoc}
	 */
	public Heap buildHeap() {
		// 从最后一个节点的父节点开始构建堆
		int start = getParentIndex(length - 1);
		for (; start >= 0; start--) {
			adjustHeap(start);
		}
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public Heap remove() {
		// 将最后一个节点与头结点交换
		swap(0, length - 1);
		// 重新复制一个数组
		int[] newData = new int[length - 1];
		System.arraycopy(data, 0, newData, 0, length - 1);
		this.data = newData;
		this.length = length - 1;
		// 从头开始调整堆
		adjustHeap(0);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public Heap insert(int value) {
		// 插入到数组最后
		int[] newData = new int[length + 1];
		System.arraycopy(data, 0, newData, 0, length);
		newData[length] = value;
		this.data = newData;
		this.length = length + 1;
		// 重新构建堆
		buildHeap();
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public void adjustHeap(int node) {
		int right = getRightChildIndex(node);// 右孩子
		int left = getLeftChildIndex(node);// 左孩子
		int max = node;// 三者最大的节点的索引
		if (right < length && data[right] > data[max]) {
			max = right;
		}
		if (left < length && data[left] > data[max]) {
			max = left;
		}
		if (max != node) {// 需要调整
			swap(node, max);
			adjustHeap(max);// 递归调整与根节点进行交换的节点，保证下层也是堆
		}
	}

	public static void main(String[] args) {
		int[] data = new int[10];
		for (int i = 0; i < data.length; i++) {
			data[i] = (int) (Math.random() * 100);
		}
		System.out.println(Arrays.toString(data));
		Heap heap = new MaxHeap(data);
		heap.buildHeap().print();
		heap.remove().print();
		heap.insert((int) (Math.random() * 100)).print();
		heap.insert((int) (Math.random() * 100)).print();
		heap.insert((int) (Math.random() * 100)).print();
	}
}
