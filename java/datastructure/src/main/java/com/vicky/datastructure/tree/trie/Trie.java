package com.vicky.datastructure.tree.trie;

/**
 * <p>
 * 最简单的Trie树结构，仅表示出Trie树的结构，实际应用需进行扩展
 * </p>
 *
 * @author Vicky
 * @email vicky01200059@163.com
 * @2015年11月23日
 *
 */
public class Trie {
	protected TrieNode root = new TrieNode('a');// TrieTree的根节点

	/**
	 * 插入
	 * 
	 * @param word
	 */
	public void insertWord(String word) {
		TrieNode index = this.root;
		for (char c : word.toLowerCase().toCharArray()) {
			index = index.addChild(c);
		}
		return;
	}

	/**
	 * TrieTree的节点
	 */
	private class TrieNode {
		/** 该节点的字符 */
		private final char nodeChar;//
		/** 一个TrieTree的节点的子节点 */
		private TrieNode[] childNodes = null;

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

		@Override
		public String toString() {
			return "TrieNode [nodeChar=" + nodeChar + "]";
		}

	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insertWord("Vicky");
	}
}
