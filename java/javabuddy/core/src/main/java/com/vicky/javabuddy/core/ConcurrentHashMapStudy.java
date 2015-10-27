package com.vicky.javabuddy.core;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

/**
 * <p>
 * 测试、应用ConcurrentHashMap，可参考{@link ConcurrentHashMap} 学习源码
 * </p>
 * 
 * @author Vicky
 *
 * @2015年10月9日
 *
 */
public class ConcurrentHashMapStudy {
	ConcurrentHashMap<String, Object> conMap;
	HashMap<String, Object> map;

	@Test
	public void test() {
		int times = 10;
		long[] times1 = new long[times];
		long[] times2 = new long[times];
		for (int i = 0; i < times; i++) {
			times1[i] = putConcurrentHashMap();
			times2[i] = putNormalHashMap();
		}
		System.out.println("putConcurrentHashMap avg time: " + countAvg(times1));
		System.out.println("putNormalHashMap avg time: " + countAvg(times2));
	}

	public long putConcurrentHashMap() {
		long start = System.nanoTime();
		Random ran = new Random();
		ConcurrentHashMap<String, Object> conMap = new ConcurrentHashMap<String, Object>(10000);
		for (int i = 0; i < 10000; i++) {
			int val = ran.nextInt(1000000);
			conMap.put(String.valueOf(i), val);
		}
		long time = System.nanoTime() - start;
		System.out.println("putConcurrentHashMap: " + time);
		return time;
	}

	public long putNormalHashMap() {
		long start = System.nanoTime();
		Random ran = new Random();
		HashMap<String, Object> map = new HashMap<String, Object>(10000);
		for (int i = 0; i < 10000; i++) {
			int val = ran.nextInt(1000000);
			map.put(String.valueOf(i), val);
		}
		long time = System.nanoTime() - start;
		System.out.println("putNormalHashMap: " + time);
		return time;
	}

	public long countAvg(long[] data) {
		long sum = 0l;
		for (long l : data) {
			sum += l;
		}
		return sum / data.length;
	}
}
