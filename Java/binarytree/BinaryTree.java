package binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree{
	/*made accessible only to the BinaryTree; enforces OOP principle*/
	private class TreeNode{
		int data;
		TreeNode left;
		TreeNode right;
		//keeps track of the position where the node was inserted
		int position;

		TreeNode(int data){
			this.data = data;
			left = null;
			right = null;
			position = -1;
		}
	}

	//if debug set to true, logs of each operations would be displayed on the console
	private boolean insertDebug;
	private boolean deleteDebug;
	
	public BinaryTree(){
		insertDebug = false;
		deleteDebug = false;
	}

	TreeNode root = null;

	// log related public methods
	public void enableInsertLogs(){
		insertDebug = true;
		System.out.println("Insertion Logs enabled!");
	}

	public void enableDeleteLogs(){
		deleteDebug = true;
		System.out.println("Deletion Logs enabled!");
	}

	public void displayInsertLogs(int data, int position){
		System.out.println("New node added: Value = " + data + " Position = " + position);
	}

	public void displayDeleteLogs(int data, int position){
		System.out.println("Node removed: Value = " + data + " Position = " + position);
	}


	//insertion takes place in level order fashion
	public int insert(int data){
		TreeNode newNode = new TreeNode(data);
		if(root == null){ //first node in the binary tree
			root = newNode;
			newNode.position = 1;
			if(insertDebug == true) displayInsertLogs(data, newNode.position);
			return newNode.position;
		}
		//we insert the new node in level order fashion
		Queue <TreeNode> queue = new LinkedList<>();
		TreeNode helper = root;
		queue.add(helper);

		while(!queue.isEmpty()){
			helper = queue.peek();
			queue.remove();

			if(helper.left == null){
				helper.left = newNode;
				newNode.position = helper.position * 2;
				if(insertDebug == true) displayInsertLogs(data, newNode.position);
				return newNode.position;
			} else {
				queue.add(helper.left);
			}

			if(helper.right == null){
				helper.right = newNode;
				newNode.position = helper.position * 2 + 1;
				if(insertDebug == true) displayInsertLogs(data, newNode.position);
				return newNode.position;
			} else {
				queue.add(helper.right);
			}
		}
		return -1;
	}
}