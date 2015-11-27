package com.vicky.datastructure.tree;

import java.util.Arrays;

/**
 * <p>
 * 后缀树的实现：Ukkonen构造法， 
 * 论文翻译文章：<code>http://www.oschina.net/translate/ukkonens-suffix-tree-algorithm-in-plain-english</code>
 * </p>
 * <p>
 * <b>论文中使用边来保存字符信息，此处实现使用节点保存字符信息，没有差别。</b>
 * 同时树的结构通过子节点和兄弟节点保存，即遍历一个节点的子节点，需先找到其直接子节点，然后通过该子节点访问其兄弟节点找到全部子节点
 * </p>
 *
 * @author Vicky
 * @email vicky01200059@163.com
 * @2015年11月25日
 *
 */
public class SuffixTree {
	private Node root = new Node(new char[0]);// 根节点
	// active point，一个三元组：(active_node,active_edge,active_length)
	// active_node是当前的活动点，用节点代表，active_edge是活动的边，这里用节点来表示，active_length是活动的长度
	private ActivePoint activePoint = new ActivePoint(root, null, 0);
	private int reminder = 0;// remainder，表示还剩多少后缀需要插入

	/**
	 * 构建后缀树
	 * 
	 * @param word
	 */
	public void build(String word) {
		int index = 0;
		char[] chars = word.toCharArray();
		while (index < chars.length) {// 循环建立后缀
			int currenctIndex = index++;// 保存当前的位置
			char w = chars[currenctIndex];// 当前的后缀字符
			
			this.print();// 打印
			System.out.println();
			System.out.println("当前插入后缀：" + w + "========");

			if (find(w)) {// 查找是否存在保存有当前后缀字符的节点
				reminder++;// 存在，则将reminder+1，activePoint.length+1，然后返回即可
				continue;
			}

			// 不存在的话，如果reminder==0表示之前在该字符之前未剩余有其他带插入的后缀字符，所以直接插入该后缀字符即可
			if (reminder == 0) {
				// 直接在当前活动节点插入一个节点即可
				// 这里插入的节点包含的字符是从当前字符开始该字符串剩余的全部字符，这里是一个优化，
				// 优化参考自：http://blog.csdn.net/v_july_v/article/details/6897097 (3.6、归纳, 反思, 优化)
				Node node = new Node(Arrays.copyOfRange(chars, currenctIndex, chars.length));
				// 如果当前活动点无子节点，则将新建的节点作为其子节点即可，否则循环遍历子节点(通过兄弟节点进行保存)
				Node child = activePoint.point.child;
				if (null == child) {
					activePoint.point.child = node;
				} else {
					while (null != child.brother) {
						child = child.brother;
					}
					child.brother = node;
				}
			} else {
				// 如果reminder>0，则说明该字符之前存在剩余字符，需要进行分割，然后插入新的后缀字符
				Node splitNode = activePoint.index;// 待分割的节点即为活动边(active_edge)
				// 创建切分后的节点，放到当前节点的子节点
				// 该节点继承了当前节点的子节点以及后缀节点信息
				Node node = new Node(Arrays.copyOfRange(splitNode.chars, activePoint.length, splitNode.chars.length));// 从活动边长度开始截取剩余字符作为子节点
				node.child = splitNode.child;
				node.suffixNode = splitNode.suffixNode;
				splitNode.child = node;
				splitNode.suffixNode = null;
				// 创建新插入的节点，放到当前节点的子节点(通过子节点的兄弟节点保存)
				Node newNode = new Node(Arrays.copyOfRange(chars, currenctIndex, chars.length));// 插入新的后缀字符
				splitNode.child.brother = newNode;
				splitNode.chars = Arrays.copyOfRange(splitNode.chars, 0, activePoint.length);// 修改当前节点的字符

				// 分割完成之后需根据规则1和规则3进行区分对待
				// 按照规则1进行处理
				if (root == activePoint.point) {// 活动节点是根节点的情况
					// activePoint.point == root
				// 按照规则3进行处理
				} else if (null == activePoint.point.suffixNode) {// 无后缀节点，则活动节点变为root
					activePoint.point = root;
				} else {// 否则活动节点变为当前活动节点的后缀节点
					activePoint.point = activePoint.point.suffixNode;
				}
				// 活动边和活动边长度都重置
				activePoint.index = null;
				activePoint.length = 0;
				// 递归处理剩余的待插入后缀
				innerSplit(chars, currenctIndex, splitNode);
			}
		}
	}

	/**
	 * 处理剩余的待插入后缀
	 * @param chars	构建后缀树的全部字符
	 * @param currenctIndex	当前已处理到的字符位置
	 * @param prefixNode 前继节点，即已经进行分割的节点，用于标识后缀节点
	 */
	private void innerSplit(char[] chars, int currenctIndex, Node prefixNode) {
		// 此处计算剩余待插入的后缀的开始位置，例如我们需要插入三个后缀(abx,bx,x)，已处理了abx，则还剩余ba和x，则下面计算的位置就是b的位置
		int start = currenctIndex - reminder + 1;
		
		this.print();// 打印
		System.out.println();
		System.out.println("当前插入后缀：" + String.copyValueOf(chars, start, currenctIndex - start + 1) + "========");
		
		// dealStart表示本次插入我们需要进行查找的开始字符位置，因为由于规则2，可能出现通过后缀节点直接找到活动节点的情况
		// 如通过ab节点的后缀节点，直接找到节点b，那么此时的activePoint(node[b], null, 0)，我们需要从node[b]开始查找x，dealStart的位置就是x的位置
		int dealStart = start + activePoint.point.chars.length + activePoint.length;
		// 从dealStart开始查找所有后缀字符是否都存在(相对与活动点)
		for (int index = dealStart; index <= currenctIndex; index++) {
			char w = chars[index];
			if (find(w)) {// 存在，则查找下一个，activePoint.length+1，这里不增加reminder
				continue;
			}
			Node splitNode = null;// 被分割的节点
			if(null==activePoint.index){// 如果activePoint.index==null，说明没有找到活动边，那么只需要在活动节点下插入一个节点即可
				Node node = new Node(Arrays.copyOfRange(chars, index, chars.length));
				Node child = activePoint.point.child;
				if(null==child){
					activePoint.point.child = node;
				}else{
					while (null != child.brother) {
						child = child.brother;
					}
					child.brother = node;
				}
			}else{
				// 开始分割，分割部分同上面的分割
				splitNode = activePoint.index;
				// 创建切分后的节点，放到当前节点的子节点
				// 该节点继承了当前节点的子节点以及后缀节点信息
				Node node = new Node(Arrays.copyOfRange(splitNode.chars, activePoint.length, splitNode.chars.length));
				node.child = splitNode.child;
				node.suffixNode = splitNode.suffixNode;
				splitNode.child = node;
				splitNode.suffixNode = null;
				// 创建新插入的节点，放到当前节点的子节点(通过子节点的兄弟节点保存)
				Node newNode = new Node(Arrays.copyOfRange(chars, index, chars.length));
				splitNode.child.brother = newNode;
				// 修改当前节点的字符数
				splitNode.chars = Arrays.copyOfRange(splitNode.chars, 0, activePoint.length);
				// 规则2，连接后缀节点
				prefixNode.suffixNode = splitNode;
			}
			// --
			reminder--;

			// 按照规则1进行处理
			if (root == activePoint.point) {// 活动节点是根节点的情况
				// activePoint.point == root
			
			// 按照规则3进行处理
			} else if (null == activePoint.point.suffixNode) {// 无后缀节点，则活动节点变为root
				activePoint.point = root;
			} else {
				activePoint.point = activePoint.point.suffixNode;
			}
			
			activePoint.index = null;
			activePoint.length = 0;
			if(reminder > 0){// 如果reminder==0则不需要继续递归插入后缀
				innerSplit(chars, currenctIndex, splitNode);
			}
		}
	}

	/**
	 * 寻找当前活动点的子节点中是否存在包含后缀字符的节点(边)
	 * 
	 * @param w
	 * @return
	 */
	private boolean find(char w) {
		final Node start = activePoint.point;
		final Node current = activePoint.index;
		boolean exist = false;
		if (null == current) {// current==null 无活动边，则从活动点的子节点开始查找
			// 寻找子节点
			Node child = start.child;
			while (null != child) {
				if (child.chars[0] == w) {// 存在
					activePoint.index = child;
					activePoint.length++;// activePoint.length++
					exist = true;
					break;
				} else {
					child = child.brother;
				}
			}
		} else if (current.chars[activePoint.length] == w) {// 有活动边，则在活动边上查找
			activePoint.length++;
			exist = true;
			if (current.chars.length == activePoint.length) {
				// 如果活动边的长度已达到活动边的最后一个字符，则将活动点置为活动边，同时活动边置为null，长度置为0
				activePoint.point = current;
				activePoint.index = null;
				activePoint.length = 0;
			}
		} else {
			exist = false;
		}
		return exist;
	}
	
	/**
	 * 查找给定字符串是否是其子串
	 * 
	 * @param word
	 * @return
	 */
	public boolean select(String word) {
		char[] chars = word.toCharArray();
		int index = 0;// 查找到的节点的匹配的位置
		// 查找从根节点开始，遍历子节点
		Node start = root;
		for (int i = 0; i < chars.length; i++) {
			if (start.chars.length < index + 1) {// 如果当前节点已匹配完，则从子节点开始，同时需重置index==0
				index = 0;
				start = start.child;
				while (null != start) {
					// 比较当前节点指定位置(index)的字符是否与待查找字符一致
					// 由于是遍历子节点，所以如果不匹配换个子节点继续
					if (start.chars[index] == chars[i]) {
						index++;
						break;
					} else {
						start = start.brother;
					}
				}
				if (null == start) {// 子节点遍历完都无匹配则返回false
					return false;
				}
			} else if (start.chars[index] == chars[i]) {
				// 如果当前查找到的节点的还有可比较字符，则进行比较，如果不同则直接返回false
				index++;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * 格式化打印出整个后缀树
	 */
	public void print() {
		Node child = root.child;
		System.out.println("[root] [activePoint:(" + activePoint.point + "," + activePoint.index + ","
				+ activePoint.length + ")], [reminder:" + reminder + "]");
		while (child != null) {
			System.out.print("|——");
			child.print("    ");
			child = child.brother;
		}
	}

	/**
	 * <p>
	 * 后缀树的节点，即边
	 * </p>
	 */
	private class Node {
		public char[] chars;
		public Node child;
		public Node brother;
		public Node suffixNode;

		public Node(char[] chars) {
			this.chars = chars;
		}

		@Override
		public String toString() {
			return "Node [chars=" + String.valueOf(chars) + "]";
		}

		public void print(String prefix) {
			System.out.print(String.valueOf(chars));
			if (null != this.suffixNode) {
				System.out.println("--" + String.valueOf(this.suffixNode.chars));
			} else {
				System.out.println();
			}
			Node child = this.child;
			while (null != child) {
				System.out.print(prefix + "|——");
				child.print(prefix + prefix);
				child = child.brother;
			}
		}
	}

	/**
	 * <p>
	 * 活动点(active point)，一个三元组：(active_node,active_edge,active_length)
	 * </p>
	 */
	private class ActivePoint {
		public Node point;
		public Node index;
		public int length;

		public ActivePoint(Node point, Node index, int length) {
			this.point = point;
			this.index = index;
			this.length = length;
		}

		@Override
		public String toString() {
			return "ActivePoint [point=" + point + ", index=" + index + ", length=" + length + "]";
		}
	}

	public static void main(String[] args) {
		SuffixTree tree = new SuffixTree();
		tree.build("abcabc$");
		tree.print();

		// 查找子串
		String word = "abc$";
		System.out.println(tree.select(word));
	}
}
