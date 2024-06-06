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
public class GenericTreeDrawing extends JPanel {
    private Node root;
    private static JButton button;

    public GenericTreeDrawing(Node root) {
        this.root = root;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (root != null) {
            drawTree(g, getWidth() / 2, 30, root, getWidth() / 4);
        }
    }

    private void drawTree(Graphics g, int x, int y, Node node, int xOffset) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, 30, 30);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(node.val), x + 10, y + 20);

        int childCount = node.children.size();
        if (childCount > 0) {
            int startX = x - xOffset * (childCount - 1) / 2;
            for (int i = 0; i < childCount; i++) {
                g.setColor(Color.BLACK);
                g.drawLine(x + 15, y + 30, startX + i * xOffset + 15, y + 60);
                drawTree(g, startX + i * xOffset, y + 60, node.children.get(i), xOffset / 2);
            }
        }
    }
    
    public static void paint(){
        Node root = null;
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
        Node genericRoot = Generic.binaryToGeneric(binaryRoot);
        GenericTreeDrawing panel = new GenericTreeDrawing(genericRoot);
         button = new JButton("Convert To Binary Tree");
        panel.add(button);
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define what happens when the button is clicked
                BinaryTreeDrawing.paint();
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        
        paint();
    }
}
