package com.vicky.datastructure.tree.trie;

/**
 * <p>
 * 一个支持前缀查找以及精确查找的Trie树
 * </p>
 *
 * @author Vicky
 * @email vicky01200059@163.com
 * @2015年11月23日
 *
 */
public class PrefixTrie {
	private TrieNode root = new TrieNode('a');// TrieTree的根节点

	/**
	 * 插入
	 * 
	 * @param word
	 */
	public void insertWord(String word) {
		TrieNode index = this.root;
		for (char c : word.toLowerCase().toCharArray()) {
			index = index.addChild(c);
			index.addPrefixCount();
		}
		index.addCount();
		return;
	}

	/**
	 * 查找
	 * 
	 * @param word
	 * @return
	 */
	public boolean selectWord(String word) {
		TrieNode index = this.root;
		for (char c : word.toLowerCase().toCharArray()) {
			index = index.getChild(c);
			if (null == index) {
				return false;
			}
		}
		return index.getCount() > 0;
	}

	/**
	 * 查找前缀出现的次数
	 * 
	 * @param prefix
	 * @return
	 */
	public int selectPrefixCount(String prefix) {
		TrieNode index = this.root;
		for (char c : prefix.toLowerCase().toCharArray()) {
			index = index.getChild(c);
			if (null == index) {
				return 0;
			}
		}
		return index.getPrefixCount();
	}

	/**
	 * TrieTree的节点
	 */
	private class TrieNode {
		/** 该节点的字符 */
		private final char nodeChar;//
		/** 一个TrieTree的节点的子节点 */
		private TrieNode[] childNodes = null;
		private int count = 0;// 单词数量，用于判断一个单词是否存在
		private int prefixCount = 0;// 前缀数量，用于查找该前缀出现的次数

		public TrieNode(char nodeChar) {
			super();
			this.nodeChar = nodeChar;
		}

		public TrieNode addChild(char ch) {
			int index = ch - 'a';
			if (null == childNodes) {
				this.childNodes = new TrieNode[26];
			}
			if (null == childNodes[index]) {
				childNodes[index] = new TrieNode(ch);
			}
			return childNodes[index];
		}

		public TrieNode getChild(char ch) {
			int index = ch - 'a';
			if (null == childNodes || null == childNodes[index]) {
				return null;
			}
			return childNodes[index];
		}

		public void addCount() {
			this.count++;
		}

		public int getCount() {
			return this.count;
		}

		public void addPrefixCount() {
			this.prefixCount++;
		}

		public int getPrefixCount() {
			return this.prefixCount;
		}

		@Override
		public String toString() {
			return "TrieNode [nodeChar=" + nodeChar + "]";
		}

	}
}
