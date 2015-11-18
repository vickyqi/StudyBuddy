package com.vicky.datastructure.tree.heap;

import java.util.Arrays;

/**
 * 
 * <p>
 * 数据结构——树——堆
 * </p>
 *
 * @author Vicky
 * @email vicky01200059@163.com
 * @2015年11月18日
 *
 */
public abstract class Heap {
	protected int[] data;
	protected int length = 0;

	public Heap(int[] data) {
		this.data = data;
		this.length = data.length;
	}

	/**
	 * 构建堆
	 */
	public abstract Heap buildHeap();

	/**
	 * 删除一个节点，只能删除根节点
	 * 
	 * @return
	 */
	public abstract Heap remove();

	/**
	 * 插入一个节点，只能插入到最后
	 * 
	 * @param value
	 * @return
	 */
	public abstract Heap insert(int value);

	/**
	 * 调整以node为根节点的堆
	 * 
	 * @param node
	 */
	public abstract void adjustHeap(int node);

	/**
	 * 交换元素
	 * 
	 * @param n1
	 * @param n2
	 */
	public void swap(int n1, int n2) {
		int temp = data[n1];
		data[n1] = data[n2];
		data[n2] = temp;
	}

	/**
	 * 获取node的父节点的索引
	 * 
	 * @param node
	 * @return
	 */
	protected int getParentIndex(int node) {
		return (node - 1) >> 1;
	}

	/**
	 * 获取node的右孩子索引
	 * 
	 * @param node
	 * @return
	 */
	protected int getRightChildIndex(int node) {
		return (node << 1) + 1;
	}

	/**
	 * 获取node的左孩子索引
	 * 
	 * @param node
	 * @return
	 */
	protected int getLeftChildIndex(int node) {
		return (node << 1) + 2;
	}

	/**
	 * 打印堆
	 */
	protected void print() {
		System.out.println(Arrays.toString(data));
	}
}
