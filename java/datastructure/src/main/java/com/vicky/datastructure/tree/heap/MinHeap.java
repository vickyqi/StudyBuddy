package com.vicky.datastructure.tree.heap;

import java.util.Arrays;

/**
 * <p>
 * 最小堆
 * </p>
 *
 * @author Vicky
 * @email vicky01200059@163.com
 * @2015年11月18日
 *
 */
public class MinHeap extends Heap {
	public MinHeap(int[] data) {
		super(data);
	}

	/**
	 * {@inheritDoc}
	 */
	public Heap buildHeap() {
		// 从最后一个节点的父节点开始构建堆
		int start = getParentIndex(length - 1);
		for (; start >= 0; start--) {
			adjustDownHeap(start);
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
		adjustDownHeap(0);
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
		// 从最后一个节点开始自下而上调整堆
		adjustUpHeap(this.length - 1);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public void adjustDownHeap(int node) {
		int right = getRightChildIndex(node);// 右孩子
		int left = getLeftChildIndex(node);// 左孩子
		int min = node;// 三者最大的节点的索引
		if (right < length && data[right] < data[min]) {
			min = right;
		}
		if (left < length && data[left] < data[min]) {
			min = left;
		}
		if (min != node) {// 需要调整
			swap(node, min);
			adjustDownHeap(min);// 递归调整与根节点进行交换的节点，保证下层也是堆
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void adjustUpHeap(int node) {
		int parent = getParentIndex(node);// 父节点
		if (parent >= 0 && data[parent] > data[node]) {
			swap(node, parent);
			adjustUpHeap(parent);// 递归调整与根节点进行交换的节点，保证上层也是堆
		}
	}

	public static void main(String[] args) {
		int[] data = new int[10];
		for (int i = 0; i < data.length; i++) {
			data[i] = (int) (Math.random() * 100);
		}
		System.out.println(Arrays.toString(data));
		Heap heap = new MinHeap(data);
		heap.buildHeap().print();
		heap.remove().print();
		heap.insert((int) (Math.random() * 100)).print();
	}
}
