package com.vicky.datastructure.tree.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class TrieUsedTest {
	private PrefixTrie trie;

	@Before
	public void before() throws IOException {
		Pattern pattern = Pattern.compile("[a-zA-Z]+");

		// 从文件中读取单词，构建TriedTree
		InputStreamReader read = new InputStreamReader(this.getClass().getResourceAsStream("words.txt"));
		BufferedReader reader = new BufferedReader(read);
		trie = new PrefixTrie();
		String line = null;
		while (null != (line = reader.readLine())) {
			line = line.trim();
			if (!pattern.matcher(line).matches()) {// 去除非法单词，如包含“-”
				continue;
			}
			trie.insertWord(line);
		}
	}

	/**
	 * 测试使用TriedTree搜索前缀出现的次数
	 */
	@Test
	public void searchPrefixWords() {
		String prefix = "vi";
		System.out.println(trie.selectPrefixCount(prefix));
		System.out.println(trie.selectWord("Vicky"));
	}
}
