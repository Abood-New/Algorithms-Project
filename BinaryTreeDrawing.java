
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ABDALRHMAN
 */
public class BinaryTreeDrawing extends JPanel {
    public Node root;

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

        g.setColor(Color.BLUE);
        g.fillOval(x, y, 50, 40);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(node.value), x + 20, y + 20);

        if (node.left != null) {
            g.setColor(Color.RED);
            g.drawLine(x + 25, y + 40, x - xOffset + 15, y + 60);
            drawTree(g, x - xOffset, y + 60, node.left, xOffset / 2);
        }
        if (node.right != null) {
            g.setColor(Color.RED);
            g.drawLine(x + 25, y + 40, x + xOffset + 15, y + 60);
            drawTree(g, x + xOffset, y + 60, node.right, xOffset / 2);
        }
    }

    public static void paint(Node root) {
        BinaryTreeDrawing panel = new BinaryTreeDrawing();

        panel.root = root;

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }

}
