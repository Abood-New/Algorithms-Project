/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.visual;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
class Node {
    public String val;
    public List<Node> children;
    Node left;
    Node right;

    public Node(String _val) {
        val = _val;
        this.left = null;
        this.right = null;
        children = new ArrayList<>();
    }

    public Node(String _val, List<Node> _children) {
        val = _val;
        this.left = null;
        this.right = null;
        children = _children;
    }
}
