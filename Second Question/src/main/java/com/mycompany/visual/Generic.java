/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.visual;

import java.util.*;

/**
 *
 * @author ABDALRHMAN
 */
/*
// Definition for a Node.
*/
class Generic {
    
    public static Node genericToBinary(Node root) {
        if (root == null) {
            return null;
        }

        Node node = new Node(root.val);

        if (!root.children.isEmpty()) {
            node.right = genericToBinary(root.children.get(root.children.size() - 1));
        }

        Node current = node.right;
        for (int i = root.children.size() - 2; i >= 0; i--) {
            current.left = genericToBinary(root.children.get(i));
            current = current.left;
        }

        return node;
    }
    
    public static Node binaryToGeneric(Node binaryTree) {
        if (binaryTree == null) {
          return null;
        }

        Node generalRoot = new Node(binaryTree.val);
        helper(binaryTree, generalRoot, null);

        return generalRoot;
}

public static void helper(Node binaryNode, Node generalNode, Node parent) {
        if (binaryNode.right != null) {
          Node child = new Node(binaryNode.right.val);
          generalNode.children.add(0,child);
          helper(binaryNode.right, child, generalNode);
        }
        if (binaryNode.left != null) {
          Node sibling = new Node(binaryNode.left.val);
          if (parent != null) {
            parent.children.add(0,sibling);
          }
          helper(binaryNode.left, sibling, parent);
        }
}
    // Pre-Order Traversal
    public static void PrintBinaryTree(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val+" ");
        PrintBinaryTree(root.left);
        PrintBinaryTree(root.right);
    }
    
    public static void printGenericTree(Node root) {
        if (root == null) {
            System.out.println("Binary Tree is empty.");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println(current.val);
            int len = current.children.size();
            for(int i=0;i<len;i++)
                queue.offer(current.children.get(i));
            }
            System.out.println();
        }
    }

