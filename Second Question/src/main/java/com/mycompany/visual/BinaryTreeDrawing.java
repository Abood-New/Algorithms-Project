/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.visual;
import static com.mycompany.visual.Main.findOrCreateNode;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author ABDALRHMAN
 */
@SuppressWarnings("serial")
    public class BinaryTreeDrawing extends JPanel {
    private Node root;
    private static JButton button;

    public BinaryTreeDrawing() {
        root = null;
       
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, getWidth() / 2, 30, root, getWidth() / 4);
    }

    private void drawTree(Graphics g, int x, int y, Node node, int xOffset) {
        if (node == null)
            return;
        
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(node.val, x + 10, y + 20);

        if (node.left != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x + 15, y + 30, x - xOffset + 15, y + 60);
            drawTree(g, x - xOffset, y + 60, node.left, xOffset / 2);
        }
        if (node.right != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x + 15, y + 30, x + xOffset + 15, y + 60);
            drawTree(g, x + xOffset, y + 60, node.right, xOffset / 2);
        }
    }
    
    public static void paint(){
        BinaryTreeDrawing panel = new BinaryTreeDrawing();Node root = null;
            try {
                try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split("->");
                        String parentVal = parts[0].trim();
                        String[] children = parts[1].split(",");
                        Node parentNode = findOrCreateNode(root, parentVal);
                        if (root == null) {
                            root = parentNode;
                        }
                        for (String child : children) {
                            Node childNode = new Node(child.trim());
                            parentNode.children.add(childNode);
                        }
                    }
                }
            } catch (IOException e) {
            }
        
        Node binaryRoot = Generic.genericToBinary(root);
        panel.root = binaryRoot;
        button = new JButton("Convert To Generic");
        panel.add(button);
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                GenericTreeDrawing.paint();
                frame.dispose();
            }
            
        });
    }

    public static void main(String[] args) {
        
        paint();
//        BinaryTreeDrawing panel = new BinaryTreeDrawing();
////        Node root = new Node("A");
////        root.children.add(new Node("B"));
////        root.children.add(new Node("C"));
////        root.children.add(new Node("D"));
////
////        root.children.get(0).children.add(new Node("E"));
////        root.children.get(0).children.add(new Node("F"));
////        
////        root.children.get(1).children.add(new Node("G"));
////
////        root.children.get(2).children.add(new Node("H"));
////        root.children.get(2).children.add(new Node("I"));
////        root.children.get(2).children.add(new Node("J"));
////        root.children.get(2).children.get(2).children.add(new Node("K"));
//        Node root = null;
//            try {
//                try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        String[] parts = line.split("->");
//                        String parentVal = parts[0].trim();
//                        String[] children = parts[1].split(",");
//                        Node parentNode = findOrCreateNode(root, parentVal);
//                        if (root == null) {
//                            root = parentNode;
//                        }
//                        for (String child : children) {
//                            Node childNode = new Node(child.trim());
//                            parentNode.children.add(childNode);
//                        }
//                    }
//                }
//            } catch (IOException e) {
//            }
//        
//        Node binaryRoot = Generic.genericToBinary(root);
//        panel.root = binaryRoot;
//        
//        JFrame frame = new JFrame();
//        frame.setSize(800, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(panel);
//        frame.setVisible(true);
        
    }
}

